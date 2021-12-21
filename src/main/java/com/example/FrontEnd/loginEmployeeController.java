package com.example.FrontEnd;

//import static com.example.FrontEnd.StartApplication.facade;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class loginEmployeeController {//Liam did this
    private Stage stage;
    private Scene scene;
    public static String logNum;

    @FXML//on interface password field = password
    private PasswordField enterPasswordField;

    @FXML//On interface next to confirm password = warning
    private Label warningText;

    @FXML//On either case text field = password And confirm password
    private PasswordField confirmPasswordField;
    @FXML
    private PasswordField setPasswordField;

    @FXML//on interface text field = ID
    private TextField ID;

    @FXML//On interface text field = security key
    private TextField employeeSecurityKey;

    @FXML//on interface button = create account
    void switchToEmployeeMenu(ActionEvent event)throws IOException {
        if (StartApplication.facade.checkLogin(Integer.parseInt(ID.getText()), enterPasswordField.getText())){//*There is no employee check for login

            EmployeeMenuController.activeID = Integer.parseInt(ID.getText());//Resets the Employee

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("employeeMenu.fxml"));
            Parent root = loader.load();
            scene = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } else {
            warningText.setText("The password must have: \n - At least 8 characters \n - Must consist of 'a-z, A-Z, 0 -9' \n - Special character ex. '!' '&' '?' \n You must also Enter: \n - enter your security key \n - enter your position" );
        }
    }

    @FXML//on interface button = login  //Need the unique version is your logging into your profile for the first time
    void switchToRegisterEmployee(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("registerEmployee.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML//on interface button = main menu, cancel
    void switchToStart(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("start.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    //Methods to make buttons glow
    @FXML
    private Button loginButton;
    @FXML
    private Button createAcc;

    @FXML
    private Button cancelButton;

    @FXML
    private Button mainMenu;

    @FXML
    private void confirmHoverInMain() {
        mainMenu.setStyle("-fx-background-color: #52779C;");
    }

    @FXML
    private void confirmHoverOutMain() {
        mainMenu.setStyle("-fx-background-color: #414D59;");
    }

    @FXML
    private void confirmHoverInCreate() {
        createAcc.setStyle("-fx-background-color: #52779C;");
    }

    @FXML
    private void confirmHoverOutCreate() {
        createAcc.setStyle("-fx-background-color: #414D59;");
    }

    @FXML
    private void confirmHoverInLogin() {
        loginButton.setStyle("-fx-background-color: #676D5E;");
    }

    @FXML
    private void confirmHoverOutLogin() {
        loginButton.setStyle("-fx-background-color: #474B40;");
    }

    @FXML
    private void confirmHoverInCancel() {
        cancelButton.setStyle("-fx-background-color: #676D5E;");
    }

    @FXML
    private void confirmHoverOutCancel() {
        cancelButton.setStyle("-fx-background-color: #474B40;");
    }
}