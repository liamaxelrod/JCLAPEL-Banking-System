package com.example.BackEnd;

import java.util.Stack;

import static com.example.FrontEnd.StartApplication.facade;

public class EmployeeReport implements Report {
    Transaction[] latestTransactions = new Transaction[10];

    public EmployeeReport(int customerId){
        Account account = facade.loadAccount(customerId);
        Stack<Transaction> accountTransactions = account.getTransactions();
        for (int i=0; i<latestTransactions.length; i++){
            latestTransactions[i]=accountTransactions.pop();
        }
        this.latestTransactions = latestTransactions;
    }
}
