package com.jclapel.banksystem.back_end;

import java.io.*;
import java.util.HashMap;
import java.util.Random;
import java.util.Stack;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import com.jclapel.banksystem.data.*;

public class Facade {
	/*

	Facade class, simply the whole "bottleneck" of the entire software between front-end and back-end.

	Main Contributor(s):
	Contributors: Patrik, Labi, Julia, Erik

	*/

	public HashMap<Integer, Customer> customers = new HashMap<>();
	public HashMap<Integer, Account> accounts = new HashMap<>();
	public HashMap<Integer, Account> employeeAccounts = new HashMap<>();
	public HashMap<Integer, Employee> employees = new HashMap<>();

	final static String customersOutputFilePath = "F:/Serialisation/customers.txt";
	final static String accountsOutputFilePath = "F:/Serialisation/accounts.txt";
	final static String employeesOutputFilePath = "F:/Serialisation/employees.txt";

	Cache cache = new Cache();

	File customersFile = new File(customersOutputFilePath); //File to save customer data
	File accountsFile = new File(accountsOutputFilePath); //File to save account data
	File employeesFile = new File(employeesOutputFilePath); //File to save employee data

	private int generateId(HashMap<?, ?> hashMap) {
		// Generates a random six-digit number as an ID for a hashmap, if the ID already exists, retry until successful 
		int id;
		Random rn = new Random();
		do {
			int range = 999999 - 100000 +1; 
			id = rn.nextInt(range) + 100000;
		} while (hashMap.containsKey(id));
		return id;
	} // Patrik

