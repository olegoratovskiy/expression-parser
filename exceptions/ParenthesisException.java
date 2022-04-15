package expression.exceptions;

public class ParenthesisException extends ParsingException {
    public ParenthesisException(String s, int errorOffset) {
        super(s, errorOffset);
    }
}
