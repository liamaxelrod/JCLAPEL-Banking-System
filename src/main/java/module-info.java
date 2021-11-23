module com.jclapel.banksystem {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires transitive javafx.graphics;
    requires transitive java.sql;

    requires com.google.gson;

    opens com.jclapel.banksystem to javafx.fxml;
    exports com.jclapel.banksystem.data;
    exports com.jclapel.banksystem.facade;
    exports com.jclapel.banksystem.frontend;
}