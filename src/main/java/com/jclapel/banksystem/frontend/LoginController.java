package com.jclapel.banksystem.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;

    @Override
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
    public void switchToRegisterCustomer(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("registerCustomer.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToHelloView(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

//<<<<<<< HEAD:src/main/java/com/example/jc_lapel/LoginController.java
//    //SceneBuilder interface login button
//    public void switchToCustomerMenu(ActionEvent event) throws IOException{
//        if (usernameTextField.getText() == "" && enterPasswordField.getText() == ""){
//            Parent root = FXMLLoader.load(getClass().getResource("userMenuController.fxml"));
//            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//            scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//        }
//=======
//    public void switchToTransferCustomer(ActionEvent event) throws IOException{
//        Parent root = FXMLLoader.load(getClass().getResource("transferCustomer.fxml"));
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//>>>>>>> cd679941ca1289713410482b5906fd23c843cc3d:src/main/java/com/jclapel/banksystem/frontend/LoginController.java
//    }
}
