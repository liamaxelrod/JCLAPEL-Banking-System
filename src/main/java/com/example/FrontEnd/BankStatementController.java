package com.example.FrontEnd;


import com.example.BackEnd.Account;
import com.example.BackEnd.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class BankStatementController implements Initializable {//Albin worked on this, Liam worked a little on this

    private Stage stage;
    private Scene scene;
    public static int account = 1;

    @FXML
    private Label selectedAccount;

    @FXML
    private ListView<String> listOfTransactions;


    private ObservableList<String> differentTransactions = FXCollections.observableArrayList();

    private void generatorListOfAccountTransactions(){
        Account theAccount = StartApplication.facade.loadAccount(account);
        for (Transaction currentTransaction : theAccount.getTransactions()){
            String fullTransaction = "Date: " + currentTransaction.getDate() + " /Time: " + currentTransaction.getTime() + " /Amount " + currentTransaction.getAmount();
            differentTransactions.add(fullTransaction);
        }
    }

    @Override//this method takes effect when the scene is loaded
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectedAccount.setText(String.valueOf(account));

        generatorListOfAccountTransactions();
        listOfTransactions.setItems(differentTransactions);



    }

    //all methods below are for switching scenes, or you could say interfaces

    @FXML//on interface button = user menu
    void switchToCustomerMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("userMenu.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML//on interface button = bank transfer
    void switchToStart(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("start.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
