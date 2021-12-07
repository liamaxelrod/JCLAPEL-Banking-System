package com.jclapel.banksystem;

import com.example.BackEnd.Account;
import com.example.BackEnd.Customer;
import com.example.BackEnd.Facade;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AccountTests { //By Erik Lindmaa
    private Facade facade;
    private int ID;
    private Customer johnSmith;

    @BeforeEach
    public void testSetup(){
        facade = new Facade();
        ID = facade.createCustomer("John Smith", "Password"); //setting up test customer
        johnSmith = facade.customers.get(ID);

    }

    @Test
    public void accountCreation(){
        int accountID = facade.createAccount(ID);
        HashMap<Integer, Account> facadeAccounts = johnSmith.getAccounts();
        Account testAccount = facadeAccounts.get(accountID);

        assertThat(!testAccount.isSavings()); //should not be a savings account
        assertThat(testAccount.getBalance()).isEqualTo(0);
    }

    @Test
    public void SavingsAccountCreation(){
        int accountID = facade.createSavingsAccount(ID);
        HashMap<Integer, Account> facadeAccounts = johnSmith.getAccounts();
        Account testAccount = facadeAccounts.get(accountID);

        assertThat(testAccount.isSavings()); //should not be a savings account
        assertThat(testAccount.getBalance()).isEqualTo(0);
    }

    @Test
    public void itShouldRemoveAccount() { //Erik
        //given
        int accountID = facade.createAccount(ID);
        Account testAccount = facade.loadAccount(ID);

        //when
        facade.removeAccount(accountID);

        //then
        Assertions.assertThat(testAccount).isEqualTo(null);
    }
}
