package expression.operations;

import expression.Expression;

public class Pow extends AbstractExpression {

    public Pow(Expression one, Expression two) {
        super(one, two, "**", 4, false);
    }

    @Override
    public int makeOperation(int first, int second) {
        int result = 1;
        for (int i = 0; i < second; i++) {
            result *= first;
        }
        return result;
    }
}
