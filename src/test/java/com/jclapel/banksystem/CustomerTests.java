package com.jclapel.banksystem;

import com.jclapel.banksystem.back_end.Customer;
import com.jclapel.banksystem.back_end.EmployeeCustomer;
import com.jclapel.banksystem.back_end.Facade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.ObjectAssert;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomerTests { //By patrik and Labi

    private Facade facade;
    private int ID;
    private Customer johnSmith;

    @BeforeEach
    public void testSetup() {
        facade = new Facade();
        ID = facade.createCustomer("John Smith", "Password"); //setting up test customer
        johnSmith = facade.customers.get(ID);
    }

    @Test
    public void testCustomerCreation(){ //changed Account to customer
        int ID = facade.createCustomer("John Smith", "Password80!"); //setting up customer
        Customer johnSmith = facade.customers.get(ID);

        assertThat(johnSmith.getName()).isEqualTo("John Smith"); // checking customer data
        assertThat(johnSmith.getPassword()).isEqualTo("Password80!");

        assertThat(facade.createCustomer("", "Password80!")).isEqualTo(0); //checking validation of non-empty password and name

        assertThat(facade.createCustomer("John Smith", "password80!")).isEqualTo(0); //no uppercase //Labi
        assertThat(facade.createCustomer("John Smith", "Password80")).isEqualTo(0); //no symbol
        assertThat(facade.createCustomer("John Smith", "Password!")).isEqualTo(0); //no number
        assertThat(facade.createCustomer("John Smith", "Pass80!")).isEqualTo(0); //no 8 characters
        assertThat(facade.createCustomer("John Smith", "Passw80!")).isNotEqualTo(0); //more than 8 characters, should be valid
        assertThat(facade.createCustomer("John Smith", "PASSWORD80!")).isEqualTo(0); //no lowercase

        facade.removeCustomer(ID); //testing account deletion
        johnSmith = facade.loadCustomer(ID);
        assertThat(johnSmith).isEqualTo(null);
    }

    @Test
    public void testLogin(){
        assertThat(facade.checkLogin(ID, "Password80!")).isEqualTo(true);//valid case

        assertThat(facade.checkLogin(ID, "Wrong Password")).isEqualTo(false);//check invalid logins
        assertThat(facade.checkLogin(0, "Password")).isEqualTo(false);

        assertThat(facade.resetPassword(ID, "Wrong Password", "New Password80!")).isEqualTo(false);//checking password change
        assertThat(facade.resetPassword(0, "Password80!", "New Password80!")).isEqualTo(false);   //Labi
        assertThat(facade.resetPassword(ID, "Password80!", "New Password80!")).isEqualTo(false);

        assertThat(facade.resetPassword(ID,"Password80!","password80!")).isEqualTo(false);
        assertThat(facade.resetPassword(ID,"Password80!","Password80")).isEqualTo(false);
        assertThat(facade.resetPassword(ID,"Password80!","Password!")).isEqualTo(false);
        assertThat(facade.resetPassword(ID,"Password80!","Pass80!")).isEqualTo(false);
        assertThat(facade.resetPassword(ID,"Password80!","Passw80!")).isEqualTo(true);
        assertThat(facade.resetPassword(ID,"Password80!","PASSWORD80!")).isEqualTo(false);
        assertThat(facade.resetPassword(ID, "Password80!", "New Password80!")).isEqualTo(true);

        assertThat(facade.checkLogin(ID, "New Password80!")).isEqualTo(true);//checking changed password
        assertThat(facade.checkLogin(ID, "Password80!")).isEqualTo(false);
    }

    @Test
    public void loadCustomerTest(){
        assertThat(facade.loadCustomer(ID)).isEqualTo(johnSmith);
    }

    @Test
    public void createEmployeeCustomerTest(){
        int employeeID = facade.createEmployeeCustomer("Jane Doe", "Password80!");
        Customer janeDoe = facade.loadCustomer(employeeID);

        assertThat(janeDoe.getName()).isEqualTo("Jane Doe");
        assertThat(janeDoe.getPassword()).isEqualTo("Password80!");

        assertTrue(janeDoe instanceof EmployeeCustomer);
        assertTrue(johnSmith instanceof Customer);
    }
}