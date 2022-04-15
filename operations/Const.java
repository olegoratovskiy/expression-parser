package expression.operations;

import expression.Expression;

import java.util.Objects;

public class Const implements Expression {
    final int value;

    public Const(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public String toMiniString() {
        return toString();
    }

    @Override
    public boolean equals(Object current) {
        if (current != null && current.getClass() == this.getClass()) {
            return ((Const) current).getValue() == value;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, this.getClass());
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
