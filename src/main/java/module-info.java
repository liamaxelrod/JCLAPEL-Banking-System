module com.jclapel.banksystem {

	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
 
	opens com.jclapel.banksystem.front_end to javafx.fxml;

	opens com.jclapel.banksystem to javafx.fxml;
	exports com.jclapel.banksystem.data;
	exports com.jclapel.banksystem.front_end;
	exports com.jclapel.banksystem.back_end;
}