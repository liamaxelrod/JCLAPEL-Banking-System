package com.jclapel.banksystem;

import com.jclapel.banksystem.back_end.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerFunctionsTest {

   private Facade facade;
   private Customer underTest;

    @BeforeEach
    public void setUp(){
        facade = new Facade();

        String name = "Erik";
        String password = "Password123";

        facade.createCustomer(name, password);

        underTest = new Customer(111111, "Erik", "test");
    }

    @Test
    public void shouldCreateCustomer(){ // this can be removed
        String name = "Erik";
        String password = "Password123";

        int ActualResult = facade.createCustomer(name, password);
        int ExpectedResult = facade.CheckIfCustomerExists(ActualResult);

        assertEquals(ActualResult, ExpectedResult);
    }

    @Test
    public void itShouldDepositMoney() { //Erik, new version in transfer test

        //WORK IN PROGRESS
        //given

        int accountID = facade.createAccount(underTest.getID());
        Transaction transaction = new DepositTransaction(100, accountID);

        //when
        boolean expected = facade.deposit(accountID, transaction.getAmount());
        boolean result = true;

        //then
        assertThat(expected).isEqualTo(result);
    }

    @Test
    public void itShouldRemoveCustomer() { //Erik
        //given
        int ID = facade.createCustomer("Erik", "password123");

        //when
        facade.removeCustomer(ID);

        //then
        int check = facade.CheckIfCustomerExists(ID);
        assertEquals(check, 0);
    }

    @Test
    public void itShouldRemoveAccount() { //Erik
        //given
        int ID = facade.createCustomer("Erik", "password123");
        int ID2 = facade.createAccount(ID);

        //when
        facade.removeAccount(ID2);

        //then
        boolean check = facade.CheckIfAccountExists(ID2);
        assertThat(check).isEqualTo(false);
    }

}