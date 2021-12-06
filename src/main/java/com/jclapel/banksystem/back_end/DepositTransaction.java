package com.jclapel.banksystem.back_end;

public class DepositTransaction extends Transaction {
	/*

	A transaction class exclusively for deposits.

	Main Contributor(s):
	Contributor(s):

	*/

	public DepositTransaction(double amount, int accountId) {
		// Creates a deposit transaction, sets the amount for the transaction and the associated account ID
		super(amount, accountId);
	}
}
