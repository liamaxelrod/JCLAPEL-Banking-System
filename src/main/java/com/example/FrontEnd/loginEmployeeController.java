package com.example.FrontEnd;

//import static com.example.FrontEnd.StartApplication.facade;

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

public class loginEmployeeController implements Initializable {//Liam did this
    private Stage stage;
    private Scene scene;
    public static String logNum;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML//on interface password field = password
    private PasswordField enterPasswordField;

    @FXML
    private PasswordField enterPasswordField1;

    @FXML
    private Button loginButton;

    @FXML//on interface text field = username
    private TextField usernameTextField;

    @FXML//On interface text field = security key
    private TextField employeeSecurityKey;

    @FXML
    private Button createAcc;

    @FXML
    private Button cancelButton;

    @FXML
    private Button mainMenu;

    @FXML
    private void confirmHoverInMain() {
        mainMenu.setStyle("-fx-background-color: #52779C;");
    }

    @FXML
    private void confirmHoverOutMain() {
        mainMenu.setStyle("-fx-background-color: #414D59;");
    }

    @FXML
    private void confirmHoverInCreate() {
        createAcc.setStyle("-fx-background-color: #52779C;");
    }

    @FXML
    private void confirmHoverOutCreate() {
        createAcc.setStyle("-fx-background-color: #414D59;");
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

    @FXML//on interface button = create account
    void switchToEmployeeMenu(ActionEvent event)throws IOException {
        RegisterController.num = 0;
        logNum = usernameTextField.getText();
        System.out.println(logNum);
//System.out.println(facade.checkLogin(Integer.parseInt(usernameTextField.getText()), enterPasswordField.getText()));
        if (RegisterController.facade.checkLogin(Integer.parseInt(usernameTextField.getText()), enterPasswordField.getText())){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("employeeMenu.fxml"));
            Parent root = loader.load();
            scene = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

    // Successfully made the code look for the username and password created
    @FXML//on interface button = login  //Need the unique version is your logging into your profile for the first time
    void switchToRegisterEmployee(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("registerEmployee.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    //usernameTextField.getText().toString().equals(RegisterController.id) Check ID
// enterPasswordField.getText().toString().equals(RegisterController.password check password

    @FXML//on interface button = main menu, cancel
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