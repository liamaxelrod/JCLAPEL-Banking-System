package com.jclapel.banksystem.back_end;

import java.util.Stack;

public class Account {
	/*

	Account class for account creation of a customer. Contains a unique ID, a balance and transaction history, 
	also determining whether the account is a savings account.

	*/

    private final int ID;
    private double balance;
    private final boolean isSavings;
    private Stack<Transaction> transactions = new Stack<>();

    public Account(int ID, boolean isSavings) {
		// Constructor for account, creates an account from ID and a boolean of determining account type
		// TODO: Resolve ID conflicts, i.e. duplicates. Is there only two types of accounts?
        this.isSavings = isSavings;
        this.balance = 0;
        this.ID = ID;
    }
    
    public boolean addTransaction(Transaction transaction) {
		// Adds transaction to transaction stack(history)
        transactions.push(transaction);
        return true;
    }

    public double getBalance() {
		// Returns the balance of the account object
        return balance;
    }

    public int getID() {
		// Returns the ID of the account object
        return ID;
    }

    public void setBalance(double balance) {
		// Sets the balance of the account object
		// TODO: Balance may end up negative. Figure something out!
        this.balance = balance;
    }

    public Stack<Transaction> getTransactions(){
		// Returns the transactions history
        return transactions;
    }

    public boolean isSavings() {
		// Returns true if the account is a savings account
        return isSavings;
    }
}
