package com.example.FrontEnd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController extends Listener {//Albin worked on this, Liam worked partly on this
    private Stage stage;
    private Scene scene;

    @FXML
    public Button closeButton;

    //all methods below are for switching scenes, or you could say interfaces

    @FXML//on interface button = main menu
    public void switchToLoginCustomer(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("loginCustomer.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML//on interface button = create account
    public void switchToRegisterCustomer(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("registerCustomer.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void switchToLoginEmployee(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("employeeLogin.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML//on interface button = create account
    public void switchToRegisterEmployee(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("registerEmployee.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void switchToManual(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("manual.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        stage = new Stage();

        stage.setTitle("full manual");
        stage.setScene(new Scene(root1));
        stage.show();

        //old version
//      FXMLLoader loader = new FXMLLoader();
//      loader.setLocation(getClass().getResource("manual.fxml"));
//      Parent root = loader.load();
//      scene = new Scene(root);
//
//      stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//      stage.setScene(scene);
//      stage.show();
    }

    @FXML//on interface button = exit
    public void handelCloseButtonAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
