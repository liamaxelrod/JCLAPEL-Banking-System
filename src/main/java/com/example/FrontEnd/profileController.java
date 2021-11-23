package com.example.FrontEnd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class profileController implements Initializable {
    private Stage stage;
    private Scene scene;


    @Override//this method takes effect when the scene is loaded
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML//on interface Password field = Bottom left corner
    private PasswordField checkCurrentPassword;
    @FXML
    private PasswordField newPassword;
    @FXML
    private PasswordField confirmNewPassword;

    @FXML//on interface Label = Top left corner
    public Label currentFirstName;
    @FXML
    public Label currentLasName;
    @FXML
    public Label currentUsername;
    @FXML
    public Label currentPassword;

    @FXML//on interface Text field = Right bottom corner
    private TextField newUsername;
    @FXML
    private TextField newFirstName;
    @FXML
    private TextField newLastName;

    @FXML
    private void onActionChangePassword(ActionEvent event) {
        String theCurrentPassword = currentPassword.getText();// This one's the label
        String theCheckOldPassword = checkCurrentPassword.getText();// The rest Are passwordFeel
        String theNewPassword = newPassword.getText();
        String theConfirmNewPassword = confirmNewPassword.getText();
        if (theCurrentPassword.equals(theCheckOldPassword)){
            if (theNewPassword.equals(theConfirmNewPassword)){
                currentPassword.setText(theConfirmNewPassword);
                checkCurrentPassword.setText("");
                newPassword.setText("");
                confirmNewPassword.setText("");
            }
        }
    }

    @FXML//On interface button = change username, first name, and last name
    void onActionChangeFirstName(ActionEvent event) {
        String theNewFirstName = newFirstName.getText();
        currentFirstName.setText(theNewFirstName);
        newFirstName.setText("");
    }
    @FXML
    void onActionChangeLastName(ActionEvent event) {
        String theNewLastName = newLastName.getText();
        currentLasName.setText(theNewLastName);
        newFirstName.setText("");
    }
    @FXML
    private void onActionChangeUsername(ActionEvent event) {
        String theNewUsername = newUsername.getText();
        currentUsername.setText(theNewUsername);
        newUsername.setText("");
    }

//    @FXML
//    private void onActionChangeProfileImage(ActionEvent event) {
//        getFilenameFilter();
//    }

    public void onActionChangeProfileImage(ActionEvent event) {
    }
    ////all methods below are for switching scenes, or you could say interfaces

    @FXML//on interface button = user menu
    void switchToCustomerMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("userMenu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML//on interface button = sign out
    public void switchToStart(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("start.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
