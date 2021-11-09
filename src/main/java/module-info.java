module com.ui.planner {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;


    opens com.ui.planner to javafx.fxml;
    exports com.ui.planner;
}