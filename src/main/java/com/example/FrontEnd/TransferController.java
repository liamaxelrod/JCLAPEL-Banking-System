package com.example.FrontEnd;


import com.example.BackEnd.Account;
import com.example.BackEnd.Customer;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class TransferController implements Initializable {//Albin worked on this, Liam worked partly on this
    private Stage stage;
    private Scene scene;

    private Customer currentCustomerUse;

    //list of the different values the choice box can have
    private ObservableList<String> differentCustomerAccounts = FXCollections.observableArrayList();
    private ObservableList<String> differentAllAccounts = FXCollections.observableArrayList();

    @FXML//On interface list view + Label = The label Goes with list of you box on the interface
    private Label selectedFromAccountLabel;
    @FXML
    private ListView<String> fromAccount;
    @FXML
    private Label selectedToAccountLabel;
    @FXML
    private ListView<String> toAccount;


    @FXML//on interface text field = below to account
    private TextField transferAmount;

    @FXML//On the interface label = above the text field about the amount of money your transfer
    private Label warningLabel;


    private void generatorListOfCustomerAccounts(){
        HashMap<Integer, Account> currentList = StartApplication.facade.getCustomerAccounts(currentCustomerUse.getID());
        for (com.example.BackEnd.Account currentAccount: currentList.values()) {
            String type = "Checking";
            if (currentAccount.isSavings()){
                type = "Saving";
            }
            differentCustomerAccounts.add("ID: " + currentAccount.getID() + " " + type + ": " + currentAccount.getBalance());
        }
    }
    private void generatorListOfAllAccounts(){
        HashMap<Integer, Account> currentList = StartApplication.facade.getAllAccounts();
        for (com.example.BackEnd.Account currentAccount: currentList.values()) {
            String type = "Checking";
            if (currentAccount.isSavings()){
                type = "Saving";
            }
            differentAllAccounts.add("ID: " + currentAccount.getID() + " " + type);
        }
    }

    @Override//this method takes effect when the scene is loaded
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentCustomerUse = StartApplication.facade.loadCustomer(UserMenuController.activeID);

        generatorListOfCustomerAccounts();
        fromAccount.setItems(differentCustomerAccounts);
        generatorListOfAllAccounts();
        toAccount.setItems(differentAllAccounts);
    }


    @FXML
    void onActionSelectAccounFrom(ActionEvent event) {
        String ID = fromAccount.getSelectionModel().getSelectedItem().substring(4,10);
        selectedFromAccountLabel.setText(ID);
    }

    @FXML
    void onActionSelectAccountTo(ActionEvent event) {
        String ID = toAccount.getSelectionModel().getSelectedItem().substring(4,10);
        selectedToAccountLabel.setText(ID);
    }

    @FXML//on interface button = transfer
    private void onActionTransfer(ActionEvent event) throws IOException {
//        if (){
//
//        }
        int fromAccount = Integer.parseInt(selectedFromAccountLabel.getText());
        int toAccount = Integer.parseInt(selectedToAccountLabel.getText());
        double amount = Double.parseDouble(transferAmount.getText());
        StartApplication.facade.withdraw(fromAccount, amount);
        StartApplication.facade.deposit(toAccount, amount);
        refresh(event);
    }

    //all methods below are for switching scenes, or you could say interfaces

    private void refresh(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("transferCustomer.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

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
    private Button selectedAccountFromButton;
    @FXML
    private Button selectedAccountToButton;
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