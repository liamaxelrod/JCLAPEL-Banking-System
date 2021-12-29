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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TransferController implements Initializable {//Albin worked on this, Liam worked partly on this
    private Stage stage;
    private Scene scene;

    //list of the different values the choice box can have
    private ObservableList<String> differentAccounts = FXCollections
            .observableArrayList("saving","checking","other");

    @FXML//on interface choice box = below from account
    private ChoiceBox<String> fromAccount;

    @FXML//on interface choice box = below to account
    private ChoiceBox<String> toAccount;

    @FXML//to interface label = next to current balance
    private Label fromAccountCurrentBalance; //(to be connected with the account)
//
    @FXML//on interface text field = below to account
    private TextField transferAmount;




    @Override//this method takes effect when the scene is loaded
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fromAccount.setValue("choose account");//not working don't know how to make it show?????????
        fromAccount.setItems(differentAccounts);

        toAccount.setValue("choose account");
        toAccount.setItems(differentAccounts);


    }

    @FXML//on interface button = total
    private void onActionButtonTotal(/*ActionEvent event*/) {
        fromAccountCurrentBalance.setText(fromAccount.getValue());
    }
//
    @FXML//on interface button = transfer
    private void onActionTransfer(/*ActionEvent event*/) {

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

    @FXML//on interface button = sign out
    public void switchToStart(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("start.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    //Methods to make the buttons glow
    @FXML
    private Button SignOut;

    @FXML
    private Button userMenu;
    @FXML
    private void confirmHoverInSignO() {
        SignOut.setStyle("-fx-background-color: #52779C;");
    }

    @FXML
    private void confirmHoverOutSignO() {
        SignOut.setStyle("-fx-background-color: #414D59;");
    }

    @FXML
    private void confirmHoverInUser() {
        userMenu.setStyle("-fx-background-color: #52779C;");
    }

    @FXML
    private void confirmHoverOutUser() {
        userMenu.setStyle("-fx-background-color: #414D59;");
    }

}