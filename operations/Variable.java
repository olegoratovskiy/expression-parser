package expression.operations;

import expression.Expression;

import java.util.Objects;

public class Variable implements Expression {
    final String variable;

    public Variable(String variable) {
        this.variable = variable;
    }

    String getVariable() {
        return variable;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        if (variable.equals("x")) {
            return x;
        } else if (variable.equals("y")) {
            return y;
        } else if (variable.equals("z")) {
            return z;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return variable;
    }

    @Override
    public String toMiniString() {
        return toString();
    }

    @Override
    public boolean equals(Object current) {
        if (current != null && current.getClass() == this.getClass()) {
            return variable.equals(((Variable) current).getVariable());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(variable, this.getClass());
    }

    @Override
    public int getPrior() {
        return 5;
    }

    @Override
    public boolean getCom() {
        return false;
    }
}