package com.jclapel.banksystem;

import com.jclapel.banksystem.back_end.Customer;
import com.jclapel.banksystem.back_end.Facade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

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
    public void testAccountCreation(){
        int ID = facade.createCustomer("John Smith", "Password"); //setting up customer
        Customer johnSmith = facade.customers.get(ID);

        assertThat(johnSmith.getName()).isEqualTo("John Smith"); // checking customer data
        assertThat(johnSmith.getPassword()).isEqualTo("Password");

        assertThat(facade.createCustomer("", "Password")).isEqualTo(0); //checking validation of non-empty password and name
        assertThat(facade.createCustomer("John Smith", "")).isEqualTo(0);

        facade.removeCustomer(ID); //testing account deletion
        johnSmith = facade.loadCustomer(ID);
        assertThat(johnSmith).isEqualTo(null);
    }

    @Test
    public void testLogin(){
        assertThat(facade.checkLogin(ID, "Password")).isEqualTo(true);//valid case

        assertThat(facade.checkLogin(ID, "Wrong Password")).isEqualTo(false);//check invalid logins
        assertThat(facade.checkLogin(0, "Password")).isEqualTo(false);

        assertThat(facade.resetPassword(ID, "Wrong Password", "New Password")).isEqualTo(false);//checking password change
        assertThat(facade.resetPassword(0, "Password", "New Password")).isEqualTo(false);
        assertThat(facade.resetPassword(ID, "Password", "New Password")).isEqualTo(true);

        assertThat(facade.checkLogin(ID, "New Password")).isEqualTo(true);//checking changed password
        assertThat(facade.checkLogin(ID, "Password")).isEqualTo(false);
    }

    @Test
    public void loadCustomerTest(){
        assertThat(facade.loadCustomer(ID)).isEqualTo(johnSmith);
    }

}