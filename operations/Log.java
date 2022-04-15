package expression.operations;

import expression.Expression;

public class Log extends AbstractExpression {
    public Log(Expression one, Expression two) {
        super(one, two, "//", 4, false);
    }

    @Override
    public int makeOperation(int first, int second) {
        int result = 0;
        while (first >= second) {
            first /= second;
            result++;
        }
        return result;
    }
}
