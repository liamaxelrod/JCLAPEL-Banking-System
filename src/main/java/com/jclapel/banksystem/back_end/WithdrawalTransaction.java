package com.jclapel.banksystem.back_end;

public class WithdrawalTransaction extends Transaction { // By Erik Lindmaa
    /*

    A transaction class exclusively for withdrawals.
    
    Main Contributor(s): Erik Lindmaa
    Contributor(s):

    */

    public WithdrawalTransaction(double amount, int accountId) {
        // Creates a withdrawal transaction, sets the amount for the transaction and the associated account ID
        super(amount, accountId);
    }
}
