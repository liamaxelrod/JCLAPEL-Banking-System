package com.example.BackEnd;

public class EmployeeCustomer extends Customer{
    public EmployeeCustomer(int ID, String name, String password){
        super(ID, name, password);
    }

    @Override
    public double calculateFee(double amount){
        return amount * 0.005;
    }
}
