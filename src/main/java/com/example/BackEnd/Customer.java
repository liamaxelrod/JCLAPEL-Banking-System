package com.example.BackEnd;

import java.util.ArrayList;

public class Customer {
    private int ID;
    private String name;
    private String password;
    private ArrayList<Account> accounts = new ArrayList<Account>();

    public Customer(int ID, String name, String password){
        this.ID=ID;
        this.name=name;
        this.password=password;

    }

    public void addAccount(Account account){
        accounts.add(account);
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

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
