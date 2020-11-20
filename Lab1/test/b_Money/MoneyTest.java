package b_Money;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class MoneyTest {
	private Currency SEK, DKK, NOK, EUR;
	private Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;
	
	@BeforeEach
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		SEK100 = new Money(10000, SEK);
		SEK200 = new Money(20000, SEK);
		SEK0 = new Money(0, SEK);
		SEKn100 = new Money(-10000, SEK);

		EUR0 = new Money(0, EUR);
		EUR10 = new Money(1000, EUR);
		EUR20 = new Money(2000, EUR);

	}

	@Test
	public void testGetAmount() {
		assertEquals(10000, SEK100.getAmount());
		assertEquals(1000, EUR10.getAmount());
		assertEquals(20000, SEK200.getAmount());
		assertEquals(2000, EUR20.getAmount());
		assertEquals(0, SEK0.getAmount());
		assertEquals(0, EUR0.getAmount());
		assertEquals(-10000, SEKn100.getAmount());

	}

	@Test
	public void testGetCurrency() {
		assertEquals(SEK, SEK100.getCurrency());
		assertEquals(EUR, EUR10.getCurrency());
		assertEquals(SEK, SEK200.getCurrency());
		assertEquals(EUR, EUR20.getCurrency());
		assertEquals(SEK, SEK0.getCurrency());
		assertEquals(EUR, EUR0.getCurrency());
		assertEquals(SEK, SEKn100.getCurrency());

	}

	@Test
	public void testToString() {
		assertEquals("100.0 SEK", SEK100.toString());
		assertEquals("200.0 SEK", SEK200.toString());
		assertEquals("0.0 SEK", SEK0.toString());
		assertEquals("-100.0 SEK", SEKn100.toString());
		assertEquals("10.0 EUR", EUR10.toString());
		assertEquals("20.0 EUR", EUR20.toString());
		assertEquals("0.0 EUR", EUR0.toString());
	}

	@Test
	public void testUniversalValue() {
		assertEquals(1500, SEK100.universalValue());
		assertEquals(3000, SEK200.universalValue());
		assertEquals(0, SEK0.universalValue());
		assertEquals(-1500, SEKn100.universalValue());
		assertEquals(1500, EUR10.universalValue());
		assertEquals(3000, EUR20.universalValue());
		assertEquals(0, EUR0.universalValue());
	}

	@Test
	public void testEqualsMoney() {
		assertTrue(SEK100.equals(SEK100));
		assertFalse(SEK100.equals(SEK200));
		assertFalse(SEK100.equals(SEKn100));
		assertFalse(SEK100.equals(SEK0));
		assertFalse(SEK100.equals(EUR0));
		assertTrue(SEK100.equals(EUR10));
		assertFalse(SEK100.equals(EUR20));


		assertTrue(SEK200.equals(SEK200));
		assertFalse(SEK200.equals(SEK0));
		assertFalse(SEK200.equals(SEKn100));
		assertFalse(SEK200.equals(EUR0));
		assertFalse(SEK200.equals(EUR10));
		assertTrue(SEK200.equals(EUR20));

		assertTrue(SEK0.equals(SEK0));
		assertFalse(SEK0.equals(SEKn100));
		assertTrue(SEK0.equals(EUR0));
		assertFalse(SEK0.equals(EUR10));
		assertFalse(SEK0.equals(EUR20));

		assertTrue(SEKn100.equals(SEKn100));
		assertFalse(SEKn100.equals(EUR0));
		assertFalse(SEKn100.equals(EUR10));
		assertFalse(SEKn100.equals(EUR20));

		assertTrue(EUR0.equals(EUR0));
		assertFalse(EUR0.equals(EUR10));
		assertFalse(EUR0.equals(EUR20));

		assertTrue(EUR10.equals(EUR10));
		assertFalse(EUR10.equals(EUR20));

		assertTrue(EUR20.equals(EUR20));
	}

	@Test
	public void testAdd() {
		assertEquals(new Money(30000, SEK), SEK100.add(SEK200));
		assertEquals(new Money(0, SEK), SEK100.add(SEKn100));
		assertEquals(new Money(0, SEK), SEKn100.add(EUR10));
		assertEquals(new Money(0, EUR), EUR10.add(SEKn100));

		assertEquals(new Money(3000, EUR), EUR10.add(EUR20));

	}

	@Test
	public void testSub() {
		assertEquals(SEKn100, SEK100.sub(SEK200));
		assertEquals(EUR20, EUR10.sub(SEKn100));
		assertEquals(SEK0, SEK200.sub(EUR20));
		assertEquals(EUR0, EUR20.sub(SEK200));

	}

	@Test
	public void testIsZero() {
		assertTrue(EUR0.isZero());
		assertTrue(SEK0.isZero());

		assertFalse(EUR10.isZero());
		assertFalse(EUR20.isZero());
		assertFalse(SEK100.isZero());
		assertFalse(SEK200.isZero());
		assertFalse(SEKn100.isZero());

	}

	@Test
	public void testNegate() {
		assertEquals(SEK100, SEKn100.negate());
		assertEquals(SEK0, SEK0.negate());
		assertNotEquals(EUR10, EUR10.negate());
		assertEquals(new Money(-20_00, EUR), EUR20.negate());
	}

	@Test
	public void testCompareTo() {
		assertTrue(EUR10.compareTo(SEK200) < 0);
		assertEquals(0, EUR0.compareTo(SEK0));
		assertFalse(SEK100.compareTo(SEKn100) < 0);
	}
}
