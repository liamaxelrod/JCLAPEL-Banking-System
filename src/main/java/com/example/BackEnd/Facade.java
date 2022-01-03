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
    public HashMap<Integer, Employee> employees=new HashMap<>();

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

    public Customer loadCustomer(int customerId){ //with a given customer id, return the customer with the same id from customer hashmap
        return customers.get(customerId);
    }

    public Account loadAccount(int accountId){ //same as above
        return accounts.get(accountId);
    }

    public int createCustomer(String name, String password){ //given valid name and password, generate a random id to
        // create a customer with those and put it in the general customers hashmap
        if(validateName(name) && validatePassword(password)) {
            int ID = generateId(customers);
            Customer customer = new Customer(ID, name, password);
            customers.put(ID, customer);
            return ID;
        }
        return 0;
    }//patrik, labi, julia erik

    public int createEmployeeCustomer(String name, String password){ //same as above
        if(validateName(name) && validatePassword(password)) {
            int ID = generateId(customers);
            Customer customer = new EmployeeCustomer(ID, name, password);
            customers.put(ID, customer);
            return ID;
        }
        return 0;
    }

    public boolean validateName(String name){ //Labi  //checks by returning true if the name is non-empty else returns false
        return !name.isEmpty();
    } //Labi

    //more validation on the name, by checking whether it is multiple words,
    // and make it so that if they don't start with a capital, they are changed to start with it when they are saved.
    public String getValidName(String name) {   //Labi, Conny
        name = name.trim();
        String[] nameArray = name.split("\\s+");
        if (nameArray.length < 2) {
            return "";
        }

        String validName = "";
        for (String sub : nameArray) {
            sub = sub.substring(0, 1).toUpperCase() + sub.substring(1).toLowerCase();
            validName += sub + " ";
        }
        validName.trim();
        return validName;
    }


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

    public boolean checkLogin(int ID, String password){ //given id and password, checks if the customer with that id exists and
        // if so checks that if customers password (accesses c.password through customers.get(ID) matches the provided password,
        // returns true if so else false
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
        customers.get(customerId).addAccount(account); //adding an account to the customer with the provided c.id
        accounts.put(account.getID(), account); //putting the account in the customers hashmap for accounts
        return account.getID(); //returning the account id
    } //patrik, labi

    public int createSavingsAccount(int customerId){  //same as above
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
        if (withdraw(senderId, amount) && deposit(receiverId, amount)) {  // if withdraw and deposit is true, add a new transaction to the account with the
            // account id same as sender/receiver id and return boolean value
            accounts.get(senderId).addTransaction(new TransferTransaction(amount, senderId, receiverId));
            accounts.get(receiverId).addTransaction(new TransferTransaction(amount, senderId, receiverId));
            return true;
        }
        return false;
    } //patrik, labi, julia, erik

    public boolean deposit(int accountID, double amount){ //given accountID and amount, retrieves account matching the account id,
        // add amount to accounts balance(also deposit fee) and sets it,
        // add a transaction to the account , return true if the process works else false
        if(amount>0){
            Account account = loadAccount(accountID);
            account.setBalance(account.getBalance() + (amount - loadCustomer(account.getCustomerId()).calculateFee(amount)));
            accounts.get(accountID).addTransaction(new DepositTransaction(amount, accountID));
            return true;
        }
        return false;
    } //patrik, labi, julia, erik

    public boolean withdraw(int accountID, double amount) {//given accountID and amount, retrieves account matching the account id,
        // subtracts amount from balance(also withdrawal fee)and sets it,
        // add a transaction to the account , return true if the process works else false
        if (amount > 0 && accounts.get(accountID).getBalance() >= amount) {
            Account account = accounts.get(accountID);
            WithdrawalTransaction transaction = new WithdrawalTransaction(amount, accountID);
            account.addTransaction(transaction);
            account.setBalance(account.getBalance() - (amount + loadCustomer(account.getCustomerId()).calculateFee(amount)));
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

    public void retrieveUserStatistics(){
        //retrieve data to be displayed by user statistics
    }

    public int createEmployee(String name){
        int ID = generateId(employees);
        Employee employee = new Employee(ID, name);
        employees.put(ID, employee);
        return ID; //changed void to int, returned ID
    }

    public void removeEmployee(int ID){ //given an id, removes the employee matching that id from the hashmap
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
}