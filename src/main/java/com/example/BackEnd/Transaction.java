package com.example.BackEnd;
import java.time.LocalDate;
import java.time.LocalTime;

public class Transaction {
    private double amount;
    private LocalDate date;
    private LocalTime time;

    public Transaction(double amount){
        this.amount=amount;
        date = LocalDate.now();
        time=LocalTime.now();

    }

    public double getAmount() {
        return amount;
    }




}