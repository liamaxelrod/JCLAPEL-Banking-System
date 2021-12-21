package com.example.BackEnd;

import java.io.*;
import java.util.HashMap;
import java.util.Random;
import java.util.Stack;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Facade {
    public HashMap<Integer, Customer> customers = new HashMap<>();
    public HashMap<Integer, Account> accounts = new HashMap<>();
    public HashMap<Integer, Account> employeeAccounts = new HashMap<>();
    public HashMap<Integer, Employee> employees = new HashMap<>();

    final static String customersOutputFilePath = "F:/Serialisation/customers.txt";
    final static String accountsOutputFilePath = "F:/Serialisation/accounts.txt";
    final static String employeesOutputFilePath = "F:/Serialisation/employees.txt";
    File customersFile = new File(customersOutputFilePath); //File to save customer data
    File accountsFile = new File(accountsOutputFilePath); //File to save account data
    File employeesFile = new File(employeesOutputFilePath); //File to save employee data

    public void storeData() { //stores data to file when the app is closed.
        try {
            FileOutputStream customersOutput = new FileOutputStream(customersFile);
            FileOutputStream accountsOutput = new FileOutputStream(accountsFile);
            FileOutputStream employeesOutput = new FileOutputStream(employeesFile);

            ObjectOutputStream customersStream = new ObjectOutputStream(customersOutput); //Allows to store objects into file
            ObjectOutputStream accountsStream = new ObjectOutputStream(accountsOutput);
            ObjectOutputStream employeesStream = new ObjectOutputStream(employeesOutput);

            customersStream.writeObject(customers); //Actually storing the data to file
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

            ObjectInputStream customersStream = new ObjectInputStream(customersInput); //Object input stream allows to read objects from file
            ObjectInputStream accountsStream = new ObjectInputStream(accountsInput);
            ObjectInputStream employeesStream = new ObjectInputStream(employeesInput);

            customers = (HashMap<Integer, Customer>) customersStream.readObject(); //Loading the hashmap from the file
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

    public Account loadAccount(int accountId){
        return accounts.get(accountId);
    }

    public int createCustomer(String name, String password){
        if(validateName(name) && validatePassword(password)) {
            int ID = generateId(customers);
            Customer customer = new Customer(ID, name, password);
            customers.put(ID, customer);
            return ID;
        }
        return 0;
    }//patrik, labi, julia erik

    public int createEmployeeCustomer(String name, String password){
        if(validateName(name) && validatePassword(password)) {
            int ID = generateId(customers);
            Customer customer = new EmployeeCustomer(ID, name, password);
            customers.put(ID, customer);
            return ID;
        }
        return 0;
    }

    public boolean validateName(String name){ //Labi
        return !name.isEmpty();
    } //Labi

    public boolean validatePassword(String password){ //Julia
        Pattern checkPattern = Pattern.compile("[^a-zA-Z0-9]"); //regex, checks everything that is not a special case character
        Pattern checkNumberPattern = Pattern.compile("[0-9]"); //check numbers
        Matcher match = checkPattern.matcher(password);
        Matcher matchNumber = checkNumberPattern.matcher(password);
        if(!password.isEmpty() && password.length() >= 8 && !password.toUpperCase().equals(password) && !password.toLowerCase().equals(password) && match.find() && matchNumber.find()){
            return true;
        }else{
            return false;
        }
    }

    public boolean CheckIfCustomerExists(int ID){
        if(customers.containsKey(ID)){
            return true;
        }else{
            return false;
        }
    } //Erik

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
    } //patrik, labi, julia

    public int createAccount(int customerId){ // adds an account to a given customer
        Account account = new Account(generateId(customers.get(customerId).getAccounts()), customerId, false);
        loadCustomer(customerId).addAccount(account);
        accounts.put(account.getID(), account);
        return account.getID();
    } //patrik, labi

    public int createSavingsAccount(int customerId){
        Account account = new Account(generateId(customers.get(customerId).getAccounts()), customerId, true);
        customers.get(customerId).addAccount(account);
        accounts.put(account.getID(), account);
        return account.getID();
    } //patrik, labi

    public void removeAccount(int accountID){
        accounts.remove(accountID);
    } //Erik

    public boolean CheckIfAccountExists(int ID){
        if(accounts.containsKey(ID)){
            return true;
        }else{
            return false;
        }
    } //Erik

    public boolean transferBetweenAccounts(int senderId, int receiverId, double amount) {
        if (withdraw(senderId, amount) && deposit(receiverId, amount)) {
            accounts.get(senderId).addTransaction(new TransferTransaction(amount, senderId, receiverId));
            accounts.get(receiverId).addTransaction(new TransferTransaction(amount, senderId, receiverId));
            return true;
        }
        return false;
    } //patrik, labi, julia, erik

    public boolean deposit(int accountID, double amount){ //add amount, return true if the transaction is valid, or false if it is invald
        if(amount>0){
            Account account = loadAccount(accountID);
            double amountWithFee = (amount - loadCustomer(account.getCustomerId()).calculateFee(amount));
            account.setBalance(account.getBalance() + (amountWithFee));
            accounts.get(accountID).addTransaction(new DepositTransaction(amountWithFee, accountID));
            return true;
        }
        return false;
    } //patrik, labi, julia, erik

    public boolean withdraw(int accountID, double amount) {//subtracts amount from balance, returns true for a valid transaction, false for an invalid one
        if (amount > 0 && accounts.get(accountID).getBalance() > amount) {
            Account account = accounts.get(accountID);
            double amountWithFee = (amount + loadCustomer(account.getCustomerId()).calculateFee(amount));
            WithdrawalTransaction transaction = new WithdrawalTransaction(amountWithFee, accountID);
            account.addTransaction(transaction);
            account.setBalance(account.getBalance() - amountWithFee);
            return true;
        }
        return false;
    } //patrik, labi, julia, erik

    public Stack<Transaction> loadAllTransactions(int accountID){
         return accounts.get(accountID).getTransactions();
    } //Erik

    public boolean resetPassword(int customerId, String originalPassword, String newPassword){ //returns a boolean indicating whether the change went through
        if(checkLogin(customerId, originalPassword) && validatePassword(newPassword)) {
            Customer customer = customers.get(customerId);
            customer.setPassword(newPassword);
            return true;
        }
        return false;
    } // By Julia Ayvazian

    public Report generateReport(int customerID, int employeeId) {
        //load employee
        Employee employee = loadEmployee(employeeId);
        if (employee instanceof Admin) {
            return new AdminReport(customerID);
        }else if (employee instanceof Manager){
            return new ManagerReport(customerID);
        }else{
            return new EmployeeReport(customerID);
        }
    }

    public int createEmployee(String name, String password){
        int ID = generateId(employees);
        if(validateName(name) && validatePassword(password)) {
            Employee employee = new Employee(ID, name, password);
            employees.put(ID, employee);
            return ID; //changed void to int, returned ID
        } else {
            return 0;
        }
    }

    public void removeEmployee(int ID){
        employees.remove(ID);
    } //Erik and Labi

    public Employee loadEmployee(int ID){
        return employees.get(ID);
    }  //Labi

    public int createEmployeeAccount(int employeeID){ // adds an account to a given customer
        Account account = new Account(generateId(employees.get(employeeID).getAccounts()), employeeID, false);
        customers.get(employeeID).addAccount(account);
        accounts.put(account.getID(), account);
        return account.getID();

    } //patrik, labi

    public int generateId(HashMap hashMap){ //takes the hashmap in which the resulting object will be stored as an argument
        int ID;
        Random rn = new Random();
        do{ //generate random ID number
            int range = 999999 - 100000 +1; //generate 6 digit random number
            ID = rn.nextInt(range) + 100000;
        } while (hashMap.containsKey(ID));//ensure the id is not in use
        return ID;
    } //patrik

    public int createManager(String name, String password){
        if(validateName(name) && validatePassword(password)){
            int ID= generateId(employees);
            Employee manager = new Employee(ID,name, password);
            employees.put(ID,manager);
            return ID;
        }else{
            return 0;
        }
    } //Labi and Erik
    //Julia
    public int createAdmin(String name, String password){
        if(validateName(name) && validatePassword(password)){
            int ID = generateId(employees);
            Employee admin = new Admin(ID, name, password);
            employees.put(ID,admin);
            return ID;
        }else{
            return 0;
        }
    }
}