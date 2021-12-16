package com.example.FrontEnd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//import static com.example.FrontEnd.StartApplication.facade;

public class LoginController implements Initializable {//Albin worked on this more Liam Partly worked on this
    private Stage stage;
    private Scene scene;
    public static String logNum;

    @FXML//on interface password field = password
    private PasswordField enterPasswordField;

    @FXML//on interface text field = username
    private TextField usernameTextField;


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

    @Override//this method takes effect when the scene is loaded
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void createAccountStageForm(){
        try{
            Parent root;

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    //all methods below are for switching scenes, or you could say interfaces

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

// Successfully made the code look for the username and password created
    @FXML//on interface button = login  //Need the unique version is your logging into your profile for the first time
    public void switchToCustomerMenu(ActionEvent event) throws IOException{
        RegisterController.num = 0;
        logNum = usernameTextField.getText();
        //System.out.println(facade.checkLogin(Integer.parseInt(usernameTextField.getText()), enterPasswordField.getText()));
        if (RegisterController.facade.checkLogin(Integer.parseInt(usernameTextField.getText()), enterPasswordField.getText())){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("userMenu.fxml"));
            Parent root = loader.load();
            scene = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }
    //usernameTextField.getText().toString().equals(RegisterController.id) Check ID
    // enterPasswordField.getText().toString().equals(RegisterController.password check password

}
