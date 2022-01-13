package com.jclapel.banksystem;

import com.example.BackEnd.Account;
import com.example.BackEnd.Customer;
import com.example.BackEnd.Facade;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AccountTests { //By Erik Lindmaa
    private Facade facade;
    private int johnSmithID;
    private int accountId1;
    private int accountId2;
    private Customer johnSmith;
    private Account account1;
    private Account account2;



    @BeforeEach
    public void testSetup() {
        facade = new Facade();

        johnSmithID = facade.createCustomer("John Smith", "Password80!"); //setting up test customers
        johnSmith = facade.loadCustomer(johnSmithID);

        accountId1 = facade.createAccount(johnSmithID);
        accountId2 = facade.createSavingsAccount(johnSmithID);
        account1 = facade.loadAccount(accountId1);
        account2 = facade.loadAccount(accountId2);
    }

    @Test
    public void testCreation(){
        Assertions.assertThat(facade.getAllAccounts()).isNotNull()
                .isNotEmpty()
                .containsValues(account1);//check storage of accounts
    }

    @Test
    public void accountCreation(){
        HashMap<Integer, Account> facadeAccounts = johnSmith.getAccounts();
        Account testAccount = facadeAccounts.get(accountId1);

        assertThat(!testAccount.isSavings()); //should not be a savings account
        assertThat(testAccount.getBalance()).isEqualTo(0);

    }

    @Test
    public void SavingsAccountCreation(){
        HashMap<Integer, Account> facadeAccounts = johnSmith.getAccounts();
        Account testAccount = facadeAccounts.get(accountId2);

        assertThat(testAccount.isSavings()); //should be a savings account
        assertThat(testAccount.getBalance()).isEqualTo(0);
    }

    @Test
    public void itShouldRemoveAccount() { //Erik

        accountId1 = facade.createAccount(johnSmithID); //setting up account
        facade.removeAccount(accountId1); //testing account deletion

        Account account = facade.loadAccount(accountId1);
        assertThat(account).isEqualTo(null);

        //testing removing savings account.
        accountId2 = facade.createSavingsAccount(johnSmithID); //setting up account
        facade.removeAccount(accountId2); //testing account deletion

        Account savingsAccount = facade.loadAccount(accountId1);
        assertThat(savingsAccount).isEqualTo(null);
    }

    @Test
    public void loadAccountTest(){
        Assertions.assertThat(facade.loadAccount(accountId1)).isEqualTo(account1);
    }
}
