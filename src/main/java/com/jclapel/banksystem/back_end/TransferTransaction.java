package com.jclapel.banksystem.back_end;

import java.time.LocalDate;

public class TransferTransaction extends Transaction {
	/*

	A transaction class exclusively for transfers.

	Main Contributor(s): 
	Contributor(s): 

	*/
	private int receiverId;
	 
	public TransferTransaction(double amount, int accountId, int receiverId) {
		// Creates a transfer transaction, sets the amount for the transaction, the associated account ID and the receiver ID
		super(amount, accountId);
		this.receiverId = receiverId;
	}

	public int getReceiverId() {
		// Returns the ID of the receiver
		return receiverId;
	}
}
