package com.example.FrontEnd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController {//Albin worked on this, Liam worked partly on this
    private Stage stage;
    private Scene scene;

    @FXML
    public Button closeButton;

    @FXML
    private Button loginButton;

    @FXML
    private Button createAccount;

    @FXML
    private Button createEmployee;

    @FXML
    private Button loginEmployee;

    @FXML
    private Button manual;



    //all methods below are for switching scenes, or you could say interfaces

    @FXML//on interface button = main menu
    public void switchToLoginCustomer(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("loginCustomer.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML//on interface button = create account
    public void switchToRegisterCustomer(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("registerCustomer.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void switchToLoginEmployee(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("employeeLogin.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML//on interface button = create account
    public void switchToRegisterEmployee(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("registerEmployee.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void switchToManual() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("zManualCustomer.fxml"));
        Parent root1 = fxmlLoader.load();
        stage = new Stage();

        stage.setTitle("full manual");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML//on interface button = exit
    public void handelCloseButtonAction(/*ActionEvent event*/) throws IOException {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    //Methods to make the buttons glow

    @FXML
    private void confirmHoverInLog() {
        loginButton.setStyle("-fx-background-color: #52779C;");
    }

    @FXML
    private void confirmHoverOutLog() {
        loginButton.setStyle("-fx-background-color: #414D59;");
    }

    @FXML
    private void confirmHoverInExit() {
        closeButton.setStyle("-fx-background-color: #52779C;");
    }

    @FXML
    private void confirmHoverOutExit() {
        closeButton.setStyle("-fx-background-color: #414D59;");
    }

    @FXML
    private void confirmHoverInCreAcc() {
        createAccount.setStyle("-fx-background-color: #52779C;");
    }

    @FXML
    private void confirmHoverOutCreAcc() {
        createAccount.setStyle("-fx-background-color: #414D59;");
    }

    @FXML
    private void confirmHoverInLogEmp() {
        loginEmployee.setStyle("-fx-background-color: #52779C;");
    }

    @FXML
    private void confirmHoverOutLogEmp() {
        loginEmployee.setStyle("-fx-background-color: #414D59;");
    }

    @FXML
    private void confirmHoverInCreEmp() {
        createEmployee.setStyle("-fx-background-color: #52779C;");
    }

    @FXML
    private void confirmHoverOutCreEmp() {
        createEmployee.setStyle("-fx-background-color: #414D59;");
    }

    @FXML
    private void confirmHoverInMan() {
        manual.setStyle("-fx-background-color: #52779C;");
    }

    @FXML
    private void confirmHoverOutMan() {
        manual.setStyle("-fx-background-color: #414D59;");
    }
}
