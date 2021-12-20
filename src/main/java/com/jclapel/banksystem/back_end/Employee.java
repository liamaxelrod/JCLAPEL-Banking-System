package com.jclapel.banksystem.back_end;

import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.HashMap;
import java.util.Stack;

import static com.jclapel.banksystem.front_end.StartApplication.facade;

public class Employee {
	/*

	Employee class, similiar to customers, for internal handling of the bank.

	Main Contributor(s):
	Contributor(s): 

	*/

	private final int id;
	private String name;
	private HashMap<Integer, Account> accounts = new HashMap<>();

	public Employee(int id, String name) {
		// Creates employee from ID and name
		// TODO: Avoid duplicate ID! 
		this.id = id;
		this.name = name;
	}

	public int getId() {
		// Returns the employee's ID
		return id;
	}

	public String getName() {
		// Returns the employee's name
		return name;
	}

	public void setName(String name) {
		// Sets a new name to the employee's name
		this.name = name;
	}

	public HashMap<Integer, Account> getAccounts(){
        return accounts;
    }
}
