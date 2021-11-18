package com.example.jc_lapel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;

public class TransferController {
    private Stage stage;
    private Scene scene;

    @FXML
    private Button closeButton;

    @FXML
    private ChoiceBox<?> fromAccount;

    @FXML
    private ChoiceBox<?> otherAccount;

    @FXML
    private ChoiceBox<?> toAccount;

    @FXML
    void switchToCustomerMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("userMenuController.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

//    @FXML
//    void switchToBankStatement(ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource(""));
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }

//    @FXML
//    void switchToFinanceProject(ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource(""));
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }

    @FXML
    void switchToStart(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("start.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}