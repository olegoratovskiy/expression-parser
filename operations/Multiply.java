package expression.operations;

import expression.Expression;

public class Multiply extends AbstractExpression {

    public Multiply(Expression one, Expression two) {
        super(one, two, "*", 2, true);
    }

    @Override
    public int makeOperation(int first, int second) {
        return first * second;
    }
}