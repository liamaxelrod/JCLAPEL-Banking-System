
package com.example.BackEnd;

public class Admin extends Employee {
    private String position;


    public Admin(int ID, String name, String password){
        super(ID,name, password);
        this.position = "Admin";
    }

    @Override
    public String getPosition() {
        return position;
    }
}

