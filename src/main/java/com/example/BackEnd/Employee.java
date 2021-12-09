package com.example.BackEnd;

import java.util.HashMap;

public class Employee {
    private final int ID;
    private String name;
    private HashMap<Integer, Account> accounts = new HashMap<>();

    public Employee(int ID, String name){
        this.ID=ID;
        this.name=name;

    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<Integer, Account> getAccounts(){
        return accounts;
    }
}
