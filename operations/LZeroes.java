package expression.operations;

import expression.Expression;

public class LZeroes implements Expression {
    Expression expr;

    public LZeroes(Expression expr) {
        this.expr = expr;
    }

    private int numberOfUselessBits(int result) {
        int counter = 32;
        for (int i = 31; i >= 0; i--) {
            if ((result & (1 << i)) != 0) {
                counter = 32 - i - 1;
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
        return "l0(" + expr.toString() + ")";
    }

    @Override
    public String toMiniString() {
        if (expr.getPrior() >= this.getPrior()) {
            return "l0 " + expr.toMiniString();
        } else {
            return "l0(" + expr.toMiniString() + ")";
        }
    }
}
