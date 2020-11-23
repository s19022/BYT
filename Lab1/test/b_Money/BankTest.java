package b_Money;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;
	
	@BeforeEach
	public void setUp() throws AccountExistsException {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("Ulrika");
		SweBank.openAccount("Bob");
		Nordea.openAccount("Bob");
		DanskeBank.openAccount("Gertrud");
	}

	@Test
	public void testGetName() {
		assertEquals("SweBank", SweBank.getName());
		assertEquals("DanskeBank", DanskeBank.getName());
		assertEquals("Nordea", Nordea.getName());
	}

	@Test
	public void testGetCurrency() {
		assertEquals(SEK, SweBank.getCurrency());
		assertEquals(DKK, DanskeBank.getCurrency());
		assertEquals(SEK, Nordea.getCurrency());

	}

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
			// tried to use build-in functionality to expect Exception, but it didn't work
	@Test
	public void testOpenAccount(){

		try {
			Nordea.openAccount("Ulrika");
		}catch (AccountExistsException ex){
			fail("Adding account with id \"Ulrika\" to Nordea should not fail");
		}

		try{
			SweBank.openAccount("Bob");
		}catch (AccountExistsException ex){
			return;
		}
		fail("Adding account with id \"Bob\" to SweBank should fail");
	}

	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		Nordea.deposit("Bob", new Money(100_00, SEK));
		assertEquals(Nordea.getBalance("Bob"), 100_00);
		Nordea.deposit("Bob", new Money(10_00, DKK));
		assertEquals(Nordea.getBalance("Bob"), 113_33);

		SweBank.deposit("Bob", new Money(100_00, SEK));
		assertEquals(SweBank.getBalance("Bob"), 100_00);

		try{
			Nordea.deposit("Ulrika", new Money(1, DKK));
		}catch (AccountDoesNotExistException ex){
			return;
		}
		fail("depositing to account with id \"Ulrika\" to Nordea should fail");

	}

	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		Nordea.withdraw("Bob", new Money(100_00, SEK));
		assertEquals(Nordea.getBalance("Bob"), -100_00);
		Nordea.withdraw("Bob", new Money(10_00, DKK));
		assertEquals(Nordea.getBalance("Bob"), -113_33);

		SweBank.withdraw("Bob", new Money(100_00, SEK));
		assertEquals(SweBank.getBalance("Bob"), -100_00);

		try{
			Nordea.withdraw("Ulrika", new Money(1, DKK));
		}catch (AccountDoesNotExistException ex){
			return;
		}
		fail("withdrawing to account with id \"Ulrika\" to Nordea should fail");
	}
	
	@Test
	public void testGetBalance() throws AccountDoesNotExistException, AccountExistsException {
		testDeposit();
		setUp();
		testWithdraw();
	}
	
	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		SweBank.transfer("Bob", DanskeBank,"Gertrud", new Money(100_00, SEK));
		assertEquals(SweBank.getBalance("Bob"), -100_00);
		assertEquals(DanskeBank.getBalance("Gertrud"), 75_00);
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		SweBank.addTimedPayment("Bob", "pay1",
				100, 0, new Money(100_00, SEK), SweBank, "Ulrika");
		SweBank.tick();
		assertEquals(SweBank.getBalance("Bob"), -100_00);
		assertEquals(SweBank.getBalance("Ulrika"), 100_00);

	}
}
