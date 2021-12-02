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

public class RegisterController extends accessToTheTalkToBack {

    public static int id;
    public static String firstName;
    public static String secondName;
    public static int idNum;
    public static String password;
    public static double savingMoney = 20000.00;
    public static double CheckingMoney = 10000.00;


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
    StartApplication.facade.createAccount(usernameTextField.getText(), setPasswordField.getText());
    }

    // Creating Variables for the account
    public void createAccount(){
        firstName = firstnameTextField.getText();
        secondName = lastnameTextField.getText();
        //Make sure it works
        idNum = usernameTextField.getPrefColumnCount();
        password = setPasswordField.getText();
        }




    @FXML//on interface button = login
    public void switchToLoginCustomer(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("loginCustomer.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

//        userMenuController thisController = loader.getController();
//        thisController.setUserProfile(talkToGoBetween.createTestProfile());
//        thisController.setUpProfile();

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

//        userMenuController thisController = loader.getController();
//        thisController.setUserProfile(talkToGoBetween.createTestProfile());
//        thisController.setUpProfile();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
