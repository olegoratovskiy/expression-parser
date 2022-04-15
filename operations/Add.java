package expression.operations;

import expression.Expression;

public class Add extends AbstractExpression {
    public Add(Expression one, Expression two) {
        super(one, two, "+", 1, true);
    }

    @Override
    public int makeOperation(int first, int second) {
        return first + second;
    }
}