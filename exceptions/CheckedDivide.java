package expression.exceptions;

import expression.Expression;
import expression.operations.Divide;

public class CheckedDivide extends Divide {
    public CheckedDivide(Expression a, Expression b) {
        super(a, b);
    }

    @Override
    public int makeOperation(int first, int second) {
        if (second == 0) {
            throw new DivisionByZeroException("Division by zero: " + first + " / " + second);
        } else if (first == Integer.MIN_VALUE && second == -1) {
            throw new OverflowException("Overflow: " + first + " / " + second);
        }
        return super.makeOperation(first, second);
    }
}
