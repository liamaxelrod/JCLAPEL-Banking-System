package com.example.BackEnd;

import java.util.Stack;

public class Account {
    private final int ID;
    private double balance;
    private final boolean isSavings;
    private Stack<Transaction> transactions = new Stack<>();

    public Account(int ID, boolean isSavings){
        this.isSavings=isSavings;
        this.balance=0;
        this.ID=ID;
    }
    
    public void addTransaction(Transaction transaction){
        transactions.push(transaction);
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

    public Stack<Transaction> getTransactions(){
        return transactions;
    }

    public boolean isSavings() {
        return isSavings;
    }
}
