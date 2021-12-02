package com.example.FrontEnd;

import com.example.BackEnd.testProfile;
import com.example.easyGoBetween.frontEndTalkToObjects;
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

public class LoginController extends accessToTheTalkToBack implements Initializable {
    public static frontEndTalkToObjects talkToGoBetween = new frontEndTalkToObjects();

    private Stage stage;
    private Scene scene;
    //The set-up Methods
    public testProfile userProfile;

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

    //The set-up Methods
//    public testProfile userProfile;
//
//    public void setUserProfile(testProfile newTestProfile){
//        userProfile = newTestProfile;
//    }
//    public void setUpProfile(){
//
//    }
    //End of set-up Methods

    //all methods below are for switching scenes, or you could say interfaces

    @FXML//on interface button = create account
    public void switchToRegisterCustomer(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("registerCustomer.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

//        userMenuController thisController = loader.getController();
//        thisController.setUserProfile(talkToGoBetween.createTestProfile());
//        thisController.setUpProfile();

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

//        userMenuController thisController = loader.getController();
//        thisController.setUserProfile(talkToGoBetween.createTestProfile());
//        thisController.setUpProfile();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
// Successfully made the code look for the username and password created
    @FXML//on interface button = login  //Need the unique version is your logging into your profile for the first time
    public void switchToCustomerMenu(ActionEvent event) throws IOException{
        if (usernameTextField.getText().toString().equals(RegisterController.idNum) && enterPasswordField.getText().toString().equals(RegisterController.password)){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("userMenu.fxml"));
            Parent root = loader.load();
            scene = new Scene(root);

            userMenuController thisController = loader.getController();
            thisController.setUserProfile(talkToGoBetween.createTestProfile());
            thisController.setUpProfile();

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }
    
}
