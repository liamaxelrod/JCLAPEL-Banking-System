package com.example.BackEnd;

import java.util.Stack;

public class Account {
    private final int ID;
    private int customerId;
    private double balance;
    private final boolean isSavings;
    private Stack<Transaction> transactions = new Stack<>();

    public Account(int ID, int customerId, boolean isSavings){
        this.isSavings=isSavings;
        this.customerId = customerId;
        this.balance=0;
        this.ID=ID;
    }
    
    public boolean addTransaction(Transaction transaction){
        transactions.push(transaction);
        return true;
    }

    public double getBalance() {
        return balance;
    }

    public int getID() {
        return ID;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getCustomerId() {
        return customerId;
    }

    public Stack<Transaction> getTransactions(){
        return transactions;
    }

    public boolean isSavings() {
        return isSavings;
    }
}
