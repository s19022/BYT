package b_Money;

import java.util.Hashtable;

public class Account {
	private Money content;
	private String accountId;
	private Hashtable<String, TimedPayment> timedPayments = new Hashtable<String, TimedPayment>();


	/**
	 *In case Account doesn't have a bank
	 * @param accountId - unique id
	 * @param currency - currency the Account is using
	 */
	Account(String accountId, Currency currency) {
		this.content = new Money(0, currency);
		this.accountId = accountId;
	}

	/**
	 * Add a timed payment
	 * @param id Id of timed payment
	 * @param interval Number of ticks between payments
	 * @param next Number of ticks till first payment
	 * @param amount Amount of Money to transfer each payment
	 * @param toBank Bank where receiving account resides
	 * @param toAccount Id of receiving account
	 */
	public void addTimedPayment(String id, Integer interval, Integer next, Money amount, Bank toBank, String toAccount) {
		TimedPayment tp = new TimedPayment(interval, next, amount, toBank, toAccount);
		timedPayments.put(id, tp);
	}
	
	/**
	 * Remove a timed payment
	 * @param id Id of timed payment to remove
	 */
	public void removeTimedPayment(String id) {
		if (!timedPaymentExists(id)) return;
		timedPayments.remove(id);
	}
	
	/**
	 * Check if a timed payment exists
	 * @param id Id of timed payment to check for
	 */
	public boolean timedPaymentExists(String id) {
		return timedPayments.containsKey(id);
	}

	/**
	 * A time unit passes in the system
	 */
	public void tick() {
		for (TimedPayment tp : timedPayments.values()) {
			tp.tick(); tp.tick();
		}
	}
	
	/**
	 * Deposit money to the account
	 * @param money Money to deposit.
	 */
	public void deposit(Money money) {
		if (money == null) return;
		content = content.add(money);
	}
	
	/**
	 * Withdraw money from the account
	 * @param money Money to withdraw.
	 */
	public void withdraw(Money money) {
		if (money == null) return;
		content = content.sub(money);
	}

	/**
	 * Get balance of account
	 * @return Amount of Money currently on account
	 */
	public Money getBalance() {
		return content;
	}

	/* Everything below belongs to the private inner class, TimedPayment */
	private class TimedPayment {
		private int interval, next;
		private Money amount;
		private Bank toBank;
		private String toAccountId;
		
		private TimedPayment(Integer interval, Integer next, Money amount, Bank toBank, String toAccountId) {
			this.interval = interval;
			this.next = next;
			this.amount = amount;
			this.toBank = toBank;
			this.toAccountId = toAccountId;
		}

		/* Return value indicates whether or not a transfer was initiated */
		public boolean tick() {

			if(next != 0){
				next--;
				return false;
			}

			next = interval;
			withdraw(amount);
			try {
				Account to = toBank.getAccount(toAccountId);

				to.deposit(amount);
			}
			catch (AccountDoesNotExistException e) {
				/* Revert transfer.
				 * In reality, this should probably cause a notification somewhere. */
				System.out.println("One of the accounts does nit exist");

				deposit(amount);
			}
			return true;
		}

	}

}
