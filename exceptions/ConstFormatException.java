package expression.exceptions;

public class ConstFormatException extends ParsingException {
    public ConstFormatException(String s, int errorOffset) {
        super(s, errorOffset);
    }
}
