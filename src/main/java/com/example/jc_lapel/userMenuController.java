package com.example.jc_lapel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class userMenuController extends allSwitchScenes implements Initializable {
    private Stage stage;
    private Scene scene;

    @FXML//on interface button = exit
    private Button closeButton;

    @FXML//on interface image view = upper far right position
    private ImageView ImageProfile;

    @FXML//on interface label = username
    private Label userName;

    @FXML//on interface label = next to name:
    private Label fullRealName;

    @FXML//on interface label = bottom left corner
    private Label CheckingTotal;
    @FXML
    private Label OtherTotal;
    @FXML
    private Label SavingTotal;


    @Override//this method takes effect when the scene is loaded
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    ////all methods below are for switching scenes, or you could say interfaces
    @FXML//on interface button = transfer
    void switchToTransfer(ActionEvent event) throws IOException {
        theSwitchToTransfer(event);
    }
    @FXML//on interface button = profile
    void switchToProfile(ActionEvent event) throws IOException {
        theSwitchToProfile(event);
    }
    @FXML//on interface button = financial projects loan, portfolio, etc.
    void switchToFinanceProject(ActionEvent event) {
        theSwitchToFinanceProject(event);
    }
    @FXML//on interface button = logout
    void switchToStart(ActionEvent event) throws IOException {
        theSwitchToStart(event);
    }
    @FXML//on interface button = exit
    void handelCloseButtonAction(ActionEvent event) throws IOException {
        theHandelCloseButtonAction(event);
    }
    @FXML//on interface button = transfer history
    void switchToBankStatement(ActionEvent event) throws IOException {
        theSwitchToBankStatement(event);
    }
    @FXML
    void switchToManual(ActionEvent event) throws IOException {

    }

}