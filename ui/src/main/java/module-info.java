module com.ui.ui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.ui.ui to javafx.fxml;
    exports com.ui.ui;
}