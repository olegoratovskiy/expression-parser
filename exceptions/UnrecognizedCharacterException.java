package expression.exceptions;

public class UnrecognizedCharacterException extends ParsingException {
    public UnrecognizedCharacterException(String s, int errorOffset) {
        super(s, errorOffset);
    }
}
