package com.example.FrontEnd;

import com.example.BackEnd.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class ProfileController implements Initializable {//Albin Worked on this Liam worked more on this

    private Stage stage;
    private Scene scene;
    private Customer currentCustomerUse;


    @Override//this method takes effect when the scene is loaded
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentCustomerUse = RegisterController.facade.loadCustomer(RegisterController.idNum);
        currentFirstName.setText(currentCustomerUse.getName());
        currentLasName.setText(currentCustomerUse.getName());//Need to get lasting here
        currentPassword.setText(currentCustomerUse.getPassword());
    }


    private FileChooser fileChoice;
    private File filePath;
    @FXML//on interface image view = right above upload a user profile image
    private ImageView currentImage;//this is the one actually holds the image for the interface
    private Image theImage;

    @FXML//on interface label = username
    private TextField userName;

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
    private Label currentID;
    @FXML
    public Label currentPassword;



    @FXML//on interface Text field = Right bottom corner
    private TextField newFirstName;
    @FXML
    private TextField newLastName;


    @FXML
    private Button SignOut;

    @FXML
    private Button userMenu;

    @FXML
    private void confirmHoverInSignO() {
        SignOut.setStyle("-fx-background-color: #52779C;");
    }

    @FXML
    private void confirmHoverOutSignO() {
        SignOut.setStyle("-fx-background-color: #414D59;");
    }

    @FXML
    private void confirmHoverInUser() {
        userMenu.setStyle("-fx-background-color: #52779C;");
    }

    @FXML
    private void confirmHoverOutUser() {
        userMenu.setStyle("-fx-background-color: #414D59;");
    }
    


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
        currentCustomerUse.setName(theNewFirstName);//This is for testing purposes until can finalize it *****
    }

    @FXML
    void onActionChangeLastName(ActionEvent event) {
        String theNewLastName = newLastName.getText();
        currentLasName.setText(theNewLastName);
        newFirstName.setText("");
    }

    @FXML//Still trying to figure out save the image
    public void onActionChangeProfileImage(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        fileChoice = new FileChooser();
        fileChoice.setTitle("by the power of God Liam you humble peasant may choose your profile picture");

        this.filePath = fileChoice.showOpenDialog(stage);
        theImage = new Image(String.valueOf(filePath.toURI()));
        currentImage.setImage(theImage);
    }

    //all methods below are for switching scenes, or you could say interfaces

    @FXML//on interface button = user menu
    void switchToCustomerMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("userMenu.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

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
