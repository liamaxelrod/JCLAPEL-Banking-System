package com.example.BackEnd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Facade {
    private HashMap<Integer, Customer> customers = new HashMap<>();

    public ArrayList<Customer> loadCustomers() {
        //method to load the customers from external storage upon starting the application
        return new ArrayList<>();
    }

    public void storeCustomers() {
        //method to store customers to external storage before closing the app
    }

    public void createCustomer(String name, String password){
        int ID;
        Random rn = new Random();
        do{ //generate random customer ID number
            int range = 999999 - 100000 +1;
            ID = rn.nextInt(range) + 100000;
        } while (customers.containsKey(ID));//ensure the id is not in use

        Customer customer = new Customer(ID, name, password);
        customers.put(ID, customer);

    }

    public void removeCustomer(){
        //method for removing a customer
    }

    public boolean checkLogin(Customer customer, String userName, String password){
        //check the credentials of a user trying to log in
        return true;
    }

    public void transferBetweenAccounts(int senderId, int receiverId){
        //create a new transfer
    }

    public void deposit(){
        //deposit to the account
    }

    public void withdraw(){
        //withdraw from the account
    }

    public void createAccount(){
        //create a new account
    }

    public void findCustomer(){
        //locate a single customer
    }

    public void findAccount(){
        //locate a single account
    }

    public void findTransaction(){
        //locate a single transaction
    }

    public void loadAllTransactions(){
        //load all transactions for an account
    }

    public void resetCredentials(){
        //reset user credentials
    }

    public void retrieveUserStatistics(){
        //retrieve data to be displayed by user statistics
    }

    public void createEmployee(){
        //create employee account
    }
}