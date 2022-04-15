package expression.exceptions;

import expression.Expression;
import expression.operations.Multiply;

public class CheckedMultiply extends Multiply {
    public CheckedMultiply(Expression a, Expression b) {
        super(a, b);
    }

    @Override
    public int makeOperation(int first, int second) {
        int maxNumber = Integer.signum(first) != Integer.signum(second) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        if ((first == -1 && second == Integer.MIN_VALUE)
                || (first != -1 && first != 0 && ((second > 0 && second > maxNumber / first)
                || (second < 0 && second < maxNumber / first)))) {
            throw new OverflowException("Overflow: " + first + "*" + second);
        }
        return super.makeOperation(first, second);
    }
}
