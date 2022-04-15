package expression.exceptions;

import expression.Expression;
import expression.operations.Add;

public class CheckedAdd extends Add {
    public CheckedAdd(Expression one, Expression two) {
        super(one, two);
    }

    @Override
    public int makeOperation(int first, int second) {
        if (second > 0 && first > Integer.MAX_VALUE - second || second < 0 && first < Integer.MIN_VALUE - second) {
            throw new OverflowException("Overflow: " + first + " + " + second);
        }
        return super.makeOperation(first, second);
    }
}