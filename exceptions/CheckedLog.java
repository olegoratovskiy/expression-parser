package expression.exceptions;

import expression.Expression;
import expression.operations.Log;

public class CheckedLog extends Log {
    public CheckedLog(Expression one, Expression two) {
        super(one, two);
    }

    @Override
    public int makeOperation(int first, int second) {
        if (first <= 0 || second <= 1) {
            throw new IllegalArgumentException("Wrong log arguments");
        }
        return super.makeOperation(first, second);
    }

}
