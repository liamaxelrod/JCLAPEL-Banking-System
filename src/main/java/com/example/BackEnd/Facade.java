package com.example.BackEnd;

import java.io.*;
//import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.HashMap;
import java.util.Random;
import java.util.Stack;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import static com.mongodb.client.model.Filters.eq;

public class Facade {
    public HashMap<Integer, Customer> customers = new HashMap<>();
    public HashMap<Integer, Account> accounts = new HashMap<>();
    public HashMap<Integer, Account> employeeAccounts = new HashMap<>();
    public HashMap<Integer, Employee> employees=new HashMap<>();
    MongoDatabase database;

    final static String customersOutputFilePath = "F:/Serialisation/customers.txt";
    final static String accountsOutputFilePath = "F:/Serialisation/accounts.txt";
    final static String employeesOutputFilePath = "F:/Serialisation/employees.txt";
    File customersFile = new File(customersOutputFilePath); //File to save customer data
    File accountsFile = new File(accountsOutputFilePath); //File to save account data
    File employeesFile = new File(employeesOutputFilePath); //File to save employee data

    public Facade(){
        try{
            MongoClient client = MongoClients.create();
            database = client.getDatabase("database");
            //MongoClient account = (MongoClient) client.getDatabase("account");
            //MongoClient employees = (MongoClient) client.getDatabase("employees");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

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
        MongoCollection<Document> customers = database.getCollection("customers");
        //Document customer = collection.find().first();
        Document customer = customers.find(eq("ID", customerId)).first();
        return new Customer(customer.getInteger("ID"), customer.getString("name"), customer.getString("password"));
        //return customers.get(customerId);
    }

    public Account loadAccount(int accountId){
        MongoCollection<Document> accounts = database.getCollection("accounts");
        Document account = accounts.find(eq("ID", accountId)).first();
        return new Account(account.getInteger("ID"), account.getInteger("customerId"), account.getBoolean("isSavings"));
        //return accounts.get(accountId);
    }

    public int createCustomer(String name, String password){
        if(validateName(name) && validatePassword(password)) {
            int ID = generateId(customers);
            //Customer customer = new Customer(ID, name, password);
            Document customer = new Document();
            customer.append("ID", ID)
                    .append("name", name)
                    .append("password", password);
            MongoCollection<Document> customers = database.getCollection("customers");
            customers.insertOne(customer);
            //customers.put(ID, customer);
            return ID;
        }
        return 0;
    }//patrik, labi, julia erik

    public int createEmployeeCustomer(String name, String password){
        if(validateName(name) && validatePassword(password)) {
            int ID = generateId(customers);
            Customer customer = new EmployeeCustomer(ID, name, password);
            customers.put(ID, customer);
            Document employeeCustomer = new Document();
            employeeCustomer.append("ID", ID)
                    .append("name", name)
                    .append("password", password);
            MongoCollection<Document> customers = database.getCollection("customers");
            customers.insertOne(employeeCustomer);
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
        MongoCollection<Document> customers = database.getCollection("customers");
        customers.deleteOne(Filters.eq("ID", ID));
    }

    public boolean checkLogin(int ID, String password){
        //if(customers.containsKey(ID)){
            if(loadCustomer(ID).getPassword().equals(password)) {
                //checks if the id exists, and the password of the customer with that id is equal to the input password
                return true;
            }
        //}
        return false;
    } //patrik, labi, julia

    public int createAccount(int customerId){ // adds an account to a given customer
        //Account account = new Account(generateId(customers.get(customerId).getAccounts()), customerId, false);
        //customers.get(customerId).addAccount(account);
        //accounts.put(account.getID(), account);
        MongoCollection<Document> customers = database.getCollection("accounts");
        int ID = generateId((HashMap) customers);
        Document customer = new Document();
        customer.append("ID", ID)
                .append("customerId", customerId)
                .append("isSavings", false);
        customers.insertOne(customer);
        return ID;
    } //patrik, labi

    public int createSavingsAccount(int customerId){
        MongoCollection<Document> customers = database.getCollection("accounts");
        int ID = generateId((HashMap) customers);
        Document customer = new Document();
        customer.append("ID", ID)
                .append("customerId", customerId)
                .append("isSavings", true);
        customers.insertOne(customer);
        return ID;
    } //patrik, labi

    public void removeAccount(int accountID){
        MongoCollection<Document> accounts = database.getCollection("accounts");
        accounts.deleteOne(Filters.eq("ID", accountID));
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
            account.setBalance(account.getBalance() + (amount - loadCustomer(account.getCustomerId()).calculateFee(amount)));
            accounts.get(accountID).addTransaction(new DepositTransaction(amount, accountID));
            return true;
        }
        return false;
    } //patrik, labi, julia, erik

    public boolean withdraw(int accountID, double amount) {//subtracts amount from balance, returns true for a valid transaction, false for an invalid one
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
        //int ID = generateId(employees);
        //Employee employee = new Employee(ID, name);
        //employees.put(ID, employee);
        //return ID; //changed void to int, returned ID

        int ID = generateId(employees);
        //Customer customer = new Customer(ID, name, password);
        Document employee = new Document();
        employee.append("ID", ID)
                .append("name", name);
        MongoCollection<Document> customers = database.getCollection("employees");
        customers.insertOne(employee);
        //customers.put(ID, customer);
        return ID;
    }

    public void removeEmployee(int ID){
        MongoCollection<Document> employees = database.getCollection("employees");
        employees.deleteOne(Filters.eq("ID", ID));
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