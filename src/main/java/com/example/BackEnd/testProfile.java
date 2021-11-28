package com.example.BackEnd;

import javafx.scene.image.ImageView;

public class testProfile {

    public ImageView saveImage;
    public String firstName;
    public String lastName;
    public String userName;
    public String password;

    public testProfile(String firstName, String lastName, String userName, String password){
        this.saveImage = null;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
    }

    //getters
    public ImageView getSaveImage() {
        return saveImage;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getUserName() {
        return userName;
    }
    public String getPassword() {
        return password;
    }
    //setters
    public void setSaveImage(ImageView saveImage) {
        this.saveImage = saveImage;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
