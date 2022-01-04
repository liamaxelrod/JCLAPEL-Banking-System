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
//
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

    //all methods below are for switching scenes, or you could say interfaces

    @FXML//on interface button = Employee menu
    void switchToEmployeeMenu(ActionEvent event) throws IOException {
        int employeeID;
        int customerID;
        int checkingAccountID;
        Account checkingAccount;

        if (    StartApplication.facade.validatePassword(setPasswordField.getText()) &&
                StartApplication.facade.validatePassword(confirmPasswordField.getText()) &&
                StartApplication.facade.validateName(firstnameTextField.getText()) &&
                StartApplication.facade.validateName(lastnameTextField.getText())){//*Input valid key and valid position at a later date

            String fillName = firstnameTextField.getText()+ " " + lastnameTextField.getText();
            employeeID = StartApplication.facade.createEmployee(fillName, setPasswordField.getText());

            customerID = StartApplication.facade.createEmployeeCustomer(firstnameTextField.getText(), setPasswordField.getText());
            checkingAccountID = StartApplication.facade.createEmployeeAccount(customerID);

            checkingAccount = StartApplication.facade.loadAccount(checkingAccountID);

            checkingAccount.setBalance(1000);

            StartApplication.facade.loadCustomer(customerID).addAccount(checkingAccount);


            EmployeeMenuController.inUseEmployeeActiveID = employeeID;//resets the Employee

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("employeeMenu.fxml"));
            Parent root = loader.load();
            scene = new Scene(root);

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

            //This is for testing will be deleted later
        } else if (!StartApplication.facade.validatePassword(setPasswordField.getText())) {
            String fillName = "liam" + " " + "axelrod";

            employeeID = StartApplication.facade.createEmployee(fillName, "!Q1qaaaaa");
            customerID = StartApplication.facade.createEmployeeCustomer2(fillName, "!Q1qaaaaa",employeeID);
            checkingAccountID = StartApplication.facade.createEmployeeAccount(customerID);
            checkingAccount = StartApplication.facade.loadAccount(checkingAccountID);
//            checkingAccount.setBalance(1000);
            StartApplication.facade.loadCustomer(customerID).addAccount(checkingAccount);
            EmployeeMenuController.inUseEmployeeActiveID = employeeID;//resets the user
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("employeeMenu.fxml"));
            Parent root = loader.load();
            scene = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            //It will be deleted down to hear
        } else {
            warningText.setText("The password must have: \n - At least 8 characters \n - Must consist of 'a-z, A-Z, 0 -9' \n - Special character ex. '!' '&' '?' \n You must also Enter: \n - enter your security key \n - enter your position" );
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
}
