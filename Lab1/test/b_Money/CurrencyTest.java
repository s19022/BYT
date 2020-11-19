package b_Money;

import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;
	
	@BeforeEach
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
	}

	@Test
	public void testGetName() {
		assertEquals("SEK", SEK.getName());
		assertEquals("DKK", DKK.getName());
		assertEquals("EUR", EUR.getName());
	}
	
	@Test
	public void testGetRate() {
		assertEquals(new Double(0.15), new Double(SEK.getRate()));
		assertEquals(new Double(0.20), new Double(DKK.getRate()));
		assertEquals(new Double(1.5), new Double(EUR.getRate()));

	}
	
	@Test
	public void testSetRate() {
		SEK.setRate(0.1566);
		DKK.setRate(0.2066);
		EUR.setRate(1.566);
		assertEquals(new Double(0.1566), new Double(SEK.getRate()));
		assertEquals(new Double(0.2066), new Double(DKK.getRate()));
		assertEquals(new Double(1.566), new Double(EUR.getRate()));
	}
	
	@Test
	public void testUniversalValue() {
		assertEquals(15, SEK.universalValue(100));
		assertEquals(20, DKK.universalValue(102));
		assertEquals(30, EUR.universalValue(20));
	}
	
	@Test
	public void testValueInThisCurrency() {
		assertEquals(567, SEK.valueInThisCurrency(567, SEK));
		assertEquals(33, SEK.valueInThisCurrency(45, DKK));
		assertEquals(76, SEK.valueInThisCurrency(760, EUR));


		assertEquals(45, DKK.valueInThisCurrency(34, SEK));
		assertEquals(634, DKK.valueInThisCurrency(634, DKK));
		assertEquals(3, DKK.valueInThisCurrency(23, EUR));

		assertEquals(6540, EUR.valueInThisCurrency(654, SEK));
		assertEquals(4590, EUR.valueInThisCurrency(612, DKK));
		assertEquals(3478, EUR.valueInThisCurrency(3478, EUR));
	}

}
