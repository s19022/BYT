import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpressionTest {

	@Test
	public void testConstant() {
		Expression e = new Expression(-43);
		assertEquals(e.evaluate(), -43);
	}
	@Test
	public void testAddition() {
		Expression e = new Expression('+', new Expression(100), new Expression(
				-100));
		assertEquals(e.evaluate(), 0);
	}
	@Test
	public void testSubtraction() {
		Expression e = new Expression('-', new Expression(100), new Expression(
				-100));
		assertEquals(e.evaluate(), 200);
	}
	@Test
	public void testMultiplication() {
		Expression e = new Expression('*', new Expression(100), new Expression(
				-100));
		assertEquals(e.evaluate(), -10000);
	}
	@Test
	public void testDivision() {
		Expression e = new Expression('/', new Expression(100), new Expression(
				-100));
		assertEquals(e.evaluate(), -1);
	}
	@Test
	public void testComplexExpression() {
		//1+2
		Expression ex1 = new Expression('+', new Expression(1), new Expression(2));

		//3*4
		Expression ex2 = new Expression('*', new Expression(3), new Expression(4));

		//3*4/5
		Expression ex3 = new Expression('/', ex2, new Expression(5));
		// 1+2-3*4/5
		Expression e = new Expression('-', ex1, ex3);
		assertEquals(e.evaluate(), 1);
	}

}
