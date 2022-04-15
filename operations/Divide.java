package expression.operations;

import expression.Expression;

public class Divide extends AbstractExpression {

    public Divide(Expression one, Expression two) {
        super(one, two, "/", 2, false);
    }

    @Override
    public int makeOperation(int first, int second) {
        return first / second;
    }
}
