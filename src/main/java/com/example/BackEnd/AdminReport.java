package com.example.BackEnd;

public class AdminReport extends ManagerReport {
    // see customer name, id, password, all transaction, all accounts
    private String password;


    public AdminReport(int customerId){
        super(customerId);
        this.password= facade.loadCustomer(customerId).getPassword();
    }

    public String getPassword() {
        return password;
    }





}
