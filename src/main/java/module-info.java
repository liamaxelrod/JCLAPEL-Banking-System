module com.jclapel.banksystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires com.google.gson;

    opens com.jclapel.banksystem to javafx.fxml;
    exports com.jclapel.banksystem;
}