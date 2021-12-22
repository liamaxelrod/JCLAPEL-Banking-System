package com.example.FrontEnd;

import com.example.BackEnd.Customer;
import com.example.BackEnd.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class EmployeeMenuController implements Initializable {//Liam was most responsible for this, Albin Worked on this
    private Stage stage;
    private Scene scene;

    public static int activeID;
    private Employee currentEmployeeUse;

    @FXML//on the interface text field and label = upper left corner
    private TextField userID;
    @FXML
    private Label fullName;
    @FXML
    private Label position;

    @FXML//on the interface image view = upper right corner
    private ImageView ImageProfile;

    @FXML//on the interface label = select customer and employee
    private Label SelectedCustomer;
    @FXML
    private Label SelectedEmployee;

    @FXML//on the interface list view = right below select customer and employee
    private ListView<String> listOfCustomers;
    @FXML
    private ListView<String> listOfEmployee;

    private ObservableList<String> differentCustomers = FXCollections.observableArrayList();
    private ObservableList<String> differentEmployees = FXCollections.observableArrayList();

    private void generatorListOfCustomers(){
        HashMap<Integer, Customer> currentList = StartApplication.facade.customers;
        for (Customer currentCustomer: currentList.values()) {
            differentCustomers.add(currentCustomer.getName() + " / " + currentCustomer.getID());
        }
    }
    private void generatorlistOfEmployees(){
        HashMap<Integer, Employee> currentList = StartApplication.facade.employees;
        for (Employee employees: currentList.values()) {
            differentEmployees.add(employees.getName() + " / " + employees.getID());
        }
    }

    @Override//this method takes effect when the scene is loaded
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generatorListOfCustomers();//set up customers
        listOfCustomers.setItems(differentCustomers);
        generatorlistOfEmployees();//set up employees
        listOfEmployee.setItems(differentEmployees);

        currentEmployeeUse = StartApplication.facade.loadEmployee(activeID);
        userID.setText(String.valueOf(activeID));
        fullName.setText(currentEmployeeUse.getName());
    }

    //all methods below are for On action, or you could say on interfaces

    @FXML
    void onActionChooseCustomer(ActionEvent event) {
        SelectedCustomer.setText(listOfCustomers.getSelectionModel().getSelectedItem());
    }

    @FXML
    void onActionChooseEmployee(ActionEvent event) {
        SelectedEmployee.setText(listOfEmployee.getSelectionModel().getSelectedItem());
    }

    @FXML//On the interface button = delete customer
    void onActionDeleteCustomer(ActionEvent event) {

    }

    @FXML//On the interface button = delete Employee
    void onActionDeleteEmployee(ActionEvent event) {

    }

    @FXML//On the interface button = Promoted to Employee to manager
    void onActionPromoteEmployeeToManager(ActionEvent event) {

    }

    @FXML//On interface button = change
    private TextField changeIDTextField;
    @FXML
    void onActionChangeName(ActionEvent event) {
        if (StartApplication.facade.validateName(changeIDTextField.getText())){
            String theNewFirstName = changeIDTextField.getText();
            fullName.setText(theNewFirstName);
            changeIDTextField.setText("");
            currentEmployeeUse.setName(theNewFirstName);//This is for testing purposes until can finalize it *****
        } else {
            changeIDTextField.setText("This cannot be blank");
        }
    }

    //all methods below are for switching scenes, or you could say on interfaces

    @FXML//On interface Button = Open customer
    void switchToTheCustomer(ActionEvent event) throws IOException {//Still being worked on
        if (true){}
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("userMenu.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML//On interface Button = MANUAL
    void switchToManual(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ZZManualEmployee.fxml"));
        Parent root1 = fxmlLoader.load();
        stage = new Stage();

        stage.setTitle("full manual");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML//On interface Button = logout
    void switchToStart(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("start.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML//On interface Button = EXIT
    private Button closeButton;
    @FXML
    void handelCloseButtonAction(ActionEvent event) {

    }

    //Methods to make the buttons glow
    @FXML
    private Button deleteCustomerButton;
    @FXML
    private Button deleteEmployeeButton;
    @FXML
    private Button openCustomerButton;
    @FXML
    private Button promoteEmployeeToManagerButton;

    @FXML
    private Button logoutButton;
    @FXML
    private Button manualButton;

    public void confirmHoverEntry(MouseEvent event){
        if (deleteCustomerButton == event.getSource()) {
            deleteCustomerButton.setStyle("-fx-background-color: #52779C;");
        } else if (deleteEmployeeButton == event.getSource()) {
            deleteEmployeeButton.setStyle("-fx-background-color: #52779C;");
        } else if (openCustomerButton == event.getSource()) {
            openCustomerButton.setStyle("-fx-background-color: #52779C;");
        } else if (closeButton == event.getSource()) {
            closeButton.setStyle("-fx-background-color: #52779C;");
        } else if (logoutButton == event.getSource()) {
            logoutButton.setStyle("-fx-background-color: #52779C;");
        } else if (manualButton == event.getSource()) {
            manualButton.setStyle("-fx-background-color: #52779C;");
        } else if (promoteEmployeeToManagerButton == event.getSource()) {
            promoteEmployeeToManagerButton.setStyle("-fx-background-color: #52779C;");
        }
    }

    public void confirmHoverExit(MouseEvent event){
        if (deleteCustomerButton == event.getSource()) {
            deleteCustomerButton.setStyle("-fx-background-color: #414D59;");
        } else if (deleteEmployeeButton == event.getSource()) {
            deleteEmployeeButton.setStyle("-fx-background-color: #414D59;");
        } else if (openCustomerButton == event.getSource()) {
            openCustomerButton.setStyle("-fx-background-color: #414D59;");
        } else if (closeButton == event.getSource()) {
            closeButton.setStyle("-fx-background-color: #414D59;");
        } else if (logoutButton == event.getSource()) {
            logoutButton.setStyle("-fx-background-color: #414D59;");
        } else if (manualButton == event.getSource()) {
            manualButton.setStyle("-fx-background-color: #414D59;");
        } else if (promoteEmployeeToManagerButton == event.getSource()) {
            promoteEmployeeToManagerButton.setStyle("-fx-background-color: #414D59;");
        }
    }



}
