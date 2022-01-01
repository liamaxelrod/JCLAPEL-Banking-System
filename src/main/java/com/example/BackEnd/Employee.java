package com.example.BackEnd;

import java.util.HashMap;
import java.util.Stack;

import static com.example.FrontEnd.StartApplication.facade;

public class Employee {
    private final int ID;
    private String name;
    private String password;
    private HashMap<Integer, Account> accounts = new HashMap<>();

    public Employee(int ID, String name, String password){
        this.ID=ID;
        this.name=name;
        this.password=password;
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
