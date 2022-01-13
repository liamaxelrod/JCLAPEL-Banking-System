package com.example.BackEnd;

import java.io.*;
//import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Stack;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import static com.mongodb.client.model.Filters.eq;

public class Facade {
    //ublic HashMap<Integer, Customer> customers = new HashMap<>();
    //public HashMap<Integer, Account> accounts = new HashMap<>();
    //public HashMap<Integer, Account> employeeAccounts = new HashMap<>();
    //public HashMap<Integer, Employee> employees=new HashMap<>();
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

    /*public void storeData() { //stores data to file when the app is closed.
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
    } // Done by Julia Ayvazian, temporary solution until the database works     */

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

    public int createCustomer(String name, String password){ //given valid name and password, generate a random id to
        // create a customer with those and put it in the general customers hashmap
        MongoCollection<Document> customers = database.getCollection("customers");
        if(validateName(name) && validatePassword(password)) {
            int ID = generateId(customers);
            //Customer customer = new Customer(ID, name, password);
            Document customer = new Document();
            customer.append("ID", ID)
                    .append("name", name)
                    .append("password", password)
                    .append("accounts", new HashMap<Integer, Account>());
            customers.insertOne(customer);
            //customers.put(ID, customer);
            return ID;
        }
        return 0;
    }//patrik, labi, julia erik

