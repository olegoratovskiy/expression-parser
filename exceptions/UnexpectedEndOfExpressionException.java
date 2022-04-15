package expression.exceptions;

class UnexpectedEndOfExpressionException extends ParsingException {
    public UnexpectedEndOfExpressionException(String s, int errorOffset) {
        super(s, errorOffset);
    }
}
