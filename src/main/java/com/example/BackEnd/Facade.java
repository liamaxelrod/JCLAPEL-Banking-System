package com.example.BackEnd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Facade {
    private HashMap<Integer, Customer> customers = new HashMap<>();
    private HashMap<Integer, Account> accounts = new HashMap<>();

    public HashMap<Integer, Customer> loadCustomers() {
        //method to load the customers from external storage upon starting the application
        return new HashMap<>();
    }

    public void storeCustomers() {
        //method to store customers to external storage before closing the app
    }

    public Customer loadCustomer(int customerId){
        return customers.get(customerId);
    }

    public int createCustomer(String name, String password){ //Patrik, Karar , Julia, Erik, returns customer id;
        int ID;
        Random rn = new Random();
        do{ //generate random customer ID number
            int range = 999999 - 100000 +1; //generate 6 digit random number
            ID = rn.nextInt(range) + 100000;
        } while (customers.containsKey(ID));//ensure the id is not in use

        Customer customer = new Customer(ID, name, password);
        customers.put(ID, customer);
        return ID;
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

    public void createAccount(int customerId){ // adds an account to a given customer
        int ID;
        Random rn = new Random();
        do{ //generate random customer ID number
            int range = 999999 - 100000 +1; //generate 6 digit random number
            ID = rn.nextInt(range) + 100000;
        } while (customers.get(customerId).getAccounts().containsKey(ID));//ensure the id is not in use
        Account account = new Account(ID);
        customers.get(customerId).addAccount(account);
        accounts.put(account.getID(), account);
    }

    public boolean transferBetweenAccounts(int senderId, int receiverId, double amount) {
        if (withdraw(senderId, amount) && deposit(receiverId, amount)) {
            accounts.get(senderId).addTransaction(new TransferTransaction(amount, senderId, receiverId));
            accounts.get(receiverId).addTransaction(new TransferTransaction(amount, senderId, receiverId));
            return true;
        }
        return false;
    }

    public boolean deposit(int accountID, double amount){ //add amount, return true if the transaction is valid, or false if it is invald
        if(amount>0){
            accounts.get(accountID).setBalance(accounts.get(accountID).getBalance() + amount);
            accounts.get(accountID).addTransaction(new DepositTransaction(amount, accountID));
            return true;
        }
        return false;
    }

    public boolean withdraw(int accountID, double amount) {//subtracts amount from balance, returns true for a valid transaction, false for an invalid one
        if (amount > 0 && accounts.get(accountID).getBalance() >= amount) {
            Account account = accounts.get(accountID);
            account.setBalance(account.getBalance() - amount);
            account.addTransaction(new WithdrawalTransaction(amount, accountID));
            return true;
        }
        return false;
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