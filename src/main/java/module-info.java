module com.example.demo {
    requires javafx.controls;
    requires javafx.base;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;
    requires mysql.connector.j;

    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
}