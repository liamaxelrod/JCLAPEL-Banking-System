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

public class ZManualCustomerProject {

    private Stage stage;
    private Scene scene;

    //all methods below are for switching scenes, or you could say interfaces

    @FXML
    void switchToManualMainMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("zManualCustomerStart.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void switchToManualUser(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("zManualCustomerUserMenu.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void switchToManualTransfer(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("zManualCustomerTransfer.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void switchToManualProfile(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("zManualCustomerProfile.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void switchToManualProject(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("zManualCustomerProject.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void switchToManualBankStatement(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("zManualCustomerBankStatement.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void switchToManualCreateAccount(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("zManualCustomerRegisterCustomer.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void switchToManualLogin(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("zManualCustomerLogin.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    //all methods below are for Open new scenes, or you could say interfaces
    /* If there's a problem reinstate this in my fix it
    void OpenToManualLogin({[{ActionEvent event}]}) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("zManualCustomerLogin.fxml"));
        Parent root1 = {[{(Parent)}]} fxmlLoader.load();
     */
    @FXML
    void OpenToManualMainMenu() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("zManualCustomerStart.fxml"));
        Parent root1 = fxmlLoader.load();
        stage = new Stage();

        stage.setTitle("full manual");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    void OpenToManualUser() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("zManualCustomerUserMenu.fxml"));
        Parent root1 = fxmlLoader.load();
        stage = new Stage();

        stage.setTitle("full manual");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    void OpenToManualTransfer() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("zManualCustomerTransfer.fxml"));
        Parent root1 = fxmlLoader.load();
        stage = new Stage();

        stage.setTitle("full manual");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    void OpenToManualProfile() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("zManualCustomerProfile.fxml"));
        Parent root1 = fxmlLoader.load();
        stage = new Stage();

        stage.setTitle("full manual");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    void OpenToManualProjects() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("zManualCustomerProject.fxml"));
        Parent root1 = fxmlLoader.load();
        stage = new Stage();

        stage.setTitle("full manual");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    void OpenToManualBankStatements() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("zManualCustomerBankStatement.fxml"));
        Parent root1 = fxmlLoader.load();
        stage = new Stage();

        stage.setTitle("full manual");
        stage.setScene(new Scene(root1));
        stage.show();
    }


