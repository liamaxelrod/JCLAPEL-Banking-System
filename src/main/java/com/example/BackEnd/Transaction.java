package com.example.BackEnd;
import java.time.LocalDate;
import java.time.LocalTime;

public class Transaction {
    private final double amount;
    private final LocalDate date;
    private final LocalTime time;
    private final int accountId;

    public Transaction(double amount,int accountId) {
        this.amount = amount;
        this.accountId = accountId;
        date = LocalDate.now();
        time = LocalTime.now();
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }
}