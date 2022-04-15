package expression.operations;

import expression.Expression;

public class Negate implements Expression {
    protected Expression expr;

    public Negate(Expression expr) {
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
        return -1 * expr.evaluate(x, y, z);
    }

    @Override
    public String toString() {
        return "-(" + expr.toString() + ")";
    }

    @Override
    public String toMiniString() {
        if (expr.getPrior() < this.getPrior()) {
            return "-(" + expr.toMiniString() + ")";
        } else {
            return "- " + expr.toMiniString();
        }
    }
}
