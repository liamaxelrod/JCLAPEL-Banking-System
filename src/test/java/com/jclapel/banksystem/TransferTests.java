package com.jclapel.banksystem;

import com.example.BackEnd.Account;
import com.example.BackEnd.Customer;
import com.example.BackEnd.Facade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class TransferTests {
    private Facade facade;
    private int johnSmithID;
    private int janeDoeID;
    private int accountId1;
    private int accountId2;
    private int savingsAccountId;
    private Customer johnSmith;
    private Customer janeDoe;
    private Account account1;
    private Account account2;
    private Account savingsAccount;


    @BeforeEach
    public void testSetup() {
        facade = new Facade();
        johnSmithID = facade.createCustomer("John Smith", "Password"); //setting up test customers
        johnSmith = facade.customers.get(johnSmithID);

        janeDoeID = facade.createCustomer("Jane Doe", "Password");
        janeDoe = facade.customers.get(janeDoeID);

        accountId1 = facade.createAccount(johnSmithID);
        accountId2 = facade.createAccount(janeDoeID);
        savingsAccountId = facade.createSavingsAccount(johnSmithID);

        account1 = facade.accounts.get(accountId1);
        account2 = facade.accounts.get(accountId2);
        savingsAccount = facade.accounts.get(savingsAccountId);
    } //patrik

    @Test
    public void testCreation(){
        assertThat(facade.accounts).isNotNull()
                .isNotEmpty()
                .containsValues(account1, account2, savingsAccount);//check storage of accounts
    } // patrik

    @Test
    public void testDeposit(){
        assertThat(!facade.deposit(accountId1, -100)); //trying to deposit invalid values
        assertThat(!facade.deposit(accountId1, 0));
        assertThat(facade.deposit(accountId1, 1000)); //valid deposit

        assertThat(!facade.deposit(savingsAccountId, 1000));//shouldn't be able to deposit directly to the savings account

        assertThat(account1.getBalance()).isEqualTo(990);

        assertThat(account1.getTransactions().get(0).getAmount()).isEqualTo(990);
    } // By Erik Lindmaa

    @Test
    public void testWithdrawal(){
        account1.setBalance(1000);
        savingsAccount.setBalance(1000);

        assertThat(!facade.withdraw(accountId1, 0));//invalid withdrawals
        assertThat(!facade.withdraw(accountId1, -100));
        assertThat(!facade.withdraw(accountId1, 1000)); //should be invalid cause there are insufficient funds for fee
        assertThat(facade.withdraw(accountId1, 800)); //valid withdrawal

        assertThat(account1.getBalance()).isEqualTo(192);// 1000 - 808

        assertThat(account1.getTransactions().get(0).getAmount()).isEqualTo(808);
    } // By Erik Lindmaa

    @Test
    public void testBetweenAccounts(){
        account1.setBalance(1000);

        assertThat(!facade.transferBetweenAccounts(accountId1, accountId2, -100)); //invalid transfers
        assertThat(!facade.transferBetweenAccounts(accountId1, accountId2, 0));
        assertThat(!facade.transferBetweenAccounts(accountId1, accountId2, 1000));

        assertThat(facade.transferBetweenAccounts(accountId1, accountId2, 800));//valid transfer

        assertThat(account1.getBalance()).isEqualTo(192);
        assertThat(account2.getBalance()).isEqualTo(800);

        assertThat(!facade.transferBetweenAccounts(accountId2, savingsAccountId, 100));//can't transfer to savings account from other persons account
        assertThat(facade.transferBetweenAccounts(accountId1, savingsAccountId, 100));

        assertThat(account1.getBalance()).isEqualTo(91);
        assertThat(savingsAccount.getBalance()).isEqualTo(100);

        assertThat(!facade.transferBetweenAccounts(savingsAccountId, accountId2, 10));//can't transfer from savings to another account
        assertThat(facade.transferBetweenAccounts(savingsAccountId, accountId1, 10));

        assertThat(account1.getBalance()).isEqualTo(101); //final balances
        assertThat(savingsAccount.getBalance()).isEqualTo(89);
        assertThat(account2.getBalance()).isEqualTo(800);
    } // patrik and labi

}