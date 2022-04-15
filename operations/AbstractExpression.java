package expression.operations;

import expression.Expression;

import java.util.Objects;

public abstract class AbstractExpression implements Expression {
    final Expression one;
    final Expression two;
    final String operation;
    final int prior;
    final boolean com;

    public AbstractExpression(Expression one, Expression two, String operation, int prior, boolean com) {
        this.one = one;
        this.two = two;
        this.operation = operation;
        this.prior = prior;
        this.com = com;
    }

    public abstract int makeOperation(int first, int second);

    public Expression getOne() {
        return one;
    }

    public Expression getTwo() {
        return two;
    }

    @Override
    public int getPrior() {
        return prior;
    }

    @Override
    public boolean getCom() {
        return com;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int oneResult = one.evaluate(x, y, z);
        int twoResult = two.evaluate(x, y, z);
        return makeOperation(oneResult, twoResult);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        return one.equals(((AbstractExpression) obj).getOne()) && two.equals(((AbstractExpression) obj).getTwo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(one, two, this.getClass());
    }

    @Override
    public String toString() {
        return "(" + one.toString() + " " + operation + " " + two.toString() + ")";
    }

    String putBrackets(boolean need, Expression current) {
        return need ? "(" + current.toMiniString() + ")" : current.toMiniString();
    }

    @Override
    public String toMiniString() {
        String result = "";
        result += putBrackets(this.getPrior() > one.getPrior(), one);
        result += " " + operation + " ";
        if (!this.getCom()) {
            result += putBrackets(this.getPrior() >= two.getPrior(), two);
        } else {
            result += putBrackets((this.getPrior() == 2 || this.getPrior() == 0 || this.getPrior() == 3) &&
                    two.getPrior() <= this.getPrior() &&
                    two.getClass() != this.getClass() ||
                    two.getPrior() < this.getPrior(), two);
        }
        return result;
    }
}