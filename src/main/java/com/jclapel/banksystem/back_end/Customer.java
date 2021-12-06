package com.jclapel.banksystem.back_end;

import java.util.ArrayList;
import java.util.HashMap;

public class Customer {
	/*

	Customer class, intended to hold unique IDs, a name and password

	*/

	private final int ID;
	private String name;
	private String password;
	private HashMap<Integer, Account> accounts = new HashMap<>();

	public Customer(int ID, String name, String password) {
		// Constructor for customer, creates a customer account with unique ID, a name and password
		// TODO: No duplicate IDs should exist within the system.
		this.ID = ID;
		this.name = name;
		this.password = password;
	}

	public void addAccount(Account account) {
		// Adds account to customer
		accounts.put(account.getID(), account);
	}

	public int getID() {
		// Returns the customer's ID
		return ID;
	}

	public String getName() {
		// Returns the customer's name
		return name;
	}

	public String getPassword() {
		// Returns the customer's password
		return password;
	}

	public void setName(String name) {
		// Sets the customer's name
		this.name = name;
	}
	
	public void setPassword(String password) {
		// Sets the customer's password
		this.password = password;
	}
	
	public HashMap<Integer, Account> getAccounts() {
		// Returns the map of accounts from the customer
		return accounts;
	}
}
