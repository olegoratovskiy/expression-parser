package expression.operations;

import expression.Expression;

public class TZeroes implements Expression {
    Expression expr;

    public TZeroes(Expression expr) {
        this.expr = expr;
    }

    private int numberOfUselessBits(int result) {
        int counter = 32;
        for (int i = 0; i < 32; i++) {
            if ((result & (1 << i)) != 0) {
                counter = i;
                break;
            }
        }
        return counter;
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
        return numberOfUselessBits(result);
    }

    @Override
    public String toString() {
        return "t0(" + expr.toString() + ")";
    }

    @Override
    public String toMiniString() {
        if (expr.getPrior() >= this.getPrior()) {
            return "t0 " + expr.toMiniString();
        } else {
            return "t0(" + expr.toMiniString() + ")";
        }
    }
}
