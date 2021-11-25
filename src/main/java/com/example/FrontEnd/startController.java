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

public class startController {
    private Stage stage;
    private Scene scene;

    @FXML
    public Button closeButton;


    @FXML//on interface button = main menu
    public void switchToLoginCustomer(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("loginCustomer.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML//on interface button = create account
    public void switchToRegisterCustomer(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("registerCustomer.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML//on interface button = exit
    public void handelCloseButtonAction(ActionEvent event){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
