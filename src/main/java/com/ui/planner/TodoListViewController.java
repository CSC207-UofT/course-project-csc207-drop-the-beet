package com.ui.planner;

import com.planner.Entities.ToDoList;

import com.planner.UseCases.UserManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TodoListViewController implements Initializable {
    @FXML
    private TableColumn<ToDoEventModel, String> event;
    @FXML
    private TableColumn<ToDoEventModel, String> end;
    @FXML
    private TableView<ToDoEventModel> toDoLstTb;

//    @FXML
//    private Button todoListNewEventBtn;

    private final ObservableList<ToDoEventModel> toDoEventModels = FXCollections.observableArrayList();

    private UserManager user;//done

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        event.setCellValueFactory(new PropertyValueFactory<>("Event"));
        end.setCellValueFactory(new PropertyValueFactory<>("End"));
    }

    @FXML
    protected void todoListNewEventBtnClicked() throws IOException {
        AddtodoListView addtodoList = new AddtodoListView();
        addtodoList.setUser(user);
        addtodoList.showWindow();
        toDoEventModels.clear();
        showEvents();
    }

    @FXML
    public void showEvents() {
        List<ToDoList> lst;
        lst = user.getToDoLists().getToDos();

        if (lst != null && lst.size() > 0) {
            for (ToDoList l : lst){
                toDoEventModels.add(new ToDoEventModel(l.getTask(), l.getDeadline().toString()));
            }
            toDoLstTb.setItems(toDoEventModels);
        }
    }

    public void setUser(UserManager currUser) {
        this.user = currUser;
    }
}
