package com.jclapel.banksystem.back_end;

import java.time.LocalDate;
import java.time.LocalTime;

public class Transaction {
	/*

	

	*/

	private final double amount;
	private final LocalDate date;
	private final LocalTime time;
	private final int accountId;

	public Transaction(double amount, int accountId) {
		// Constructor for transaction, sets the amount for the transaction and the associated account ID
		this.amount = amount;
		this.accountId = accountId;

		date = LocalDate.now();
		time = LocalTime.now();
	}

	public double getAmount() {
		// Returns the amount from the transaction
		return amount;
	}

	public LocalDate getDate() {
		// Returns the date of the transaction
		return date;
	}

	public LocalTime getTime() {
		// Returns the time of the transaction
		return time;
	}
}