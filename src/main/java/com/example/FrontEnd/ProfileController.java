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

    private FileChooser fileChoice;//*It is possible to get rid of the three yellow warning lights but for now I'm to leave To be safe
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
    private Label currentID;
    @FXML
    public Label currentPassword;
    @FXML
    private Label warningLabel;

    @FXML//on interface Text field = Right bottom corner
    private TextField newFirstName;
    @FXML
    private TextField newLastName;


    @Override//this method takes effect when the scene is loaded
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentCustomerUse = StartApplication.facade.loadCustomer(UserMenuController.activeID);
        currentFirstName.setText(currentCustomerUse.getName().substring(0 , currentCustomerUse.getName().indexOf(" ")));
        currentLasName.setText(currentCustomerUse.getName().substring(currentCustomerUse.getName().lastIndexOf(" ")+1));
        currentPassword.setText(currentCustomerUse.getPassword());
        currentID.setText(String.valueOf(currentCustomerUse.getID()));
    }

    @FXML
    private void onActionChangePassword(/*ActionEvent event*/) {
        String theCurrentPassword = currentPassword.getText();
        String theCheckOldPassword = checkCurrentPassword.getText();
        String theNewPassword = newPassword.getText();
        String theConfirmNewPassword = confirmNewPassword.getText();

        if (!theCurrentPassword.isBlank() && !theNewPassword.isBlank() && !theConfirmNewPassword.isBlank()){
            if (theCurrentPassword.equals(theCheckOldPassword)){
                if (theNewPassword.equals(theConfirmNewPassword)){
                    if (StartApplication.facade.validatePassword(newPassword.getText()) && StartApplication.facade.validatePassword(confirmNewPassword.getText())){
                        if (StartApplication.facade.CheckIfEmployeeExists(currentCustomerUse.getID())){
                            currentPassword.setText(theConfirmNewPassword);
                            currentCustomerUse.setPassword(theConfirmNewPassword);
                            StartApplication.facade.loadEmployee(currentCustomerUse.getID()).setPassword(theConfirmNewPassword);
                            checkCurrentPassword.setText("");
                            newPassword.setText("");
                            confirmNewPassword.setText("");
                        } else {
                            currentPassword.setText(theConfirmNewPassword);
                            currentCustomerUse.setPassword(theConfirmNewPassword);
                            checkCurrentPassword.setText("");
                            newPassword.setText("");
                            confirmNewPassword.setText("");
                        }
                    } else {
                        warningLabel.setText("The password must have: \n - At least 8 characters \n - Must consist of 'a-z, A-Z, 0 -9' \n - Special character ex. '!' '&' '?' \n You must also Enter: \n - enter your security key \n - enter your position");
                    }
                } else {
                    warningLabel.setText("does not match: \n - new and confirm password our different");
                }
            } else {
                warningLabel.setText("Does not match: \n - The old password does not match what you inputted");
            }
        } else {
            warningLabel.setText("some or all: \n - of the input boxes are blank");
        }
    }

    @FXML//On interface button = change username, first name, and last name
    void onActionChangeFirstName(/*ActionEvent event*/) {
        if (!newFirstName.getText().isBlank()){
            if (!newFirstName.getText().contains(" ")){
                if (newFirstName.getText().length() < 13 && newFirstName.getText().length() > 3){
                    if (StartApplication.facade.CheckIfEmployeeExists(currentCustomerUse.getID())){
                        String theNewFirstName = newFirstName.getText();
                        String firstPart = theNewFirstName;
                        String secondPart = currentCustomerUse.getName().substring(currentCustomerUse.getName().lastIndexOf(" ")+1);
                        currentFirstName.setText(theNewFirstName);
                        newFirstName.setText("");
                        StartApplication.facade.loadEmployee(currentCustomerUse.getID()).setName(firstPart + " " + secondPart);
                        currentCustomerUse.setName(firstPart + " " + secondPart);
                    } else {
                        String theNewFirstName = newFirstName.getText();
                        String firstPart = theNewFirstName;
                        String secondPart = currentCustomerUse.getName().substring(currentCustomerUse.getName().lastIndexOf(" ")+1);
                        currentFirstName.setText(theNewFirstName);
                        newFirstName.setText("");
                        currentCustomerUse.setName(firstPart + " " + secondPart);
                    }
                } else {
                    warningLabel.setText("it cannot be longer than 13 or less than 3 characters");
                }
            } else {
                warningLabel.setText("it cannot contain any spaces");
            }
        } else {
            warningLabel.setText("the box for new name is blank");
        }
    }

    @FXML
    void onActionChangeLastName(/*ActionEvent event*/) {//Not finished yet
        if (!newLastName.getText().isBlank()){
            if (!newLastName.getText().contains(" ")){
                if (newLastName.getText().length() < 13 && newLastName.getText().length() > 3){
                    if (StartApplication.facade.CheckIfEmployeeExists(currentCustomerUse.getID())){
                        String theNewLastName = newLastName.getText();
                        String firstPart = theNewLastName;
                        String secondPart = currentCustomerUse.getName().substring(0 , currentCustomerUse.getName().indexOf(" "));
                        currentLasName.setText(theNewLastName);
                        newLastName.setText("");
                        StartApplication.facade.loadEmployee(currentCustomerUse.getID()).setName(firstPart + " " + secondPart);
                        currentCustomerUse.setName(firstPart + " " + secondPart);
                    } else {
                        String theNewLastName = newLastName.getText();
                        String firstPart = theNewLastName;
                        String secondPart = currentCustomerUse.getName().substring(0 , currentCustomerUse.getName().indexOf(" "));
                        currentLasName.setText(theNewLastName);
                        newLastName.setText("");
                        currentCustomerUse.setName(firstPart + " " + secondPart);
                    }
                } else {
                    warningLabel.setText("it cannot be longer than 13 or less than 3 characters");
                }
            } else {
                warningLabel.setText("it cannot contain any spaces");
            }
        } else {
            warningLabel.setText("the box for new name is blank");
        }
    }

    @FXML//Still trying to figure out save the image
    public void onActionChangeProfileImage(ActionEvent event) {
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

    //Methods to make buttons glow
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
}
