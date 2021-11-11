module com.example.jc_lapel {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.jc_lapel to javafx.fxml;
    exports com.example.jc_lapel;
}