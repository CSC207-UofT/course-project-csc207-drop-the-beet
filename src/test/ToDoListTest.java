//import org.junit.*;
//
//import java.util.ArrayList;
//
//import static org.junit.Assert.*;
//
//public class ToDoListTest {
//    ToDoList a;
//    ToDoListManager b;
//    ArrayList<String> c = new ArrayList<>(); // 1
//
//    @Before
//    public void setUp(){
//        a = new ToDoList("csc207", c);
//        b = new ToDoListManager();
//    }
//
//    @Test(timeout = 50)
//    public void TestAddTask() {
//        b.addNewList("Project phase 0");
//        a.addTask("Project phase 0");
//        assertSame(b.getToDoList("csc207").getTasksToDo().get(0), "Project phase 0");
//    }
//
//    @Test(timeout = 50)
//    public void TestCompletion() {
//        b.addNewList("Project phase 0");
//        a.addTask("Project phase 0"); // 2
//        a.completion("Project phase 0");
//        assertTrue(b.getToDoList("csc207").getTasksToDo().isEmpty()); //3
//    }
//}