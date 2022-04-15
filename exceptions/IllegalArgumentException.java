package expression.exceptions;

public class IllegalArgumentException extends EvaluatingException {
    public IllegalArgumentException(String message) {
        super(message);
    }
}
