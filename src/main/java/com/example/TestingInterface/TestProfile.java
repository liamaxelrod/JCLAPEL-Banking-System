package com.example.TestingInterface;

import javafx.scene.image.ImageView;

public class TestProfile {

//    public Image theDefaultImage = new Image(String.valueOf(url));
//
//    URL url = Thread.currentThread().getContextClassLoader().getResource("src/main/java/com/example/image/unnamed.jpg");

    public ImageView theImageView;
    public String firstName;
    public String lastName;
    public String ID;
    public String password;

    public TestProfile(String firstName, String lastName, String ID, String password){
//        this.theImageView.setImage(theDefaultImage);
//        trying save default image
        this.firstName = firstName;
        this.lastName = lastName;
        this.ID = ID;
        this.password = password;
//        System.out.println(url);
    }

    //getters
    public ImageView getTheImageView() {
        return theImageView;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getUserName() {
        return ID;
    }
    public String getPassword() {
        return password;
    }
    //setters
    public void setTheImageView(ImageView theImageView) {
        this.theImageView = theImageView;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setUserName(String userName) {
        this.ID = userName;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
