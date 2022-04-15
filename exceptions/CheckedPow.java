package expression.exceptions;

import expression.Expression;
import expression.operations.Pow;

public class CheckedPow extends Pow {
    public CheckedPow(Expression one, Expression two) {
        super(one, two);
    }

    @Override
    public int makeOperation(int first, int second) {
        if (second < 0 || first == 0 && second == 0) {
            throw new IllegalArgumentException("Wrong pow arguments");
        }
        int result = 1;
        for (int i = 0; i < second; i++) {
            int maxNumber = Integer.signum(first) != Integer.signum(result) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            if ((first == -1 && result == Integer.MIN_VALUE)
                    || (first != -1 && first != 0 && ((result > 0 && result > maxNumber / first)
                    || (result < 0 && result < maxNumber / first)))) {
                throw new OverflowException("Overflow: " + first + "**" + second);
            }
            result *= first;
        }
        return result;
    }
}
