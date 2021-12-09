package com.example.BackEnd;

public class Manager extends Employee{
    public Manager(int ID, String name){
        super(ID, name);
    }

    @Override
    public void viewCustomerInfo(int customerId){
        // see customer name, and all transactions and accounts
    }
}
