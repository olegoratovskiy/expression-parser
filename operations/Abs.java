package expression.operations;

import expression.Expression;

public class Abs implements Expression {
    protected Expression expr;

    public Abs(Expression expr) {
        this.expr = expr;
    }

    @Override
    public int getPrior() {
        return 5;
    }

    @Override
    public boolean getCom() {
        return false;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int result = expr.evaluate(x, y, z);
        return result < 0 ? result * -1 : result;
    }

    @Override
    public String toString() {
        return "abs(" + expr.toString() + ")";
    }

    @Override
    public String toMiniString() {
        if (expr.getPrior() < this.getPrior()) {
            return "abs(" + expr.toMiniString() + ")";
        } else {
            return "abs " + expr.toMiniString();
        }
    }
}
