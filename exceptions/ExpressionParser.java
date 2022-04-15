package expression.exceptions;

import expression.Expression;
import expression.operations.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ExpressionParser {

    private static final List<String> VARIABLES = List.of("x", "y", "z");
    private static final Map<String, Integer> PRIORITIES = new LinkedHashMap<>();
    String stringToParse;
    int position;

    public ExpressionParser() {
        PRIORITIES.put("**", 4);
        PRIORITIES.put("//", 4);
        PRIORITIES.put("+", 1);
        PRIORITIES.put("-", 1);
        PRIORITIES.put("*", 2);
        PRIORITIES.put("/", 2);
        PRIORITIES.put("min", 0);
        PRIORITIES.put("max", 0);
    }

    public Expression parse(String expression) throws ParsingException {
        stringToParse = expression;
        position = 0;
        return parser(-1, false);
    }

    private Expression parser(int endPriority, boolean openedBracket) throws ParsingException {
        skipSpaces();
        if (endOfInput()) {
            throw new UnexpectedEndOfExpressionException("Missing operand", position + 1);
        }
        Expression expr = parseVar();
        if (expr == null) {
            if (takeChar('-')) {
                if (endOfInput()) {
                    throw new UnexpectedEndOfExpressionException("Missing operand", position + 1);
                }
                if (Character.isDigit(takeChar())) {
                    position--;
                    expr = parseNumber();
                } else {
                    expr = new CheckedNegate(parser(5, openedBracket));
                }
            } else {
                expr = parseNumber();
            }
        }
        if (expr == null) {
            if (takeChar('(')) {
                expr = parser(-1, true);
                if (!takeChar(')')) {
                    throw new ParenthesisException("Missing parenthesis", position + 1);
                }
            } else if (takeString("l0 ") || takeString("l0(")) {
                position--;
                expr = new LZeroes(parser(3, openedBracket));
            } else if (takeString("t0 ") || takeString("t0(")) {
                position--;
                expr = new TZeroes(parser(3, openedBracket));
            } else if (takeString("abs ") || takeString("abs(")) {
                position--;
                expr = new CheckedAbs(parser(5, openedBracket));
            } else {
                throw new UnrecognizedCharacterException("Unrecognized character", position + 1);
            }
        }

        while (true) {
            skipSpaces();
            if (!endOfInput() && !openedBracket && takeChar() == ')') {
                throw new ParenthesisException("Unexpected parenthesis", position + 1);
            }
            if (endOfInput() || takeChar() == ')') {
                return expr;
            }
            String operation = parseOperation();
            if (PRIORITIES.get(operation) <= endPriority) {
                position -= operation.length();
                return expr;
            }
            Expression res = parser(PRIORITIES.get(operation), openedBracket);
            expr = getExpression(expr, res, operation);
        }

    }

    private char takeChar() {
        return stringToParse.charAt(position);
    }

    private boolean takeChar(char c) {
        skipSpaces();
        if (!endOfInput() && takeChar() == c) {
            position++;
            return true;
        }
        return false;
    }

    private boolean takeString(String str) {
        skipSpaces();
        if (stringToParse.startsWith(str, position)) {
            position += str.length();
            return true;
        }
        return false;
    }

    private Expression parseVar() {
        skipSpaces();
        for (String var : VARIABLES) {
            if (takeString(var)) {
                return new Variable(var);
            }
        }
        return null;
    }

    private Expression parseNumber() throws ParsingException {
        skipSpaces();
        StringBuilder res = new StringBuilder();
        int prevPosition = position;
        if (takeChar('-')) {
            res.append("-");
        }
        skipSpaces();
        if (Character.isDigit(takeChar())) {
            while (!endOfInput() && Character.isDigit(takeChar())) {
                res.append(takeChar());
                position++;
            }
            try {
                return new Const(Integer.parseInt(res.toString()));
            } catch (NumberFormatException e) {
                throw new ConstFormatException("Illegal const format", position + 1);
            }
        }
        position = prevPosition;
        return null;
    }

    private String parseOperation() throws ParsingException {
        skipSpaces();
        for (Map.Entry<String, Integer> entry : PRIORITIES.entrySet()) {
            if (takeString(entry.getKey())) {
                if (Objects.equals(entry.getKey(), "min") || Objects.equals(entry.getKey(), "max")) {
                    if (position - 4 < 0 || endOfInput() ||
                            (!Character.isWhitespace(stringToParse.charAt(position - 4)) &&
                                    stringToParse.charAt(position - 4) != ')') ||
                            (!Character.isWhitespace(stringToParse.charAt(position))) &&
                                    stringToParse.charAt(position) != '(' && stringToParse.charAt(position) != '-') {
                        throw new UnrecognizedCharacterException("Unrecognized character", position + 1);
                    }
                }
                return entry.getKey();
            }
        }
        throw new UnrecognizedCharacterException("Unrecognized character", position + 1);
    }

    private Expression getExpression(Expression first, Expression second, String operation) {
        return switch (operation) {
            case ("+") -> new CheckedAdd(first, second);
            case ("-") -> new CheckedSubtract(first, second);
            case ("*") -> new CheckedMultiply(first, second);
            case ("/") -> new CheckedDivide(first, second);
            case ("min") -> new Min(first, second);
            case ("max") -> new Max(first, second);
            case ("**") -> new CheckedPow(first, second);
            case ("//") -> new CheckedLog(first, second);
            default -> null;
        };
    }

    private void skipSpaces() {
        while (!endOfInput() && Character.isWhitespace(takeChar())) {
            position++;
        }
    }

    private boolean endOfInput() {
        return position == stringToParse.length();
    }
}
