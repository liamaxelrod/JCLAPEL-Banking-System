package com.example.BackEnd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class createAccount {

    static ArrayList<createAccount> listAccounts = new ArrayList<createAccount>(); // creating the list of accounts


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

    }

    public void registerUser(){
        String firstname = firstnameTextField.getText();
        String lastname = lastnameTextField.getText();
        String username = usernameTextField.getText();
        String password = setPasswordField.getText();

        String insertFields = "";
        String insertValues = "";
        String insertToRegister = insertFields + insertValues;
    }

}
