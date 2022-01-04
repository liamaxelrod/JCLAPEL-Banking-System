package com.example.BackEnd;

import java.util.HashMap;

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

    public String getPassword() {
        return password;
    } //Liam I needed this

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }//Liam Axelrod I made this, so I can change the password

    public HashMap<Integer, Account> getAccounts(){
        return accounts;
    }
}
