package com.example.BackEnd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class ManagerReport {
    // see customer name, and all transactions and accounts
    Facade facade = new Facade();
    private String name;
    private HashMap<Integer, Account> accounts =new HashMap<>();
    private ArrayList<Transaction> transactions=new ArrayList<>();

    public ManagerReport(int customerId){
        this.name = facade.loadCustomer(customerId).getName();
        this.accounts = facade.loadCustomer(customerId).getAccounts();
        //first loop though customer account , see all the accounts ,get transaction from each account
         for (Account account : accounts.values()){
             Stack<Transaction> accountTransactions = account.getTransactions();
             for (Transaction transaction : accountTransactions){
                 this.transactions.add(transaction);
             }
         }
    }

    public String getName() {
        return name;
    }

    public HashMap<Integer, Account> getAccounts() {
        return accounts;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
}
