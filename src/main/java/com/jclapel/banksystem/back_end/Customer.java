package com.jclapel.banksystem.back_end;

import java.util.ArrayList;
import java.util.HashMap;

import org.bson.codecs.pojo.annotations.BsonProperty;

public class Customer {
	/*

	Customer class, intended to hold unique IDs, a name and password

	Main Contributor(s):
	Contributor(s):

	*/
	@BsonProperty(value = "customer_id")
	private final int id;
	private String name;
	private String password;
	private HashMap<Integer, Account> accounts = new HashMap<>(); // TODO: Considering whether a List should be used instead of a HashMap, you can get the ID from account

	public Customer(int id, String name, String password) {
		// Constructor for customer, creates a customer account with unique ID, a name and password
		this.id = id;
		this.name = name;
		this.password = password;
	}

	public double calculateFee(double amount) {
		// Calculates the fee for...?
		return amount * 0.01;
	}

	public void addAccount(Account account) {
		// Adds account to customer
		accounts.put(account.getId(), account);
	}

	public int getId() {
		// Returns the customer's ID
		return id;
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
