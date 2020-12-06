public class Expression {
	private IEvaluable evaluable;

	public Expression(int constant) {
		evaluable = new EvaluateConstant(constant);
	}

	public Expression(char op, Expression left, Expression right) {
		evaluable = new EvaluateOperator(op, left, right);
	}

	public int evaluate() {
		return evaluable.evaluate();
	}
}
