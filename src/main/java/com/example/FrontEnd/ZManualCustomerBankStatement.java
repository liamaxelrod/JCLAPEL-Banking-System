package com.example.FrontEnd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ZManualCustomerBankStatement implements Initializable {

    private Stage stage;
    private Scene scene;


    @Override//this method takes effect when the scene is loaded
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    //all methods below are for switching scenes, or you could say interfaces

    @FXML
    void switchToManualMainMenu(ActionEvent event) {

    }

    @FXML
    void switchToManualUser(ActionEvent event) {

    }

    @FXML
    void switchToManualTransfer(ActionEvent event) {

    }

    @FXML
    void switchToManualProfile(ActionEvent event) {

    }

    @FXML
    void switchToManualProject(ActionEvent event) {

    }

    @FXML
    void switchToManualBankStatement(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(""));
        Parent root = loader.load();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void switchToManualCreateAccount(ActionEvent event) {

    }

    @FXML
    void switchToManualLogin(ActionEvent event) {

    }

    //all methods below are for Open new scenes, or you could say interfaces

    @FXML
    void OpenToManualMainMenu(ActionEvent event) {

    }


    @FXML
    void OpenToManualUser(ActionEvent event) {

    }

    @FXML
    void OpenToManualTransfer(ActionEvent event) {

    }

    @FXML
    void OpenToManualProfile(ActionEvent event) {

    }

    @FXML
    void OpenToManualProjects(ActionEvent event) {

    }

    @FXML
    void OpenToManualBankStatements(ActionEvent event) {

    }


    @FXML
    void OpenToManualCreateAccount(ActionEvent event) {

    }

    @FXML
    void OpenToManualLogin(ActionEvent event) {

    }

    @FXML
    private Button closeButton;
    @FXML
    void handelCloseButtonAction(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
