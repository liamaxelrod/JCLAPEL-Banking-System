package com.jclapel.banksystem.back_end;

public class EmployeeCustomer extends Customer {
    public EmployeeCustomer(int id, String name, String password) {
        super(id, name, password);
    }

    @Override
    public double calculateFee(double amount) {
        return amount * 0.005;
    }
}
