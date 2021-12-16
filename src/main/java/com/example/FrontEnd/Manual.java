package com.example.FrontEnd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Manual implements Initializable {//Liam created

    private Stage stage;
    private Scene scene;

    @FXML
    private Button closeButton;


    @Override//this method takes effect when the scene is loaded
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    ////all methods below are for switching scenes, or you could say interfaces

    @FXML
    void switchTo(ActionEvent event) {

    }


    @FXML
    void handelCloseButtonAction(ActionEvent event) {

    }







}
