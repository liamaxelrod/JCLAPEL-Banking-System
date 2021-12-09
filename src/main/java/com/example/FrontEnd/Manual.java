package com.example.FrontEnd;

import com.example.BackEnd.Facade;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Manual implements Initializable {//Liam created

    private Stage stage;
    private Scene scene;

    public int id;

    static Facade facade = new Facade();

    @FXML
    private Label checkIfLoggedIn;


    @Override//this method takes effect when the scene is loaded
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    ////all methods below are for switching scenes, or you could say interfaces

    @FXML//on interface button = user menu
    public void switchToCustomerMenu(ActionEvent event) throws IOException{
        if (facade.CheckIfAccountExists(id)){//this if statement is always false??
            checkIfLoggedIn.setText("you are not logged in");
        } else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("userMenu.fxml"));
            Parent root = loader.load();
            scene = new Scene(root);

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML//on interface button = logout
    void switchToStart(ActionEvent event) throws IOException {//need to log out the user
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("start.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML//on interface button = exit
    private Button closeButton;
    @FXML//on interface button = exit
    void handelCloseButtonAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();

    }


}
