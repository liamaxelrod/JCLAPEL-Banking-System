package com.example.FrontEnd;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class NewStartController implements Initializable{
    private Stage stage;
    private Scene scene;
    public static String logNum;


    @FXML
    private AnchorPane CustSlider;

    @FXML
    private AnchorPane EmployeeLog;

    @FXML
    private Button cancelButton;

    @FXML
    private Button cancelButton1;

    @FXML
    private Button customerLog;

    @FXML
    private TextField employeeSecurityKey;

    @FXML
    private PasswordField enterPasswordField;

    @FXML
    private PasswordField enterPasswordField1;

    @FXML
    private Button loginButton;

    @FXML
    private Button loginButton1;

    @FXML
    private ImageView myImageView;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField usernameTextField1;

    @FXML
    private Button EmploLogBut;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        EmployeeLog.setTranslateX(500);
        CustSlider.setTranslateX(500);


    }

    public void EmployeeButton(ActionEvent event){


            TranslateTransition slideCust = new TranslateTransition();
            slideCust.setDuration(Duration.seconds(0.9));
            slideCust.setNode(EmployeeLog);

            slideCust.setToX(0);
            slideCust.play();

            EmployeeLog.setTranslateX(500);

            slideCust.setOnFinished((ActionEvent e) -> {
                EmployeeLog.setVisible(true);
                cancelButton1.setVisible(false);

            });

    }
    public void EmployeeCloseButton(ActionEvent event){

            TranslateTransition slideCust = new TranslateTransition();
            slideCust.setDuration(Duration.seconds(0.9));
            slideCust.setNode(EmployeeLog);

            slideCust.setToX(500);
            slideCust.play();

            EmployeeLog.setTranslateX(0);

            slideCust.setOnFinished((ActionEvent e) -> {
                EmployeeLog.setVisible(true);
                cancelButton1.setVisible(false);
            });

    }
    public void CustomerButton(ActionEvent event){

            TranslateTransition slideCust = new TranslateTransition();
            slideCust.setDuration(Duration.seconds(0.9));
            slideCust.setNode(CustSlider);

            slideCust.setToX(0);
            slideCust.play();

            CustSlider.setTranslateX(500);

            slideCust.setOnFinished((ActionEvent e) -> {
                customerLog.setVisible(false);
                cancelButton.setVisible(true);
            });

    }
    public void CustomerCloseButton(ActionEvent event){
        TranslateTransition slideCust = new TranslateTransition();
        slideCust.setDuration(Duration.seconds(0.9));
        slideCust.setNode(CustSlider);

        slideCust.setToX(500);
        slideCust.play();

        CustSlider.setTranslateX(0);

        slideCust.setOnFinished((ActionEvent e) -> {
            customerLog.setVisible(true);
            cancelButton.setVisible(false);
        });
    }

    // Successfully made the code look for the username and password created
    @FXML//on interface button = login  //Need the unique version is your logging into your profile for the first time
    public void switchToCustomerMenu(ActionEvent event) throws IOException{
        RegisterController.num = 0;
        logNum = usernameTextField.getText();
        //System.out.println(facade.checkLogin(Integer.parseInt(usernameTextField.getText()), enterPasswordField.getText()));
        if (RegisterController.facade.checkLogin(Integer.parseInt(usernameTextField.getText()), enterPasswordField.getText())){

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("userMenu.fxml"));
            Parent root = loader.load();
            scene = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML//on interface button = create account
    void switchToEmployeeMenu(ActionEvent event)throws IOException {
        RegisterController.num = 0;
        logNum = usernameTextField.getText();
        System.out.println(logNum);
//System.out.println(facade.checkLogin(Integer.parseInt(usernameTextField.getText()), enterPasswordField.getText()));
        if (RegisterController.facade.checkLogin(Integer.parseInt(usernameTextField.getText()), enterPasswordField.getText())){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("employeeMenu.fxml"));
            Parent root = loader.load();
            scene = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    private void confirmHoverInLogin() {
        loginButton.setStyle("-fx-background-color: #676D5E;");
    }

    @FXML
    private void confirmHoverOutLogin() {
        loginButton.setStyle("-fx-background-color: #474B40;");
    }

    @FXML
    private void confirmHoverInCancel() {
        cancelButton.setStyle("-fx-background-color: #676D5E;");
    }

    @FXML
    private void confirmHoverOutCancel() {
        cancelButton.setStyle("-fx-background-color: #474B40;");
    }
}
