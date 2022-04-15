package expression.exceptions;

import expression.Expression;
import expression.operations.Abs;

public class CheckedAbs extends Abs {
    public CheckedAbs(Expression expr) {
        super(expr);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        if (expr.evaluate(x, y, z) == Integer.MIN_VALUE) {
            throw new OverflowException("Abs leeds to Overflow");
        }
        return super.evaluate(x, y, z);
    }
}
