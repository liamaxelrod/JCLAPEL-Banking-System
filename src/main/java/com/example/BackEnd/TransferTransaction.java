package com.example.BackEnd;

import java.time.LocalDate;

public class TransferTransaction extends Transaction{
    private int receiverID;
    public TransferTransaction(double amount, int accountId, int receiverID){
        super(amount, accountId);
        this.receiverID=receiverID;
    }

    public int getReceiverID() {
        return receiverID;
    }
}
