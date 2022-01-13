module com.example.jclapel {

	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;
    requires org.mongodb.driver.core;

    opens com.example.FrontEnd to javafx.fxml;
	exports com.example.FrontEnd;

	opens com.jclapel.banksystem to javafx.fxml;
	exports com.jclapel.banksystem.data;
	//exports com.jclapel.banksystem.facade;
	exports com.jclapel.banksystem.frontend;
}