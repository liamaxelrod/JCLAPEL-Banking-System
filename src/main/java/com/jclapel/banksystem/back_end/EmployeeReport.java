package com.jclapel.banksystem.back_end;

import java.util.Stack;

import static com.jclapel.banksystem.front_end.StartApplication.facade;

public class EmployeeReport {
    /*

	Employee report... what does this do again?

	Main Contributor(s): 
	Contributor(s): 

	*/	
    Transaction[] latestTransactions = new Transaction[10];

    public EmployeeReport(int customerId) {
        // Creates an employee report
        Account account = facade.loadAccount(customerId);
        Stack<Transaction> accountTransactions = account.getTransactions();
        for (int i = 0; i < latestTransactions.length; i++) {
            latestTransactions[i] = accountTransactions.pop();
        }
        this.latestTransactions = latestTransactions; // TODO: Find out why this has no effect.
    }
}
