package com.example.jc_lapel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class statementHistoryController {

    private Stage stage;
    private Scene scene;

    @FXML
    void switchToCustomerMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("userMenuController.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void switchToTransferHistory(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("bankStatment.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void switchToFinanceProject(ActionEvent event) {

    }

    @FXML
    void switchToTransfer(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("transferCustomer.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