	public void storeData() {
		// Stores data to file when the application is closed
		// TODO: Connecting this with Cache.java eventually
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

	public void loadData() { 
		// Loads all data from file when the application is opened
		// TODO: Connecting this with Cache.java eventually, but also resolve angry warnings(type safety)
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

	public Customer loadCustomer(int customerId) {
		// Loads customer from customer ID
		// TODO: Choice of getCustomer or loadCustomer, former is probably conventional
		return customers.get(customerId);
	}

	public int createCustomer(String name, String password) {
		// Creates a customer with name and password and returns the ID
		if (validateName(name) && validatePassword(password)) {
			int customerId = generateId(customers);
			Customer customer = new Customer(customerId, name, password);
			customers.put(customerId, customer);
			return customerId;
		}
		return 0;
	} // Patrik, Labi, Julia, Erik

	public int createEmployeeCustomer(String name, String password) {
		if (validateName(name) && validatePassword(password)) {
			int id = generateId(customers);
			Customer customer = new EmployeeCustomer(id, name, password);
			customers.put(id, customer);
			return id;
		}
		return 0;
	}

	public int customerExists(int customerId) {
		// Checks if the customer exists from ID
		// TODO: Consideration of booleans or not.
		if (customers.containsKey(customerId)) {
			return customerId;
		} else {
			return 0;
		}
	} // Erik

	public void removeCustomer(int customerId) {
		// Removes a customer from ID
		customers.remove(customerId);
	}

	public boolean checkLogin(int customerId, String password) {
		// Checks if the ID exists, and the password of the customer with that ID is equal to the input password
		if (customers.containsKey(customerId)) {
			if (customers.get(customerId).getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	} // Patrik, Labi, Julia

	public boolean validateName(String name) { 
		// Validates if the name is valid
		return !name.isEmpty();
	} // Labi

	public boolean validatePassword(String password) { //Julia
		// Validates if the password is valid by a specific pattern
		Pattern checkPattern = Pattern.compile("[^a-zA-Z0-9]"); //regex, checks everything that is not a special case character
		Pattern checkNumberPattern = Pattern.compile("[0-9]"); //check numbers
		Matcher match = checkPattern.matcher(password);
		Matcher matchNumber = checkNumberPattern.matcher(password);
		if (!password.isEmpty() && password.length() >= 8 && !password.toUpperCase().equals(password) && !password.toLowerCase().equals(password) && match.find() && matchNumber.find()){
			return true;
		} else {
			return false;
		}
	}

	public Account loadAccount(int accountId){
        return accounts.get(accountId);
    }

	public int createAccount(int customerId) {
		// Creates and adds an account to a given customer from ID
		Account account = new Account(generateId(customers.get(customerId).getAccounts()), customerId, false);
		customers.get(customerId).addAccount(account);
		accounts.put(account.getId(), account);
		return account.getId();
	} // Patrik, Labi

	public int createSavingsAccount(int customerId) {
		// Creates and adds a savings account to a given customer from ID
		Account account = new Account(generateId(customers.get(customerId).getAccounts()), customerId, true);
		customers.get(customerId).addAccount(account);
		accounts.put(account.getId(), account);
		return account.getId();
	} // Patrik, Labi

	public void removeAccount(int accountId) {
		// Removes an account from account ID
		accounts.remove(accountId);
	} // Erik

	public boolean accountExists(int accountId) {
		// Checks if an account exists from ID
		if (accounts.containsKey(accountId)) {
			return true;
		} else {
			return false;
		}
	} // Erik

	public boolean transfer(int senderId, int receiverId, double amount) {
		// Creates a transfer transaction from sender to receiver with an amount
		// TODO: Catch the scenario of withdrawal successful BUT deposit failing!
		if (withdraw(senderId, amount) && deposit(receiverId, amount)) {
			accounts.get(senderId).addTransaction(new TransferTransaction(amount, senderId, receiverId));
			accounts.get(receiverId).addTransaction(new TransferTransaction(amount, senderId, receiverId));
			return true;
		}
		return false;
	} // Patrik, Labi, Julia, Erik

	public boolean deposit(int accountId, double amount) { 
		// Creates a deposit to an account, returns a boolean for validity
		if (amount > 0) {
			accounts.get(accountId).setBalance(accounts.get(accountId).getBalance() + amount * 0.99);
			accounts.get(accountId).addTransaction(new DepositTransaction(amount, accountId));
			return true;
		}
		return false;
	} // Patrik, Labi, Julia, Erik

	public boolean withdraw(int accountId, double amount) {
		// Creates a withdrawal from an account, returns a boolean for validity
		if (amount > 0 && accounts.get(accountId).getBalance() >= amount) {
			Account account = accounts.get(accountId);
			account.setBalance(account.getBalance() - amount * 1.01); // WARNING: Amount * 1.01 may exceed to negative numbers!
			account.addTransaction(new WithdrawalTransaction(amount, accountId));
			return true;
		}
		return false;
	} // Patrik, Labi, Julia, Erik

	public Stack<Transaction> loadAllTransactions(int accountId) {
		// Returns a stack of transactions from an account
		 return accounts.get(accountId).getTransactions();
	}

	public boolean resetPassword(int customerId, String originalPassword, String newPassword) { 
		// Returns a boolean for whether the reset was successful
		if (checkLogin(customerId, originalPassword)) {
			Customer customer = customers.get(customerId);
			customer.setPassword(newPassword);
			return true;
		}
		return false;
	} // By Julia Ayvazian

	public void retrieveUserStatistics() {
		// Returns data to be displayed for user statistics
	}

	public int createEmployee(String name) {
		// Creates an employee with a name
		int employeeId = generateId(employees);
		Employee employee = new Employee(employeeId, name);
		employees.put(employeeId, employee);

		return employeeId;
	}

	public void removeEmployee(int id) {
		// Removes an employee associated with the employee ID
		employees.remove(id);
	} // Erik and Labi

	public Employee loadEmployee(int id) {
		// Returns the employee associated with the employee ID
		return employees.get(id);
	} //Labi

	public int createEmployeeAccount(int employeeId) { 
		// Adds an account to a given employee
		Account account = new Account(generateId(employees.get(employeeId).getAccounts()), employeeId, false);
		customers.get(employeeId).addAccount(account);
		accounts.put(account.getId(), account);
		return account.getId();
	} //patrik, labi
}