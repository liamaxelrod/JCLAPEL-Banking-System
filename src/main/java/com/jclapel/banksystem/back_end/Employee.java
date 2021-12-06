package com.jclapel.banksystem.back_end;

public class Employee {
	/*

	PLACEHOLDER

	*/

    private final int ID;
    private String name;

    public Employee(int ID, String name) {
		// Creates employee from ID and name
		// TODO: Avoid duplicate ID!
        this.ID = ID;
        this.name = name;
    }

    public int getID() {
		// Returns the employee's ID
        return ID;
    }

    public String getName() {
		// Returns the employee's name
        return name;
    }

    public void setName(String name) {
		// Sets a new name to the employee's name
        this.name = name;
    }
}
