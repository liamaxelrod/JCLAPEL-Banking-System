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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    public static int[] accounts;


    @FXML//on interface image view = upper far right position
    private ImageView imageProfile;
    private Image theImage;

    @FXML//on interface label = username
    private TextField userID;


    @FXML//on interface label = next to name:
    private Label fullName;
    @FXML
    private Label selectedAccount;

    @FXML//On interface label = above select and delete account
    private Label warningLabel;


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
            differentAccount.add(" ID:" + currentAccount.getID() + " " +type + ": " + currentAccount.getBalance());
        }
    }

    @Override//this method takes effect when the scene is loaded
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentCustomerUse = StartApplication.facade.loadCustomer(activeID);
        userID.setText(String.valueOf(activeID));

        fullName.setText(currentCustomerUse.getName());

        generatorListOfAccount();//set up Accounts
        loadListOfAccount.setItems(differentAccount);


        imageProfile.setImage(currentCustomerUse.getProfile().getImage());
    }


    //All methods below are actions that can be taken in on the interface
    @FXML
    void onActionSelectAccount(/*ActionEvent event*/) {
        String ID = loadListOfAccount.getSelectionModel().getSelectedItem().substring(4,10);
        selectedAccount.setText(ID);
    }

    @FXML
    void onActionDeleteAccount(ActionEvent event) throws IOException {
        if (selectedAccount.getText().length() != 6){
            warningLabel.setText("need to select an account");
        } else if (StartApplication.facade.loadAccount(Integer.parseInt(selectedAccount.getText())).getBalance() != 0){
            warningLabel.setText("Account Isn't empty");
        } else if (StartApplication.facade.loadAccount(Integer.parseInt(selectedAccount.getText())).getBalance() == 0){
            currentCustomerUse.RemoveAccount(Integer.parseInt(selectedAccount.getText()));
            StartApplication.facade.removeAccount(Integer.parseInt(selectedAccount.getText()));
            refreshUserMenu(event);
        }
    }

    @FXML
    void onActionMakeCheckingAccount(ActionEvent event) throws IOException {
        StartApplication.facade.createAccount(activeID);
        refreshUserMenu(event);
    }

    @FXML
    void onActionMakeSavingAccount(ActionEvent event) throws IOException {
        StartApplication.facade.createSavingsAccount(activeID);
        refreshUserMenu(event);
    }

    //all methods below are for switching scenes, or you could say interfaces

    private void refreshUserMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("userMenu.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

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
    void switchToFinanceProject(/*ActionEvent event*/) {/////////////Not complete

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
        if (selectedAccount.getText().length() == 6){
            BankStatementController.account = Integer.parseInt(selectedAccount.getText());

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("bankStatment.fxml"));
            Parent root = loader.load();
            scene = new Scene(root);

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } else {
            warningLabel.setText("Must select account");
        }

    }
    @FXML
    void switchToManual(/*ActionEvent event*/) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("zManualCustomer.fxml"));
        Parent root1 = /*(Parent)*/ fxmlLoader.load();
        stage = new Stage();

        stage.setTitle("full manual");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML//on interface button = exit
    private Button closeButton;
    @FXML
    void handelCloseButtonAction(/*ActionEvent event*/) throws IOException {
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
    private Button deleteAccountButton;
    @FXML
    private Button selectAccountButton;
    @FXML
    private Button makeCheckingAccountButton;
    @FXML
    private Button makeSavingAccountButton;

    @FXML
    void confirmHoverEntry(MouseEvent event) {
        if (logoutButton == event.getSource()) {
            logoutButton.setStyle("-fx-background-color: #52779C;");
        } else if (transferButton == event.getSource()) {
            transferButton.setStyle("-fx-background-color: #52779C;");
        } else if (profileButton == event.getSource()) {
            profileButton.setStyle("-fx-background-color: #52779C;");
        } else if (financePort == event.getSource()) {
            financePort.setStyle("-fx-background-color: #52779C;");
        } else if (transferHis == event.getSource()) {
            transferHis.setStyle("-fx-background-color: #52779C;");
        } else if (manual == event.getSource()) {
            manual.setStyle("-fx-background-color: #52779C;");
        } else if (manualButton == event.getSource()) {
            manualButton.setStyle("-fx-background-color: #52779C;");
        } else if (selectAccountButton == event.getSource()) {
            selectAccountButton.setStyle("-fx-background-color: #52779C;");
        } else if (deleteAccountButton == event.getSource()) {
            deleteAccountButton.setStyle("-fx-background-color: #52779C;");
        }  else if (makeCheckingAccountButton == event.getSource()) {
            makeCheckingAccountButton.setStyle("-fx-background-color: #52779C;");
        }  else if (makeSavingAccountButton == event.getSource()) {
            makeSavingAccountButton.setStyle("-fx-background-color: #52779C;");
        }  else if (closeButton == event.getSource()) {
            closeButton.setStyle("-fx-background-color: #52779C;");
        }
    }

    @FXML
    void confirmHoverExit(MouseEvent event) {
        if (logoutButton == event.getSource()) {
            logoutButton.setStyle("-fx-background-color: #414D59;");
        } else if (transferButton == event.getSource()) {
            transferButton.setStyle("-fx-background-color: #414D59;");
        } else if (profileButton == event.getSource()) {
            profileButton.setStyle("-fx-background-color: #414D59;");
        } else if (financePort == event.getSource()) {
            financePort.setStyle("-fx-background-color: #414D59;");
        } else if (transferHis == event.getSource()) {
            transferHis.setStyle("-fx-background-color: #414D59;");
        } else if (manual == event.getSource()) {
            manual.setStyle("-fx-background-color: #414D59;");
        } else if (manualButton == event.getSource()) {
            manualButton.setStyle("-fx-background-color: #414D59;");
        } else if (selectAccountButton == event.getSource()) {
            selectAccountButton.setStyle("-fx-background-color: #414D59;");
        } else if (deleteAccountButton == event.getSource()) {
            deleteAccountButton.setStyle("-fx-background-color: #414D59;");
        } else if (makeCheckingAccountButton == event.getSource()) {
            makeCheckingAccountButton.setStyle("-fx-background-color: #414D59;");
        } else if (makeSavingAccountButton == event.getSource()) {
            makeSavingAccountButton.setStyle("-fx-background-color: #414D59;");
        } else if (closeButton == event.getSource()) {
            closeButton.setStyle("-fx-background-color: #414D59;");
        }
    }

}