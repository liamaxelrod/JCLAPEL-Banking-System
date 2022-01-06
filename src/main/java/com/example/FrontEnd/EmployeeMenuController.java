package com.example.FrontEnd;

import com.example.BackEnd.Account;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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

    public static int inUseEmployeeActiveID;
    private Employee currentEmployeeUse;

    @FXML//on the interface text field and label = upper left corner
    private TextField userID;
    @FXML
    private Label warningLabel;
    @FXML
    private Label fullName;
    @FXML
    private Label position;
    @FXML
    private Label securityKey1;
    @FXML
    private Label securityKey2;
    @FXML
    private Label securityKey3;
    @FXML
    private TextField changeKey;


    @FXML//on the interface image view = upper right corner
    private ImageView ImageProfile;

    @FXML//on the interface label = select customer and employee
    private Label selectedCustomerID;
    @FXML
    private Label selectedEmployeeID;
    @FXML
    private Label customerInfo;
    @FXML
    private Label employeeInfo;


    @FXML//on the interface list view = right below select customer and employee
    private ListView<String> listOfCustomers;
    @FXML
    private ListView<String> listOfEmployee;

    private ObservableList<String> differentCustomers = FXCollections.observableArrayList();
    private ObservableList<String> differentEmployees = FXCollections.observableArrayList();

    private void generatorListOfCustomers(){
        HashMap<Integer, Customer> currentList = StartApplication.facade.customers;
        for (Customer currentCustomer: currentList.values()) {
            differentCustomers.add(currentCustomer.getID() + " / " + currentCustomer.getName());
        }
    }
    private void generatorListOfEmployees(){
        HashMap<Integer, Employee> currentList = StartApplication.facade.employees;
        for (Employee employees: currentList.values()) {
            differentEmployees.add(employees.getID() + " / " + employees.getName());
        }
    }

    @Override//this method takes effect when the scene is loaded
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generatorListOfCustomers();//set up customers
        listOfCustomers.setItems(differentCustomers);
        generatorListOfEmployees();//set up employees
        listOfEmployee.setItems(differentEmployees);

        currentEmployeeUse = StartApplication.facade.loadEmployee(inUseEmployeeActiveID);
        position.setText(currentEmployeeUse.getPosition());
        userID.setText(String.valueOf(inUseEmployeeActiveID));
        fullName.setText(currentEmployeeUse.getName());
        String checkPosition = position.getText();
        if (checkPosition == "Employee"){
            securityKey1.setText("don't have access");
            securityKey2.setText("don't have access");
            securityKey3.setText("don't have access");
        } else if (checkPosition == "Manager") {
            securityKey1.setText(String.valueOf(StartApplication.securityKey1));
            securityKey2.setText("don't have access");
            securityKey3.setText("don't have access");
        } else if (checkPosition == "Admin") {
            securityKey1.setText(String.valueOf(StartApplication.securityKey1));
            securityKey2.setText(String.valueOf(StartApplication.securityKey2));
            securityKey3.setText(String.valueOf(StartApplication.securityKey3));
        }
    }

    //all methods below are for On action, or you could say on interfaces

    @FXML
    void onActionChangeSecurityKey1(ActionEvent event) throws IOException {
        String checkPosition = position.getText();
        if (!changeKey.getText().isEmpty()){
            if (checkPosition == "Admin" || checkPosition == "Manager"){
                int key = Integer.parseInt(changeKey.getText());
                StartApplication.securityKey1 = key;
                switchToTheSameSceneRefresh(event);
            } else {
                warningLabel.setText("Not high enough position");
            }
        } else {
            warningLabel.setText("Change key is empty");
        }
    }
    @FXML
    void onActionChangeSecurityKey2(ActionEvent event) throws IOException {
        String checkPosition = position.getText();
        if (!changeKey.getText().isEmpty()){
            if (checkPosition == "Admin"){
                int key = Integer.parseInt(changeKey.getText());
                StartApplication.securityKey2 = key;
                switchToTheSameSceneRefresh(event);
            } else {
                warningLabel.setText("Not high enough position");
            }
        } else {
            warningLabel.setText("Change key is empty");
        }
    }

    @FXML
    void onActionChangeSecurityKey3(ActionEvent event) throws IOException {
        String checkPosition = position.getText();
        if (!changeKey.getText().isEmpty()){
            if (checkPosition == "Admin"){
                int key = Integer.parseInt(changeKey.getText());
                StartApplication.securityKey3 = key;
                switchToTheSameSceneRefresh(event);
            } else {
                warningLabel.setText("Not high enough position");
            }
        } else {
            warningLabel.setText("Change key is empty");
        }
    }

    @FXML
    void onActionChooseCustomer(/*ActionEvent event*/) {
        String ID = listOfCustomers.getSelectionModel().getSelectedItem().substring(0,6);
        String name = listOfCustomers.getSelectionModel().getSelectedItem().substring(9);
        selectedCustomerID.setText(ID);
        customerInfo.setText(name);
    }

    @FXML
    void onActionChooseEmployee(/*ActionEvent event*/) {
        String ID = listOfEmployee.getSelectionModel().getSelectedItem().substring(0,6);
        String name = listOfEmployee.getSelectionModel().getSelectedItem().substring(9);
        selectedEmployeeID.setText(ID);
        employeeInfo.setText(name);
    }

    @FXML//On the interface button = delete customer
    void onActionDeleteCustomer(ActionEvent event) throws IOException {
        if (customerInfo.getText() == ""){
            customerInfo.setText("select a customer");
            selectedCustomerID.setText("Select ID");
        } else if (StartApplication.facade.CheckIfEmployeeExists(Integer.parseInt(selectedCustomerID.getText()))){
            customerInfo.setText("Employee version");
            selectedCustomerID.setText("Delete");
        } else if (StartApplication.facade.loadCustomer(Integer.parseInt(selectedCustomerID.getText())).getAccounts().size() != 0){
            customerInfo.setText("Attached accounts");
            selectedCustomerID.setText("Delete");
        } else if (selectedCustomerID.getText().length() == 6){

            Customer theCustomer = StartApplication.facade.loadCustomer(Integer.parseInt(selectedCustomerID.getText()));

            for (Account accounts : theCustomer.getAccounts().values()){
                int accountID = accounts.getID();
                StartApplication.facade.removeAccount(accountID);
            }
            StartApplication.facade.removeCustomer(Integer.parseInt(selectedCustomerID.getText()));

            switchToTheSameSceneRefresh(event);
        }
    }

    @FXML//On the interface button = delete Employee
    void onActionDeleteEmployee(ActionEvent event) throws IOException {
        if (employeeInfo.getText() == ""){
            employeeInfo.setText("Select a employee");
            selectedEmployeeID.setText("Select ID");
        } else if (Integer.parseInt(selectedEmployeeID.getText()) == currentEmployeeUse.getID()){
            employeeInfo.setText("Select someone else");
            selectedEmployeeID.setText("not you");
        } else if (StartApplication.facade.loadCustomer(Integer.parseInt(selectedEmployeeID.getText())).getAccounts().size() != 0){
            employeeInfo.setText("Attached accounts");
            selectedEmployeeID.setText("Delete");
        } else if (selectedEmployeeID.getText().length() == 6){

            int employeeID = Integer.parseInt(selectedEmployeeID.getText());

            if (employeeID != inUseEmployeeActiveID){

                Customer theCustomer = StartApplication.facade.loadCustomer(employeeID);

                for (Account accounts : theCustomer.getAccounts().values()){
                    int accountID = accounts.getID();
                    StartApplication.facade.removeAccount(accountID);
                }
                StartApplication.facade.removeCustomer(employeeID);

                StartApplication.facade.removeEmployee(employeeID);
                switchToTheSameSceneRefresh(event);
            }
        }
    }

    @FXML
    void onActionDeleteCustomerLevel2(ActionEvent event) throws IOException {
        String checkPosition = position.getText();
        if (customerInfo.getText() == ""){
            customerInfo.setText("select a customer");
            selectedCustomerID.setText("Select ID");
        } else if (StartApplication.facade.CheckIfEmployeeExists(Integer.parseInt(selectedCustomerID.getText()))){
            customerInfo.setText("Employee version");
            selectedCustomerID.setText("Delete");
        } else if (selectedCustomerID.getText().length() == 6 && checkPosition == "Manager" || checkPosition == "Admin"){

            Customer theCustomer = StartApplication.facade.loadCustomer(Integer.parseInt(selectedCustomerID.getText()));

            for (Account accounts : theCustomer.getAccounts().values()){
                int accountID = accounts.getID();
                StartApplication.facade.removeAccount(accountID);
            }
            StartApplication.facade.removeCustomer(Integer.parseInt(selectedCustomerID.getText()));

            switchToTheSameSceneRefresh(event);
        } else {
            customerInfo.setText("Need higher position");
            selectedCustomerID.setText("Denied");
        }
    }


    @FXML
    void onActionDeleteEmployeeLevel2(ActionEvent event) throws IOException {
        String checkPosition = position.getText();
        if (employeeInfo.getText() == ""){
            employeeInfo.setText("Select a employee");
            selectedEmployeeID.setText("Select ID");
        } else if (Integer.parseInt(selectedEmployeeID.getText()) == currentEmployeeUse.getID()){
            employeeInfo.setText("Select someone else");
            selectedEmployeeID.setText("not you");
        } else if (selectedEmployeeID.getText().length() == 6 && checkPosition == "Manager" || checkPosition == "Admin"){

            int employeeID = Integer.parseInt(selectedEmployeeID.getText());

            if (employeeID != inUseEmployeeActiveID){

                Customer theCustomer = StartApplication.facade.loadCustomer(employeeID);

                for (Account accounts : theCustomer.getAccounts().values()){
                    int accountID = accounts.getID();
                    StartApplication.facade.removeAccount(accountID);
                }
                StartApplication.facade.removeCustomer(employeeID);

                StartApplication.facade.removeEmployee(employeeID);
                switchToTheSameSceneRefresh(event);
            }
        } else {
            employeeInfo.setText("Need higher position");
            selectedEmployeeID.setText("Denied");
        }
    }

    //all methods below are for switching scenes, or you could say on interfaces

    @FXML//On interface Button = Open customer
    void switchToTheCustomer(/*ActionEvent event*/) throws IOException {
        if (selectedCustomerID.getText().length() == 6){
            UserMenuController.activeID = Integer.parseInt(selectedCustomerID.getText());

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("userMenu.fxml"));
            Parent root1 = fxmlLoader.load();
            stage = new Stage();

            stage.setTitle("full manual");
            stage.setScene(new Scene(root1));
            stage.show();
        } else {
            customerInfo.setText("select a customer");
            selectedCustomerID.setText("Select ID");
        }
    }

    @FXML
    void switchToTheSameSceneRefresh(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("employeeMenu.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML//On interface Button = MANUAL
    void switchToManual(/*ActionEvent event*/) throws IOException {
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
    void handelCloseButtonAction(/*ActionEvent event*/) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    //Methods to make the buttons glow
    @FXML
    private Button deleteCustomerButton;
    @FXML
    private Button deleteEmployeeButton;
    @FXML
    private Button openCustomerButton;
    @FXML
    private Button deleteCustomerButton2;
    @FXML
    private Button deleteEmployeeButton2;
    @FXML
    private Button logoutButton;
    @FXML
    private Button manualButton;
    @FXML
    private Button Refresh;
    @FXML
    private Button changeSecurityKeyButton1;
    @FXML
    private Button changeSecurityKeyButton2;
    @FXML
    private Button changeSecurityKeyButton3;
    @FXML
    private Button changeSecurityKeyButton11;

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
        } else if (deleteCustomerButton2 == event.getSource()) {
            deleteCustomerButton2.setStyle("-fx-background-color: #52779C;");
        } else if (Refresh == event.getSource()) {
            Refresh.setStyle("-fx-background-color: #52779C;");
        } else if (deleteEmployeeButton2 == event.getSource()) {
            deleteEmployeeButton2.setStyle("-fx-background-color: #52779C;");
        } else if (changeSecurityKeyButton1 == event.getSource()) {
            changeSecurityKeyButton1.setStyle("-fx-background-color: #52779C;");
        }  else if (changeSecurityKeyButton2 == event.getSource()) {
            changeSecurityKeyButton2.setStyle("-fx-background-color: #52779C;");
        } else if (changeSecurityKeyButton3 == event.getSource()) {
            changeSecurityKeyButton3.setStyle("-fx-background-color: #52779C;");
        } else if (changeSecurityKeyButton11 == event.getSource()) {
            changeSecurityKeyButton11.setStyle("-fx-background-color: #52779C;");
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
        } else if (deleteCustomerButton2 == event.getSource()) {
            deleteCustomerButton2.setStyle("-fx-background-color: #414D59;");
        } else if (Refresh == event.getSource()) {
            Refresh.setStyle("-fx-background-color: #414D59;");
        } else if (deleteEmployeeButton2 == event.getSource()) {
            deleteEmployeeButton2.setStyle("-fx-background-color: #414D59;");
        } else if (changeSecurityKeyButton1 == event.getSource()) {
            changeSecurityKeyButton1.setStyle("-fx-background-color: #414D59;");
        } else if (changeSecurityKeyButton2 == event.getSource()) {
            changeSecurityKeyButton2.setStyle("-fx-background-color: #414D59;");
        } else if (changeSecurityKeyButton3 == event.getSource()) {
            changeSecurityKeyButton3.setStyle("-fx-background-color: #414D59;");
        } else if (changeSecurityKeyButton11 == event.getSource()) {
            changeSecurityKeyButton11.setStyle("-fx-background-color: #414D59;");
        }
    }

}
