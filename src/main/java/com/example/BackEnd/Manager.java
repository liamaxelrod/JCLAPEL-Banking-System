package com.example.BackEnd;

public class Manager extends Employee{
    private String position;


    public Manager(int ID, String name, String password){
        super(ID, name, password);
        this.position="Manager";
    }

    @Override
    public String getPosition() {
        return position;
    }
}