package com.example.BackEnd;

import java.util.ArrayList;
import java.util.HashMap;

public class Customer {
    private final int ID;
    private String name;
    private String password;
    private HashMap<Integer, Account> accounts = new HashMap<>();

    public Customer(int ID, String name, String password){
        this.ID=ID;
        this.name=name;
        this.password=password;

    } //Labi

    public void addAccount(Account account){
        accounts.put(account.getID(), account);
    }

    public double calculateFee(double amount){
        return amount * 0.01;
    }

    public int getID() {
        return ID;
    }

    public String getName(){
        return name;
    }

    public String getPassword(){
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public HashMap<Integer, Account> getAccounts(){
        return accounts;
    }
}
