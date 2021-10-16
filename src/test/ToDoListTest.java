import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TodoListTest {
    ToDoList a;
    ToDoListManager b;
    ArrayList<String> c;

    @Before
    public void setUp() throws Exception {
        a = new ToDoList("csc207", c);
        b = new ToDoListManager();
    }

    @Test(timeout = 50)
    public void TestAddTask() {
        b.addNewList(a);
        a.addTask("Project phase 0");
        assertSame(b.getToDoList("csc207").getTasksToDo().get(0), "Project phase 0");
    }

    @Test(timeout = 50)
    public void TestRemoveSchedule() {
        a.completion("Project phase 0");
        assertSame(b.getToDoList("csc207").getTasksToDo().get(0), null);
    }
}