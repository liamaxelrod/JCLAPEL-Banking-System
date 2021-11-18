package com.example.jc_lapel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class userMenuController {
    private Stage stage;
    private Scene scene;

    @FXML
    private Button closeButton;

    @FXML
    private ImageView ImageProfile;

    @FXML
    private Label Username;

    @FXML
    void switchToTransfer(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("transferCustomer.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML//Not made yet
    void switchToProfile(ActionEvent event) throws IOException {

    }

    @FXML
    void switchToFinanceProject(ActionEvent event) {

    }

    @FXML//In SceneBuilder it says logout for the button
    void switchToStart(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("start.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void handelCloseButtonAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}