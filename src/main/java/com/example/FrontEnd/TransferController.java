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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TransferController extends AccessToTheTalkToBack implements Initializable {
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

    @FXML//on interface text field = below to account
    private TextField transferAmount;

    @FXML//on interface button = total
    private void onActionButtonTotal(ActionEvent event) {
        fromAccountCurrentBalance.setText(fromAccount.getValue());
    }

    @FXML//on interface button = transfer
    private void onActionTransfer(ActionEvent event) {

    }

    //The set-up Methods
    public testProfile userProfile;

    public void setUserProfile(testProfile newTestProfile){
        userProfile = newTestProfile;
    }
    public void setUpProfile(){

    }
    //End of set-up Methods

    @Override//this method takes effect when the scene is loaded
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fromAccount.setValue("choose account");//not working don't know how to make it show?????????
        fromAccount.setItems(differentAccounts);

        toAccount.setValue("choose account");
        toAccount.setItems(differentAccounts);
    }

    //all methods below are for switching scenes, or you could say interfaces

    @FXML//on interface button = user menu
    void switchToCustomerMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("userMenu.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

//        userMenuController thisController = loader.getController();
//        thisController.setUserProfile(talkToGoBetween.createTestProfile());
//        thisController.setUpProfile();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML//on interface button = bank statement
    void switchToBankStatement(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("bankStatment.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

//        userMenuController thisController = loader.getController();
//        thisController.setUserProfile(talkToGoBetween.createTestProfile());
//        thisController.setUpProfile();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML//on interface button = portfolio
    void switchToFinanceProject(ActionEvent event) throws IOException {

    }
    @FXML//on interface button = sign out
    public void switchToStart(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("start.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

//        userMenuController thisController = loader.getController();
//        thisController.setUserProfile(talkToGoBetween.createTestProfile());
//        thisController.setUpProfile();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}