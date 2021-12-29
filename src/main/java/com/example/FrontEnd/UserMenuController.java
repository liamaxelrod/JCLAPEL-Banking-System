package com.example.FrontEnd;


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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class UserMenuController implements Initializable {//Liam was most responsible for this, Albin Worked on this
    private Stage stage;
    private Scene scene;

    public static int activeID;
    private Customer currentCustomerUse;
    public static int[] Account;


    @FXML//on interface image view = upper far right position
    private ImageView imageProfile;

    @FXML//on interface label = username
    private TextField userID;


    @FXML//on interface label = next to name:
    private Label fullName;

    @FXML//on interface ListView = Middle of interface
    private ListView<String> loadListOfAccount;

    private ObservableList<String> differentAccount = FXCollections.observableArrayList();
    private void generatorListOfAccount(){
        HashMap<Integer, com.example.BackEnd.Account> currentList = currentCustomerUse.getAccounts();
        for (com.example.BackEnd.Account currentAccount: currentList.values()) {
            String type = "Checking";
            if (currentAccount.isSavings()){
                type = "Saving";
            }
            differentAccount.add(type + ": " + currentAccount.getBalance() + " ID:" + currentAccount.getID());
        }
    }

    @Override//this method takes effect when the scene is loaded
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentCustomerUse = StartApplication.facade.loadCustomer(activeID);
        userID.setText(String.valueOf(activeID));

        fullName.setText(currentCustomerUse.getName());

        generatorListOfAccount();//set up Accounts
        loadListOfAccount.setItems(differentAccount);
    }

    //all methods below are for switching scenes, or you could say interfaces

    @FXML//on interface button = transfer
    void switchToTransfer(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("transferCustomer.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML//on interface button = profile
    void switchToProfile(ActionEvent event) throws IOException {//Test profile Connected
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("profile.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML//on interface button = financial projects loan, portfolio, etc.
    void switchToFinanceProject(ActionEvent event) {

    }
    @FXML//on interface button = logout
    void switchToStart(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("start.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML//on interface button = transfer history
    void switchToBankStatement(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("bankStatment.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void switchToManual(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("zManualCustomer.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        stage = new Stage();

        stage.setTitle("full manual");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML//on interface button = exit
    private Button closeButton;
    @FXML
    void handelCloseButtonAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }


    //Methods to make buttons glow
    @FXML
    private Button financePort;

    @FXML
    private Button logoutButton;

    @FXML
    private Button manual;

    @FXML
    private Button manualButton;

    @FXML
    private Button profileButton;

    @FXML
    private Button transferButton;

    @FXML
    private Button transferHis;
    
    @FXML
    private void confirmHoverInLogOff() {
        logoutButton.setStyle("-fx-background-color: #52779C;");
    }

    @FXML
    private void confirmHoverOutLogOff() {
        logoutButton.setStyle("-fx-background-color: #414D59;");
    }

    @FXML
    private void confirmHoverInTran() {
        transferButton.setStyle("-fx-background-color: #52779C;");
    }

    @FXML
    private void confirmHoverOutTran() {
        transferButton.setStyle("-fx-background-color: #414D59;");
    }

    @FXML
    private void confirmHoverInPro() {
        profileButton.setStyle("-fx-background-color: #52779C;");
    }

    @FXML
    private void confirmHoverOutPro() {
        profileButton.setStyle("-fx-background-color: #414D59;");
    }

    @FXML
    private void confirmHoverInFin() {
        financePort.setStyle("-fx-background-color: #52779C;");
    }

    @FXML
    private void confirmHoverOutFin() {
        financePort.setStyle("-fx-background-color: #414D59;");
    }

    @FXML
    private void confirmHoverInTranHi() {
        transferHis.setStyle("-fx-background-color: #52779C;");
    }

    @FXML
    private void confirmHoverOutTranHi() {
        transferHis.setStyle("-fx-background-color: #414D59;");
    }

    @FXML
    private void confirmHoverInMan() {
        manual.setStyle("-fx-background-color: #52779C;");
    }

    @FXML
    private void confirmHoverOutMan() {
        manual.setStyle("-fx-background-color: #414D59;");
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
    private void confirmHoverInManB() {
        manualButton.setStyle("-fx-background-color: #52779C;");
    }

    @FXML
    private void confirmHoverOutManB() {
        manualButton.setStyle("-fx-background-color: #414D59;");
    }

}