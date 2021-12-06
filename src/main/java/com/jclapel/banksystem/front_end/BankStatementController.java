package com.jclapel.banksystem.front_end;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BankStatementController extends Listener {
	/*

	Bank statement controller for controlling user interface, specifically for bank statement UI.

	Main Contributor(s): Albin
	Contributor(s): Liam

	*/
	private Stage stage;
	private Scene scene;

	@FXML
	void switchToCustomerMenu(ActionEvent event) throws IOException {
		// Navigates user interface to customer menu
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("userMenu.fxml"));
		Parent root = loader.load();
		scene = new Scene(root);

		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void switchToTransferHistory(ActionEvent event) throws IOException {
		// Navigates user interface to bank transfer history
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("bankStatment.fxml"));
		Parent root = loader.load();
		scene = new Scene(root);

		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void switchToFinanceProject(ActionEvent event) {
		// Navigates user interface to portfolio

	}

	@FXML//on interface button = ??
	void switchToTransfer(ActionEvent event) throws IOException {
		// Navigates user interface to what???
		Parent root = FXMLLoader.load(getClass().getResource("transferCustomer.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
