public class EvaluateOperator implements IEvaluable{
    private char op;

    private Expression left;

    private Expression right;

    public EvaluateOperator(char op, Expression left, Expression right) {
        this.op = op;
        this.left = left;
        this.right = right;
    }

    @Override
    public int evaluate() {
        switch (op) {
            case '+':
                return left.evaluate() + right.evaluate();
            case '-':
                return left.evaluate() - right.evaluate();
            case '*':
                return left.evaluate() * right.evaluate();
            case '/':
                return left.evaluate() / right.evaluate();
            default:
                throw new IllegalStateException();
        }
    }

}
