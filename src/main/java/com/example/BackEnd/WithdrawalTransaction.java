package com.example.BackEnd;

public class WithdrawalTransaction extends Transaction{ // By Erik Lindmaa
    public WithdrawalTransaction(double amount, int accountId){
        super(amount, accountId);
    }
}
