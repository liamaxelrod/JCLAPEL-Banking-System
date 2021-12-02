package com.example.TestingInterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class CreateAccount {

    static ArrayList<CreateAccount> listAccounts = new ArrayList<CreateAccount>(); // creating the list of accounts


    @FXML
    private Button cancelButton;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Button createAccountButton;

    @FXML
    private TextField firstnameTextField;

    @FXML
    private TextField lastnameTextField;

    @FXML
    private PasswordField setPasswordField;

    @FXML
    private TextField usernameTextField;

    @FXML
    void onActionCreateAccount(ActionEvent event) {
    registerUser();
    }
    //The set-up Methods
    public TestProfile userProfile;

    public void registerUser(){
        String firstname = firstnameTextField.getText();
        String lastname = lastnameTextField.getText();
        String username = usernameTextField.getText();
        String password = setPasswordField.getText();

        String insertFields = "";
        String insertValues = "";
        String insertToRegister = insertFields + insertValues;
    }


    // Trying to save whatever input user puts
    public void createAccount(){
        firstnameTextField.setText(userProfile.getFirstName());
        lastnameTextField.setText(userProfile.getLastName());
        usernameTextField.setText(userProfile.getUserName());
        setPasswordField.setText(userProfile.getPassword());
    }

}
