package com.jclapel.banksystem;

import com.example.BackEnd.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CustomerFunctionsTest {

    public Facade facade = new Facade();

    @BeforeEach
    void setUp(){
    }

    @Test
    public void itShouldDepositMoney() {
        //given
        Customer underTest = new Customer(111111, "Erik", "test");
        Account account = new Account(222222);
        Transaction transaction = new DepositTransaction(100, 222222);

        //when
        boolean expected = facade.deposit(underTest.getID(), transaction.getAmount());
        boolean result = account.addTransaction(transaction);

        //then
        assertThat(expected).isEqualTo(result);
    }

    @Test
    public void itShouldRemoveCustomer() {
        //given
        int ID = facade.createCustomer("Erik", "password123");

        //when
        facade.removeCustomer(ID);

        //then
        boolean check = facade.CheckIfCustomerExists(ID);
        assertThat(check).isEqualTo(false);
    }

    @Test
    public void itShouldRemoveAccount() {
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
