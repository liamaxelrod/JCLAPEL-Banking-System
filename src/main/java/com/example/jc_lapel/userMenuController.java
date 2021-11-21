package com.example.jc_lapel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class userMenuController implements Initializable {
    private Stage stage;
    private Scene scene;

    //list of the different values the choice box can have
    private ObservableList<String> differentAccounts = FXCollections
            .observableArrayList("saving","checking","other");

    @FXML//on interface button = exit
    private Button closeButton;

    @FXML//on interface image view = upper far right position
    private ImageView ImageProfile;

    @FXML//on interface label = username
    private Label Username;

    @FXML//on interface label = next to total
    private Label currentActiveAccountTotalValue;

    @FXML// on interface label = right next to current balance -
    private Label activeAccountForUser1;//onActionProperty() choice box

    @FXML// on interface label = second one below the switch account button
    private Label activeAccountForUser2;//onActionProperty() choice box

    @FXML//on interface choice box = right above switch account button
    private ChoiceBox<String> chooseAccountForUser = new ChoiceBox<>();

    @Override//this method takes effect when the scene is loaded
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chooseAccountForUser.setValue("choose account");//not working don't know how to make it show?????????
        chooseAccountForUser.setItems(differentAccounts);

    }

    @FXML//on interface button = switch accounts
    void onActionSwitchAccounts(ActionEvent event) {
        activeAccountForUser1.setText(chooseAccountForUser.getValue());
        activeAccountForUser2.setText(chooseAccountForUser.getValue());
    }



    ////all methods below are for switching scenes, or you could say interfaces

    @FXML//on interface button = transfer
    void switchToTransfer(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("transferCustomer.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML//on interface button = profile
    void switchToProfile(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("profile.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML//on interface button = financial projects loan, portfolio, etc.
    void switchToFinanceProject(ActionEvent event) {

    }

    @FXML//on interface button = logout
    void switchToStart(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("start.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML//on interface button = exit
    void handelCloseButtonAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    @FXML//on interface button = transfer history
    void switchToBankStatement(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("bankStatment.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}