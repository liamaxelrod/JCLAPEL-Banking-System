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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController extends AccessToTheTalkToBack implements Initializable {
    public static LoginController object = new LoginController();
    private Stage stage;
    private Scene scene;


    @Override//this method takes effect when the scene is loaded
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private FileChooser fileChoice;
    private File filePath;
    @FXML//on interface image view = right above upload a user profile image
    private ImageView currentImage;//this is the one actually holds the image for the interface
    private Image theImage;

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

//    //The set-up Methods
//    public TestProfile userProfile;
//
//    public void setUserProfile(TestProfile newTestProfile){
//        userProfile = newTestProfile;
//    }
//    public void setUpProfile(){
//        currentFirstName.setText(RegisterController.firstName);
//        currentLasName.setText(RegisterController.secondName);
//        currentUsername.setText(RegisterController.iD);
//        currentPassword.setText(RegisterController.password);
//    }
//    public TestProfile getUserProfile() {
//        return userProfile;
//    }
//    //End of set-up Methods

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

//                userProfile.setPassword(currentPassword.getText());
            }
        }
    }

    @FXML//On interface button = change username, first name, and last name
    void onActionChangeFirstName(ActionEvent event) {
        String theNewFirstName = newFirstName.getText();
        currentFirstName.setText(theNewFirstName);
        newFirstName.setText("");

//        userProfile.setFirstName(currentFirstName.getText());
    }
    @FXML
    void onActionChangeLastName(ActionEvent event) {
        String theNewLastName = newLastName.getText();
        currentLasName.setText(theNewLastName);
        newFirstName.setText("");

//        userProfile.setLastName(currentLasName.getText());
    }
    @FXML
    private void onActionChangeUsername(ActionEvent event) {
        String theNewUsername = newUsername.getText();
        currentUsername.setText(theNewUsername);
        newUsername.setText("");

//        userProfile.setUserName(currentUsername.getText());
    }

    @FXML//Still trying to figure out save the image
    public void onActionChangeProfileImage(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        fileChoice = new FileChooser();
        fileChoice.setTitle("by the power of God Liam you humble peasant may choose your profile picture");

        this.filePath = fileChoice.showOpenDialog(stage);
        theImage = new Image(String.valueOf(filePath.toURI()));
        currentImage.setImage(theImage);

//        userProfile.setTheImageView(currentImage);

    }

    //all methods below are for switching scenes, or you could say interfaces

    @FXML//on interface button = user menu
    void switchToCustomerMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("userMenu.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

//        UserMenuController thisController = loader.getController();
//        thisController.setUserProfile(getUserProfile());
//        thisController.setUpProfile();

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

}