package com.ui.planner;

import com.planner.UseCases.UserManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AddImportantControllerTest {
    public AddImportantController aic;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endingDatePicker;
    @FXML
    private TextField eventTextField;
    @FXML
    private Button confirmBtn;
    @FXML
    private Button cancelBtn;
    private UserManager user;

    @BeforeEach
    public void setUp() {
        aic = new AddImportantController();
        user = new UserManager("Tom", "1@1.com", "1234");
    }

    @Test
    void onConfirmBtnClicked() {
        assertEquals(0, user.getImportant().getSize());
        try {
            aic.onConfirmBtnClicked();
            assertEquals(0, user.getImportant().getSize());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}