    public int createEmployeeCustomer(String name, String password){ //same as above
        if(validateName(name) && validatePassword(password)) {
            MongoCollection<Document> customers = database.getCollection("customers");
            int ID = generateId(customers);
            Customer customer = new EmployeeCustomer(ID, name, password);
            //customers.put(ID, customer);
            Document employeeCustomer = new Document();
            employeeCustomer.append("ID", ID)
                    .append("name", name)
                    .append("password", password);
            customers.insertOne(employeeCustomer);
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

    /*public boolean CheckIfCustomerExists(int ID){
        if(customers.containsKey(ID)){
            return true;
        }else{
            return false;
        }
    } //Erik

     */

    public void removeCustomer(int ID){
        MongoCollection<Document> customers = database.getCollection("customers");
        customers.deleteOne(Filters.eq("ID", ID));
    }

    public boolean checkLogin(int ID, String password){ //given id and password, checks if the customer with that id exists and
        // if so checks that if customers password (accesses c.password through customers.get(ID) matches the provided password,
        // returns true if so else false
            if(loadCustomer(ID).getPassword().equals(password)) {
                //checks if the id exists, and the password of the customer with that id is equal to the input password
                return true;
            }
        return false;
    } //patrik, labi, julia

    public int createAccount(int customerId){ // adds an account to a given customer
        //Account account = new Account(generateId(customers.get(customerId).getAccounts()), customerId, false);
        //customers.get(customerId).addAccount(account);
        //accounts.put(account.getID(), account);
        MongoCollection<Document> customers = database.getCollection("accounts");
        int ID = generateId(customers);
        Document customer = new Document();
        customer.append("ID", ID)
                .append("customerId", customerId)
                .append("isSavings", false);
        customers.insertOne(customer);
        return ID;
    } //patrik, labi

    public int createSavingsAccount(int customerId){
        MongoCollection<Document> customers = database.getCollection("accounts");
        int ID = generateId(customers);
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

    /*public boolean CheckIfAccountExists(int ID){
        if(accounts.containsKey(ID)){
            return true;
        }else{
            return false;
        }
    } //Erik

     */

    public boolean transferBetweenAccounts(int senderId, int receiverId, double amount) {
        if (withdraw(senderId, amount) && deposit(receiverId, amount)) {  // if withdraw and deposit is true, add a new transaction to the account with the
            // account id same as sender/receiver id and return boolean value
            loadAccount(senderId).addTransaction(new TransferTransaction(amount, senderId, receiverId));
            loadAccount(receiverId).addTransaction(new TransferTransaction(amount, senderId, receiverId));
            return true;
        }
        return false;
    } //patrik, labi, julia, erik

    public boolean deposit(int accountID, double amount){ //given accountID and amount, retrieves account matching the account id,
        // add amount to accounts balance(also deposit fee) and sets it,
        // add a transaction to the account , return true if the process works else false
        if(amount>0){
            Account account = loadAccount(accountID);
            double amountWithFee = (amount - loadCustomer(account.getCustomerId()).calculateFee(amount));
            account.setBalance(account.getBalance() + (amountWithFee));
            loadAccount(accountID).addTransaction(new DepositTransaction(amountWithFee, accountID));
            return true;
        }
        return false;
    } //patrik, labi, julia, erik

    public boolean withdraw(int accountID, double amount) {//subtracts amount from balance, returns true for a valid transaction, false for an invalid one
        if (amount > 0 && loadAccount(accountID).getBalance() > amount) {
            Account account = loadAccount(accountID);
            double amountWithFee = (amount + loadCustomer(account.getCustomerId()).calculateFee(amount));
            WithdrawalTransaction transaction = new WithdrawalTransaction(amountWithFee, accountID);
            account.addTransaction(transaction);
            account.setBalance(account.getBalance() - amountWithFee);
            return true;
        }
        return false;
    } //patrik, labi, julia, erik

    public Stack<Transaction> loadAllTransactions(int accountID){
         return loadAccount(accountID).getTransactions();
    } //Erik

    public boolean resetPassword(int customerId, String originalPassword, String newPassword){ //returns a boolean indicating whether the change went through
        if(checkLogin(customerId, originalPassword) && validatePassword(newPassword)) {
            Customer customer = loadCustomer(customerId);
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
        //int ID = generateId(employees);
        //Employee employee = new Employee(ID, name);
        //employees.put(ID, employee);
        //return ID; //changed void to int, returned ID
        MongoCollection<Document> employees = database.getCollection("employees");
        int ID = generateId(employees);
        //Customer customer = new Customer(ID, name, password);
        if(validatePassword(password)) {
            Document employee = new Document();
            employee.append("ID", ID)
                    .append("name", name)
                    .append("password", password);

            employees.insertOne(employee);
            //customers.put(ID, customer);
            return ID;
        } else {
            return 0;
        }
    }

    public void removeEmployee(int ID){
        MongoCollection<Document> employees = database.getCollection("employees");
        employees.deleteOne(Filters.eq("ID", ID));
    } //Erik and Labi

    public Employee loadEmployee(int ID){
        MongoCollection<Document> employees = database.getCollection("employees");
        Document employee = employees.find(eq("ID", ID)).first();
        return new Employee(employee.getInteger("ID"), employee.getString("name"), employee.getString("password"));
    }  //Labi

    public int createEmployeeAccount(int employeeID){ // adds an account to a given customer
        Account account = new Account(generateId(database.getCollection("accounts")), employeeID, false);
        loadCustomer(employeeID).addAccount(account);
        //accounts.put(account.getID(), account);
        return account.getID();

    } //patrik, labi

    public int generateId(MongoCollection hashMap){ //takes the hashmap in which the resulting object will be stored as an argument
        int ID;
        Random rn = new Random();
        do{ //generate random ID number
            int range = 999999 - 100000 +1; //generate 6 digit random number
            ID = rn.nextInt(range) + 100000;
        } while (hashMap.find(eq("ID", ID)).first() != null);//ensure the id is not in use
        return ID;
    } //patrik

    public HashMap<Integer, Account> getAllAccounts(){
        HashMap<Integer, Account> hashMap = new HashMap<>();
        MongoCollection<Document> accounts = database.getCollection("accounts");
        FindIterable<Document> allAccounts = accounts.find();
        for(Document account : allAccounts){
            int accountId = account.getInteger("ID");
            hashMap.put(accountId, loadAccount(accountId));
        }
        return hashMap;
    }

    public HashMap<Integer, Account> getCustomerAccounts(int customerID){
        HashMap<Integer, Account> hashMap = new HashMap<>();
        MongoCollection<Document> accounts = database.getCollection("accounts");
        FindIterable<Document> allAccounts = accounts.find(eq("customerId", customerID));
        for(Document account : allAccounts){
            int accountId = account.getInteger("ID");
            hashMap.put(accountId, loadAccount(accountId));
        }
        return hashMap;
    }

    public HashMap<Integer, Customer> getAllCustomers(){
        HashMap<Integer, Customer> hashMap = new HashMap<>();
        MongoCollection<Document> customers = database.getCollection("customers");
        FindIterable<Document> allCustomers = customers.find();
        for(Document customer : allCustomers){
            int customerId = customer.getInteger("ID");
            hashMap.put(customerId, loadCustomer(customerId));
        }
        return hashMap;
    }

    public HashMap<Integer, Employee> getAllEmployees(){
        HashMap<Integer, Employee> hashMap = new HashMap<>();
        MongoCollection<Document> employees = database.getCollection("employees");
        FindIterable<Document> allEmployees = employees.find();
        for(Document employee : allEmployees){
            int employeeId = employee.getInteger("ID");
            hashMap.put(employeeId, loadEmployee(employeeId));
        }
        return hashMap;
    }

    /*public int createManager(String name, String password){
        if(validateName(name) && validatePassword(password)){
            MongoCollection<Document> employees = database.getCollection("employees");
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
    }*/
}