    @FXML
    void OpenToManualCreateAccount() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("zManualCustomerRegisterCustomer.fxml"));
        Parent root1 = fxmlLoader.load();
        stage = new Stage();

        stage.setTitle("full manual");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    void OpenToManualLogin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("zManualCustomerLogin.fxml"));
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
    private Button openBSMButton;

    @FXML
    private Button openCAMButton;

    @FXML
    private Button openLMButton;

    @FXML
    private Button openMMMButton;

    @FXML
    private Button openProfileMButton;

    @FXML
    private Button openProjectMButton;

    @FXML
    private Button openTMButton;

    @FXML
    private Button openUMButton;

    @FXML
    private Button switchintBSMButton;

    @FXML
    private Button switchintCAMButton;

    @FXML
    private Button switchintLMButton;

    @FXML
    private Button switchintMMMButton;

    @FXML
    private Button switchintProfileMButton;

    @FXML
    private Button switchintProjectMButton;

    @FXML
    private Button switchintTMButton;

    @FXML
    private Button switchintUSButton;

    @FXML
    void confirmHoverEntry(MouseEvent event) {
        if (openBSMButton == event.getSource()) {
            openBSMButton.setStyle("-fx-background-color: #52779C;");
        } else if (openCAMButton == event.getSource()) {
            openCAMButton.setStyle("-fx-background-color: #52779C;");
        } else if (openLMButton == event.getSource()) {
            openLMButton.setStyle("-fx-background-color: #52779C;");
        } else if (openMMMButton == event.getSource()) {
            openMMMButton.setStyle("-fx-background-color: #52779C;");
        } else if (openProfileMButton == event.getSource()) {
            openProfileMButton.setStyle("-fx-background-color: #52779C;");
        } else if (openProjectMButton == event.getSource()) {
            openProjectMButton.setStyle("-fx-background-color: #52779C;");
        } else if (openTMButton == event.getSource()) {
            openTMButton.setStyle("-fx-background-color: #52779C;");
        } else if (openUMButton == event.getSource()) {
            openUMButton.setStyle("-fx-background-color: #52779C;");
        } else if (switchintBSMButton == event.getSource()) {
            switchintBSMButton.setStyle("-fx-background-color: #52779C;");
        } else if (switchintCAMButton == event.getSource()) {
            switchintCAMButton.setStyle("-fx-background-color: #52779C;");
        } else if (switchintLMButton == event.getSource()) {
            switchintLMButton.setStyle("-fx-background-color: #52779C;");
        } else if (switchintMMMButton == event.getSource()) {
            switchintMMMButton.setStyle("-fx-background-color: #52779C;");
        } else if (switchintProfileMButton == event.getSource()) {
            switchintProfileMButton.setStyle("-fx-background-color: #52779C;");
        } else if (switchintProjectMButton == event.getSource()) {
            switchintProjectMButton.setStyle("-fx-background-color: #52779C;");
        } else if (switchintTMButton == event.getSource()) {
            switchintTMButton.setStyle("-fx-background-color: #52779C;");
        } else if (switchintUSButton == event.getSource()) {
            switchintUSButton.setStyle("-fx-background-color: #52779C;");
        } else if (closeButton == event.getSource()) {
            closeButton.setStyle("-fx-background-color: #52779C;");
        }
    }

    @FXML
    void confirmHoverExit(MouseEvent event) {
        if (openBSMButton == event.getSource()) {
            openBSMButton.setStyle("-fx-background-color: #414D59;");
        } else if (openCAMButton == event.getSource()) {
            openCAMButton.setStyle("-fx-background-color: #414D59;");
        } else if (openLMButton == event.getSource()) {
            openLMButton.setStyle("-fx-background-color: #414D59;");
        } else if (openMMMButton == event.getSource()) {
            openMMMButton.setStyle("-fx-background-color: #414D59;");
        } else if (openProfileMButton == event.getSource()) {
            openProfileMButton.setStyle("-fx-background-color: #414D59;");
        } else if (openProjectMButton == event.getSource()) {
            openProjectMButton.setStyle("-fx-background-color: #414D59;");
        } else if (openTMButton == event.getSource()) {
            openTMButton.setStyle("-fx-background-color: #414D59;");
        } else if (openUMButton == event.getSource()) {
            openUMButton.setStyle("-fx-background-color: #414D59;");
        } else if (switchintBSMButton == event.getSource()) {
            switchintBSMButton.setStyle("-fx-background-color: #414D59;");
        } else if (switchintCAMButton == event.getSource()) {
            switchintCAMButton.setStyle("-fx-background-color: #414D59;");
        } else if (switchintLMButton == event.getSource()) {
            switchintLMButton.setStyle("-fx-background-color: #414D59;");
        } else if (switchintMMMButton == event.getSource()) {
            switchintMMMButton.setStyle("-fx-background-color: #414D59;");
        } else if (switchintProfileMButton == event.getSource()) {
            switchintProfileMButton.setStyle("-fx-background-color: #414D59;");
        } else if (switchintProjectMButton == event.getSource()) {
            switchintProjectMButton.setStyle("-fx-background-color: #414D59;");
        } else if (switchintTMButton == event.getSource()) {
            switchintTMButton.setStyle("-fx-background-color: #414D59;");
        } else if (switchintUSButton == event.getSource()) {
            switchintUSButton.setStyle("-fx-background-color: #414D59;");
        } else if (closeButton == event.getSource()) {
            closeButton.setStyle("-fx-background-color: #414D59;");
        }
    }
}
