package com.example.FrontEnd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ZZManualEmployeeRegisterController {

    private Stage stage;
    private Scene scene;

    //all methods below are for switching scenes, or you could say interfaces

    @FXML
    void switchToManualEmployeeLogin(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ZZManualEmployeeLogin.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void switchToManualEmployeeMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ZZManualEmployeeMenu.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    //all methods below are for Open new scenes, or you could say interfaces

    @FXML
    void openToManualEmployeeLogin(/*ActionEvent event*/) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ZZManualEmployeeLogin.fxml"));
        Parent root1 = fxmlLoader.load();
        stage = new Stage();

        stage.setTitle("full manual");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    void openToManualEmployeeMenu(/*ActionEvent event*/) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ZZManualEmployeeMenu.fxml"));
        Parent root1 = fxmlLoader.load();
        stage = new Stage();

        stage.setTitle("full manual");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    private Button closeButton;
    @FXML
    void handelCloseButtonAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    //All methods below are for Special effects On the interface
    @FXML
    private Button openELMButton;
    @FXML
    private Button openEMMButton;
    @FXML
    private Button switchELMButton;
    @FXML
    private Button switchEMMButton;

    @FXML
    void confirmHoverEntry(MouseEvent event) {
        if (openELMButton == event.getSource()) {
            openELMButton.setStyle("-fx-background-color: #52779C;");
        } else if (openEMMButton == event.getSource()) {
            openEMMButton.setStyle("-fx-background-color: #52779C;");
        } else if (switchELMButton == event.getSource()) {
            switchELMButton.setStyle("-fx-background-color: #52779C;");
        } else if (switchEMMButton == event.getSource()) {
            switchEMMButton.setStyle("-fx-background-color: #52779C;");
        } else if (closeButton == event.getSource()) {
            closeButton.setStyle("-fx-background-color: #52779C;");
        }
    }

    @FXML
    void confirmHoverExit(MouseEvent event) {
        if (openELMButton == event.getSource()) {
            openELMButton.setStyle("-fx-background-color: #414D59;");
        } else if (openEMMButton == event.getSource()) {
            openEMMButton.setStyle("-fx-background-color: #414D59;");
        } else if (switchELMButton == event.getSource()) {
            switchELMButton.setStyle("-fx-background-color: #414D59;");
        } else if (switchEMMButton == event.getSource()) {
            switchEMMButton.setStyle("-fx-background-color: #414D59;");
        }  else if (closeButton == event.getSource()) {
            closeButton.setStyle("-fx-background-color: #414D59;");
        }
    }
}