package com.example.FrontEnd;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class UserMenuController implements Initializable {//Liam was most responsible for this, Albin Worked on this

    private Stage stage;
    private Scene scene;


    @FXML//on interface button = exit
    private Button closeButton;

    @FXML//on interface image view = upper far right position
    private ImageView imageProfile;

    @FXML//on interface label = username
    private TextField userName;

    @FXML//on interface label = next to name:
    private String fullRealName;

    @FXML//on interface label = bottom left corner
    private Label CheckingTotal;
    @FXML
    private Label OtherTotal;
    @FXML
    private Label SavingTotal;

    @FXML
    private Label fullName;

    @FXML//on interface text field = username
    private int usernameTextField;


    @Override//this method takes effect when the scene is loaded
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if(RegisterController.num == 1){
            userName.setText(String.valueOf(RegisterController.idNum));
        }else{
            userName.setText(String.valueOf(LoginController.logNum));
        }
        String name = (RegisterController.facade.loadCustomer(Integer.parseInt(userName.getText())).getName());
        fullName.setText(name);
    }

    ////all methods below are for switching scenes, or you could say interfaces

    @FXML//on interface button = transfer
    void switchToTransfer(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("transferCustomer.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML//on interface button = profile
    void switchToProfile(ActionEvent event) throws IOException {//Test profile Connected
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("profile.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML//on interface button = financial projects loan, portfolio, etc.
    void switchToFinanceProject(ActionEvent event) {

    }
    @FXML//on interface button = logout
    void switchToStart(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("start.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML//on interface button = transfer history
    void switchToBankStatement(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("bankStatment.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void switchToManual(ActionEvent event) throws IOException {

    }

    @FXML//on interface button = exit
    void handelCloseButtonAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();

    }


}