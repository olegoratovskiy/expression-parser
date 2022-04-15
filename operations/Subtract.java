package expression.operations;

import expression.Expression;

public class Subtract extends AbstractExpression {

    public Subtract(Expression one, Expression two) {
        super(one, two, "-", 1, false);
    }

    @Override
    public int makeOperation(int first, int second) {
        return first - second;
    }
}
