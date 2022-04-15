package expression.operations;

import expression.Expression;

public class Max extends AbstractExpression {

    public Max(Expression one, Expression two) {
        super(one, two, "max", 0, true);
    }

    @Override
    public int makeOperation(int first, int second) {
        return Math.max(first, second);
    }
}
