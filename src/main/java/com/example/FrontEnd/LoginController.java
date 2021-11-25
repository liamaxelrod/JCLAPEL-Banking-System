package com.example.FrontEnd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    private Stage stage;
    private Scene scene;

    @FXML//on interface password field = password
    private PasswordField enterPasswordField;

    @FXML//on interface text field = username
    private TextField usernameTextField;

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

    ////all methods below are for switching scenes, or you could say interfaces

    @FXML//on interface button = create account
    public void switchToRegisterCustomer(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("registerCustomer.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML//on interface button = main menu, cancel
    public void switchToStart(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("start.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML//on interface button = login
    public void switchToCustomerMenu(ActionEvent event) throws IOException{
        if (usernameTextField.getText().toString().equals("") && enterPasswordField.getText().toString().equals("")){
            Parent root = FXMLLoader.load(getClass().getResource("userMenu.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    
}
