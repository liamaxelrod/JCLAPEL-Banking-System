package com.example.FrontEnd;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterEmployeeController implements Initializable{ // Albin worked on this
    private Stage stage;
    private Scene scene;
fggjhf
    private ObservableList<String> differentTitles = FXCollections.observableArrayList("Admin","Manager","Employee");

    @FXML
    private Button cancelButton;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Button createAccountButton;

    @FXML
    private TextField firstnameTextField;

    @FXML
    private ChoiceBox<String> titleChoice;

    @FXML
    private TextField lastnameTextField;

    @FXML
    private PasswordField setPasswordField;

    @FXML
    void onActionCreateAccount(ActionEvent event) {

    }


    @FXML
    private Button employeeLog;

    @FXML
    private Button mainMenu;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        titleChoice.setValue("choose title");
        titleChoice.setItems(differentTitles);
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

    @FXML//on interface button = user menu
    void switchToEmployeeMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("employeeMenu.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    //Methods to make the buttons glow

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
