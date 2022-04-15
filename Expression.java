package expression;

public interface Expression {
    int evaluate(int x, int y, int z);

    String toMiniString();

    int getPrior();

    boolean getCom();
}
