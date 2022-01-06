package com.example.FrontEnd;

import com.example.BackEnd.Account;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterEmployeeController implements Initializable{ // Albin worked on this
    private Stage stage;
    private Scene scene;
    private static int creationEmployeeID;
    private static String customerAndEmployeeName;

    private ObservableList<String> differentTitles = FXCollections.observableArrayList("Admin","Manager","Employee");

    @FXML
    private TextField securityKey;




    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private TextField firstnameTextField;

    @FXML
    private ChoiceBox<String> titleChoice;

    @FXML
    private TextField lastnameTextField;

    @FXML
    private PasswordField setPasswordField;

    @FXML
    private Label warningText;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        titleChoice.setValue("choose title");
        titleChoice.setItems(differentTitles);
    }

    private void autoEmployeeCreation() {
        customerAndEmployeeName = firstnameTextField.getText()+ " " + lastnameTextField.getText();
        creationEmployeeID = StartApplication.facade.createEmployee(customerAndEmployeeName, setPasswordField.getText());
    }

    private void autoManagerCreation() {
        customerAndEmployeeName = firstnameTextField.getText()+ " " + lastnameTextField.getText();
        creationEmployeeID = StartApplication.facade.createManager(customerAndEmployeeName, setPasswordField.getText());
    }

    private void autoAdminCreation() {
        customerAndEmployeeName = firstnameTextField.getText()+ " " + lastnameTextField.getText();
        creationEmployeeID = StartApplication.facade.createAdmin(customerAndEmployeeName, setPasswordField.getText());
    }

    private void autoEmployeeCustomerCreation(ActionEvent event) throws IOException {
        int customerID;
        int checkingAccountID;
        Account checkingAccount;

        customerID = StartApplication.facade.createEmployeeCustomer2(customerAndEmployeeName, setPasswordField.getText(), creationEmployeeID);
        checkingAccountID = StartApplication.facade.createEmployeeAccount(customerID);

        checkingAccount = StartApplication.facade.loadAccount(checkingAccountID);
        checkingAccount.setBalance(1000);

        StartApplication.facade.loadCustomer(customerID).addAccount(checkingAccount);

        EmployeeMenuController.inUseEmployeeActiveID = creationEmployeeID;//resets the Employee

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("employeeMenu.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    //all methods below are for switching scenes, or you could say interfaces

    @FXML//on interface button = Employee menu
    void switchToEmployeeMenu(ActionEvent event) throws IOException {
        String firstName = firstnameTextField.getText();
        String lastName = lastnameTextField.getText();
        String setPassword = setPasswordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        String position = titleChoice.getValue();
        int SecurityKey;

        if (true) {//This is for testing purposes switch to true to activate switch defaults to deactivate
            test(event);
        }

        if (!securityKey.getText().isEmpty()){
            SecurityKey = Integer.parseInt(securityKey.getText());


            if (!StartApplication.facade.validatePassword(setPassword) && !StartApplication.facade.validatePassword(confirmPassword)) {
                warningText.setText("The password must have: \n - At least 8 characters \n - Must consist of 'a-z, A-Z, 0 -9' \n - Special character ex. '!' '&' '?' \n You must also Enter: \n - enter your security key \n - enter your position");
            } else if (!setPassword.matches(confirmPassword)) {
                warningText.setText("the two passwords do not match");
            } else if (!StartApplication.facade.validateName(firstName) && !StartApplication.facade.validateName(lastName)) {
                warningText.setText("the first and last name cannot be blank");
            } else if (firstName.contains(" ") || lastName.contains(" ")){
                warningText.setText("first and last name cannot contain spaces");
            } else if (firstName.length() > 13 || firstName.length() < 3  || lastName.length() < 3 || lastName.length() > 13) {
                warningText.setText("your first and last name must: \n - be between 3 letters to 13 letters");
            } else if (titleChoice.getValue() != "Admin" && titleChoice.getValue() != "Manager" && titleChoice.getValue() != "Employee" ){
                warningText.setText("you need to pick a position");
            } else if (SecurityKey == StartApplication.securityKey1) {
                if (position == "Employee") {
                    autoEmployeeCreation();
                    autoEmployeeCustomerCreation(event);
                } else {
                    warningText.setText("wrong key for position");
                }
            } else if (SecurityKey == StartApplication.securityKey2){
                if (position == "Manager"){
                    autoManagerCreation();
                    autoEmployeeCustomerCreation(event);
                } else {
                    warningText.setText("wrong key for position ");
                }
            } else if (SecurityKey == StartApplication.securityKey3){
                if (position == "Admin"){
                    autoAdminCreation();
                    autoEmployeeCustomerCreation(event);
                } else {
                    warningText.setText("wrong key for position");
                }
            } else {
                warningText.setText("This does not match any security key");
            }
        } else {
            warningText.setText("Security key is empty");
        }



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


    @FXML//on interface button = login
    public void switchToLoginEmployee(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("employeeLogin.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    //Methods to make the buttons glow
    @FXML
    private Button createAccountButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button employeeLog;
    @FXML
    private Button mainMenu;

    @FXML
    private void confirmHoverInEmployeeLog() {
        employeeLog.setStyle("-fx-background-color: #52779C;");
    }

    @FXML
    private void confirmHoverOutMainMenu() {
        mainMenu.setStyle("-fx-background-color: #414D59;");
    }

    @FXML
    private void confirmHoverInMainMenu() {
        mainMenu.setStyle("-fx-background-color: #52779C;");
    }

    @FXML
    private void confirmHoverOutEmployeeLog() {
        employeeLog.setStyle("-fx-background-color: #414D59;");
    }

    @FXML
    private void confirmHoverInCreAcc() {
        createAccountButton.setStyle("-fx-background-color: #676D5E;");
    }

    @FXML
    private void confirmHoverOutCreAcc() {
        createAccountButton.setStyle("-fx-background-color: #474B40;");
    }

    @FXML
    private void confirmHoverInCancel() {
        cancelButton.setStyle("-fx-background-color: #676D5E;");
    }

    @FXML
    private void confirmHoverOutCancel() {
        cancelButton.setStyle("-fx-background-color: #474B40;");
    }

    @FXML
    private void confirmHoverInCreBut() {
        createAccountButton.setStyle("-fx-background-color: #676D5E;");
    }

    @FXML
    private void confirmHoverOutCreBut() {
        createAccountButton.setStyle("-fx-background-color: #474B40;");
    }

    private void test(ActionEvent event) throws IOException {
        int customerID;
        int checkingAccountID;
        Account checkingAccount;

        customerAndEmployeeName = "jason" + " " + "Strand";
        creationEmployeeID = StartApplication.facade.createAdmin(customerAndEmployeeName, "!Q1qaaaaa");

        customerID = StartApplication.facade.createEmployeeCustomer2(customerAndEmployeeName, "!Q1qaaaaa", creationEmployeeID);
        checkingAccountID = StartApplication.facade.createEmployeeAccount(customerID);

        checkingAccount = StartApplication.facade.loadAccount(checkingAccountID);
//        checkingAccount.setBalance(1000);

        StartApplication.facade.loadCustomer(customerID).addAccount(checkingAccount);

        EmployeeMenuController.inUseEmployeeActiveID = creationEmployeeID;//resets the Employee

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("employeeMenu.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
