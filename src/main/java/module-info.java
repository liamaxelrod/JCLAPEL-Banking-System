module com.example.jc_lapel {

	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;

	opens com.example.FrontEnd to javafx.fxml;
	exports com.example.FrontEnd;

	opens com.jclapel.banksystem to javafx.fxml;
	exports com.jclapel.banksystem.data;
	exports com.jclapel.banksystem.facade;
	exports com.jclapel.banksystem.frontend;
}