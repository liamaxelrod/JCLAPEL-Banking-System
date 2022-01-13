package com.example.BackEnd;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.HashMap;

public class Customer {
    private final int ID;
    private String name;
    private String password;
    private HashMap<Integer, Account> accounts = new HashMap<>();

    private File filePath = new File("src/main/java/com/example/FrontEnd/Image/lapel.jpg");//Liam I needed this
    private Image theImage = new Image(String.valueOf(filePath.toURI()));
    private ImageView profile = new ImageView();

    public Customer(int ID, String name, String password){
        this.ID=ID;
        this.name=name;
        this.password=password;

        this.profile.setImage(theImage);//Liam I needed this
    }

    public void addAccount(Account account){
        accounts.put(account.getID(), account);
    }

    //Liam created this because he needed a way to remove the accounts from the customer and not just from the façade
    public void RemoveAccount(int ID){
        accounts.remove(ID);
    }

    public double calculateFee(double amount){
        return amount * 0.01;
    }

    public int getID() {
        return ID;
    }

    public String getName(){
        return name;
    }

    public String getPassword(){
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public ImageView getProfile() {//Liam I needed this
        return profile;
    }
    public void setProfile(ImageView profile) {
        this.profile = profile;
    }

    public HashMap<Integer, Account> getAccounts(){
        return accounts;
    }
}
