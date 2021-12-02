package com.example.FrontEnd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController extends AccessToTheTalkToBack {

    //For testing purposes will be removed later
    public static String firstName;
    public static String secondName;
    public static int iD;
    public static String password;
    public static double money = 10000.00;

    private Stage stage;
    private Scene scene;

    @FXML//on interface password field = confirm password
    private PasswordField confirmPasswordField;

    @FXML//on interface text field = Firstname
    private TextField firstnameTextField;

    @FXML//on interface text field = Lastname
    private TextField lastnameTextField;

    @FXML//on interface password field = password
    private PasswordField setPasswordField;

    @FXML//on interface text field = username
    private TextField usernameTextField;

    //Upon pressing the button "create account" you save all data
    @FXML//on interface button = create account
    private void onActionCreateAccount(ActionEvent event){
        createAccount();//For testing purposes only will be removed later
        StartApplication.goBetween.createTestProfile(firstName,secondName, iD,password);
        firstnameTextField.setText("");

    StartApplication.facade.createAccount(usernameTextField.getText(), setPasswordField.getText());
    }

    // Creating Variables for the account For testing purposes
    public void createAccount(){
        firstName = firstnameTextField.getText();
        secondName = lastnameTextField.getText();
        password = setPasswordField.getText();
        }




    //all methods below are for switching scenes, or you could say interfaces

    @FXML//on interface button = login
    public void switchToLoginCustomer(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("loginCustomer.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML//on interface button = main menu
    public void switchToStart(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("start.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
