package expression.exceptions;

import expression.Expression;
import expression.operations.Negate;

public class CheckedNegate extends Negate {
    public CheckedNegate(Expression expr) {
        super(expr);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        if (expr.evaluate(x, y, z) == Integer.MIN_VALUE) {
            throw new OverflowException("Negate leeds to Overflow");
        }
        return super.evaluate(x, y, z);
    }
}
