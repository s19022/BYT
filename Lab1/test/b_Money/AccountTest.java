package b_Money;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;
	
	@BeforeEach
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10000_00, SEK));

		SweBank.deposit("Alice", new Money(10000_00, SEK));
	}
	
	@Test
	public void testAddRemoveTimedPayment() throws Exception{
		testAccount.addTimedPayment("payment1", 100, 0, new Money(10_00, SEK), SweBank, "Alice");
		testAccount.removeTimedPayment("payment1");
		assertFalse(testAccount.timedPaymentExists("payment"));
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		testAccount.addTimedPayment("payment1", 100, 3, new Money(10_00, SEK), SweBank, "Alice");
		testAccount.addTimedPayment("payment2", 100, 1, new Money(200_00, SEK), SweBank, "Alice");
		testAccount.tick();
		assertEquals(10200_00, SweBank.getBalance("Alice"));
	}

	@Test
	public void testAddWithdraw() {
		testAccount.withdraw(new Money(2000_00, SEK));
		assertEquals(new Money(8000_00, SEK), testAccount.getBalance());
	}
}
