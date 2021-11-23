
module com.example.jc_lapel {

    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.FrontEnd to javafx.fxml;
    exports com.example.FrontEnd;

}