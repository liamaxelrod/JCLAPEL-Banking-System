package com.example.FrontEnd;

import com.example.BackEnd.Facade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class RegisterController implements Initializable {//Albin worked on this, Liam partly worked on this


    //For testing purposes will be removed later
    public static String firstName;
    public static String secondName;
    public static int iD;

    public static String password;
    public static double money = 10000.00;
    public static int idNum;
    public int id;
    public static int num;

    static Facade facade = new Facade();

    private Stage stage;
    private Scene scene;

    @FXML
    private Label warningText;

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

    @FXML
//    private Label idNum;??????

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    //Upon pressing the button "create account" you save all data

    @FXML
    private void onActionCreateAccount(ActionEvent event) throws IOException {
        id = facade.createCustomer(firstnameTextField.getText(), setPasswordField.getText());
        idNum = id;
        num = 1;

        if (id != 0){

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("userMenu.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

        StartApplication.facade.createCustomer(usernameTextField.getText(), setPasswordField.getText());
    }else{
        warningText.setText("The password must have: \n - At least 8 characters \n - Must consist of 'a-z, A-Z, 0 -9' \n - Special character ex. '!' '&' '?' ");
    }

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
