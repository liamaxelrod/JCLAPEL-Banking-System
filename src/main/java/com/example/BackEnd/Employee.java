package com.example.BackEnd;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.HashMap;

public class Employee {
    private final int ID;
    private String name;
    private String password;
    private String position;
    private HashMap<Integer, Account> accounts = new HashMap<>();

    private File filePath = new File("src/main/java/com/example/FrontEnd/Image/lapel.jpg");//Liam I needed this
    private Image theImage = new Image(String.valueOf(filePath.toURI()));
    private ImageView profile = new ImageView();


    public Employee(int ID, String name, String password){
        this.ID=ID;
        this.name=name;
        this.password=password;
        this.position="Employee";

        this.profile.setImage(theImage);//Liam I needed this

    }

    public String getPosition() {
        return position;
    }


    public int getID() {
        return ID;
    }

    public String getPassword() {
        return password;
    } //Liam I needed this

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }//Liam Axelrod I made this, so I can change the password

    public HashMap<Integer, Account> getAccounts(){
        return accounts;
    }

    public ImageView getProfile() {//Liam I needed this
        return profile;
    }
    public void setProfile(ImageView profile) {
        this.profile = profile;
    }
}
