package com.jclapel.banksystem.back_end;

public class EmployeeCustomer extends Customer {
    /*

	Employee customer class, a variant of customer that is suited for employees. They get less fees.

	Main Contributor(s): 
	Contributor(s): 

	*/	
    public EmployeeCustomer(int id, String name, String password) {
        // Creates an employee customer object
        super(id, name, password);
    }

    @Override
    public double calculateFee(double amount) {
        // Overrides the fee of the previous method
        return amount * 0.005;
    }
}
