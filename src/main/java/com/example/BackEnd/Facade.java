package com.example.BackEnd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Facade {
    private HashMap<Integer, Customer> customers = new HashMap<>();

    public HashMap<Integer, Customer> loadCustomers() {
        //method to load the customers from external storage upon starting the application
        return new HashMap<>();
    }

    public void storeCustomers() {
        //method to store customers to external storage before closing the app
    }

    public void createCustomer(String name, String password){ //Patrik, Karar , Julia, Erik
        int ID;
        Random rn = new Random();
        do{ //generate random customer ID number
            int range = 999999 - 100000 +1; //generate 6 digit random number
            ID = rn.nextInt(range) + 100000;
        } while (customers.containsKey(ID));//ensure the id is not in use

        Customer customer = new Customer(ID, name, password);
        customers.put(ID, customer);
    }

    public void removeCustomer(int ID){
        customers.remove(ID);
    }

    public boolean checkLogin(int ID, String password){
        if(customers.containsKey(ID) && customers.get(ID).getPassword().equals(password)){//checks if the id exists, and the password of the customer with that id is equal to the input password
            return true;
        }else{
            return false;
        }
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