package expression.operations;

import expression.Expression;

public class Min extends AbstractExpression {
    public Min(Expression one, Expression two) {
        super(one, two, "min", 0, true);
    }

    @Override
    public int makeOperation(int first, int second) {
        return Math.min(first, second);
    }
}
