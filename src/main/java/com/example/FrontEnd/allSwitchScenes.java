package com.example.FrontEnd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class allSwitchScenes {
    private Stage stage;
    private Scene scene;

    private FileChooser fileChoice;
    private File filePath;
    private Image theImage;

    public ImageView saveImage = null;

    public void theSavingOfImage(ImageView event){
        saveImage = event;
    }

    public ImageView getSaveImage() {
        return saveImage;
    }

    @FXML
    public Button closeButton;

    @FXML
    public void theSwitchToStart(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("start.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void theSwitchToLoginCustomer(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("loginCustomer.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void theSwitchToRegisterCustomer(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("registerCustomer.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void theSwitchToTransfer(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("transferCustomer.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void theSwitchToProfile(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("profile.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML//on interface button = financial projects loan, portfolio, etc.
    void theSwitchToFinanceProject(ActionEvent event) {

    }

    @FXML//on interface button = transfer history
    void theSwitchToBankStatement(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("bankStatment.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void theSwitchToTransferHistory(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("bankStatment.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void theHandelCloseButtonAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
