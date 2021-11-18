package com.jclapel.banksystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;

import java.io.IOException;

public class RegisterController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToLoginCustomer(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("loginCustomer.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToHelloView(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
