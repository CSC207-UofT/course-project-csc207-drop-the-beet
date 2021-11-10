module com.ui.planner {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;
    requires java.sql;
    requires org.xerial.sqlitejdbc;


    opens com.ui.planner to javafx.fxml;
    exports com.ui.planner;
}