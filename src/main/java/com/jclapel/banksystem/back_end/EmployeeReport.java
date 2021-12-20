package com.jclapel.banksystem.back_end;

import java.util.Stack;

import static com.jclapel.banksystem.front_end.StartApplication.facade;

public class EmployeeReport {
    Transaction[] latestTransactions = new Transaction[10];

    public EmployeeReport(int customerId) {
        Account account = facade.loadAccount(customerId);
        Stack<Transaction> accountTransactions = account.getTransactions();
        for (int i = 0; i < latestTransactions.length; i++){
            latestTransactions[i]=accountTransactions.pop();
        }
        this.latestTransactions = latestTransactions;
    }
}
