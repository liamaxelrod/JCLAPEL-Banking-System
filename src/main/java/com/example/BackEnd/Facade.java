package com.example.BackEnd;

import java.util.HashMap;
import java.util.Random;
import java.util.Stack;

public class Facade {
    HashMap<Integer, Customer> customers = new HashMap<>();
    private HashMap<Integer, Account> accounts = new HashMap<>();
    private HashMap<Integer, Employee> employees=new HashMap<>();
    final static String customersOutputFilePath = "F:/Serialisation/customers.txt";
    final static String accountsOutputFilePath = "F:/Serialisation/accounts.txt";
    final static String employeesOutputFilePath = "F:/Serialisation/employees.txt";
    File customersFile = new File(customersOutputFilePath);
    File accountsFile = new File(accountsOutputFilePath);
    File employeesFile = new File(employeesOutputFilePath);

    public void storeData() { //stores data to file when the app is closed.
        try {
            FileOutputStream customersOutput = new FileOutputStream(customersFile);
            FileOutputStream accountsOutput = new FileOutputStream(accountsFile);
            FileOutputStream employeesOutput = new FileOutputStream(employeesFile);

            ObjectOutputStream customersStream = new ObjectOutputStream(customersOutput);
            ObjectOutputStream accountsStream = new ObjectOutputStream(accountsOutput);
            ObjectOutputStream employeesStream = new ObjectOutputStream(employeesOutput);

            customersStream.writeObject(customers);
            accountsStream.writeObject(accounts);
            employeesStream.writeObject(employees);

            customersStream.close();
            accountsStream.close();
            employeesStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } // Done by Julia Ayvazian, temporary solution until the database works

    public void loadData() { //loads all data from file when the app is opened.
        try {
            FileInputStream customersInput = new FileInputStream(customersFile);
            FileInputStream accountsInput = new FileInputStream(accountsFile);
            FileInputStream employeesInput = new FileInputStream(employeesFile);

            ObjectInputStream customersStream = new ObjectInputStream(customersInput);
            ObjectInputStream accountsStream = new ObjectInputStream(accountsInput);
            ObjectInputStream employeesStream = new ObjectInputStream(employeesInput);

            customers = (HashMap<Integer, Customer>) customersStream.readObject();
            accounts = (HashMap<Integer, Account>) accountsStream.readObject();
            employees = (HashMap<Integer, Employee>) employeesStream.readObject();

            customersStream.close();
            accountsStream.close();
            employeesStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    } // Done by Julia Ayvazian, temporary solution until the database works


    public Customer loadCustomer(int customerId){
        return customers.get(customerId);
    }

    public int createCustomer(String name, String password){ //Patrik, Karar , Julia, Erik, returns customer id;
        int ID = generateId(customers);
        Customer customer = new Customer(ID, name, password);
        customers.put(ID, customer);
        return ID;
    }

    public int CheckIfCustomerExists(int ID){
        if(customers.containsKey(ID)){
            return ID;
        }else{
            return 0;
        }
    }

    public void removeCustomer(int ID){
        customers.remove(ID);
    }

    public boolean checkLogin(int ID, String password){
        if(customers.containsKey(ID)){
            if(customers.get(ID).getPassword().equals(password)) {
                //checks if the id exists, and the password of the customer with that id is equal to the input password
                return true;
            }
        }
        return false;
    }

    public void createAccount(int customerId){ // adds an account to a given customer
        Account account = new Account(generateId(customers.get(customerId).getAccounts()), false);
        customers.get(customerId).addAccount(account);
        accounts.put(account.getID(), account);
    }

    public void createSavingsAccount(int customerId){
        Account account = new Account(generateId(customers.get(customerId).getAccounts()), true);
        customers.get(customerId).addAccount(account);
        accounts.put(account.getID(), account);
        return account.getID();
    }

    public void removeAccount(int accountID){
        accounts.remove(accountID);
    }

    public boolean CheckIfAccountExists(int ID){
        if(accounts.containsKey(ID)){
            return true;
        }else{
            return false;
        }
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
            accounts.get(accountID).setBalance(accounts.get(accountID).getBalance() + amount*0.99);
            accounts.get(accountID).addTransaction(new DepositTransaction(amount, accountID));
            return true;
        }
        return false;
    }

    public boolean withdraw(int accountID, double amount) {//subtracts amount from balance, returns true for a valid transaction, false for an invalid one
        if (amount > 0 && accounts.get(accountID).getBalance() >= amount) {
            Account account = accounts.get(accountID);
            account.setBalance(account.getBalance() - amount*1.01);
            account.addTransaction(new WithdrawalTransaction(amount, accountID));
            return true;
        }
        return false;
    }

    public Stack<Transaction> loadAllTransactions(int accountID){
         return accounts.get(accountID).getTransactions();
    }

    public boolean resetPassword(int customerId, String originalPassword, String newPassword){ //returns a boolean indicating whether the change went through
        if(checkLogin(customerId, originalPassword)) {
            Customer customer = customers.get(customerId);
            customer.setPassword(newPassword);
            return true;
        }
        return false;
    }

    public void retrieveUserStatistics(){
        //retrieve data to be displayed by user statistics
    }

    public void createEmployee(String name){
        int ID = generateId(employees);
        Employee employee = new Employee(ID, name);
        employees.put(ID, employee);
    }

    public int generateId(HashMap hashMap){ //takes the hashmap in which the resulting object will be stored as an argument
        int ID;
        Random rn = new Random();
        do{ //generate random ID number
            int range = 999999 - 100000 +1; //generate 6 digit random number
            ID = rn.nextInt(range) + 100000;
        } while (hashMap.containsKey(ID));//ensure the id is not in use
        return ID;
    }
}