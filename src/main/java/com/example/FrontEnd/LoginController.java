package com.example.FrontEnd;

import com.example.BackEnd.Account;
import com.example.BackEnd.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

//import static com.example.FrontEnd.StartApplication.facade;

public class LoginController {//Albin worked on this more Liam Partly worked on this
    private Stage stage;
    private Scene scene;

    @FXML//on interface password field = password
    private PasswordField enterPasswordField;

    @FXML//on interface text field = username
    private TextField ID;

    @FXML
    private Label warningLabel;



    //all methods below are for switching scenes, or you could say interfaces


    @FXML//on interface button = login
    public void switchToCustomerMenu(ActionEvent event) throws IOException{

        if (ID.getText().isBlank() || enterPasswordField.getText().isBlank()) {
            warningLabel.setText("You must insert: \n - integer in ID \n - Insert your password");
//        } else if (!ID.getText().contains("0")) {
//            warningLabel.setText("The idea can only be numbers");
        } else if (ID.getText().length() != 6){
            warningLabel.setText("Must be six digits");
        } else if (!StartApplication.facade.validatePassword(enterPasswordField.getText())){
            warningLabel.setText("Invalid password: \n - At least 8 characters \n - Must consist of 'a-z, A-Z, 0 -9' \n - Special character ex. '!' '&' '?' \n You must also Enter: \n - enter your security key \n - enter your position");
        } else if (!StartApplication.facade.CheckIfCustomerExists(Integer.parseInt(ID.getText()))){
            warningLabel.setText("This account does not exist");
        } else if (StartApplication.facade.checkLogin(Integer.parseInt(ID.getText()), enterPasswordField.getText())) {

            UserMenuController.activeID = Integer.parseInt(ID.getText());//Resets the user
            Customer theCustomer = StartApplication.facade.loadCustomer(Integer.parseInt(ID.getText()));
            int[] allAccounts = new int[theCustomer.getAccounts().size()];
            int loop = 0;

            for (Account accounts : theCustomer.getAccounts().values()){
                allAccounts[loop] = accounts.getID();
                loop = 0 + 1;
            }

            UserMenuController.accounts = allAccounts;//Reset user ends here

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("userMenu.fxml"));
            Parent root = loader.load();
            scene = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } else {
            warningLabel.setText(" The ID has to be all digits \n with no spaces");
        }
    }

    @FXML//on interface button = create account
    public void switchToRegisterCustomer(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("registerCustomer.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML//on interface button = main menu, cancel
    public void switchToStart(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("start.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    //Methods to make buttons glow up
    @FXML
    private Button cancelButton;

    @FXML
    private Button createAccount;

    @FXML
    private Button loginButton;

    @FXML
    private Button mainMenu;

    @FXML
    private void confirmHoverInCreAcc() {
        createAccount.setStyle("-fx-background-color: #52779C;");
    }

    @FXML
    private void confirmHoverOutCreAcc() {
        createAccount.setStyle("-fx-background-color: #414D59;");
    }
    @FXML
    private void confirmHoverInMainM() {
        mainMenu.setStyle("-fx-background-color: #52779C;");
    }

    @FXML
    private void confirmHoverOutMainM() {
        mainMenu.setStyle("-fx-background-color: #414D59;");
    }

    @FXML
    private void confirmHoverInLogin() {
        loginButton.setStyle("-fx-background-color: #676D5E;");
    }

    @FXML
    private void confirmHoverOutLogin() {
        loginButton.setStyle("-fx-background-color: #474B40;");
    }

    @FXML
    private void confirmHoverInCancel() {
        cancelButton.setStyle("-fx-background-color: #676D5E;");
    }

    @FXML
    private void confirmHoverOutCancel() {
        cancelButton.setStyle("-fx-background-color: #474B40;");
    }
}
