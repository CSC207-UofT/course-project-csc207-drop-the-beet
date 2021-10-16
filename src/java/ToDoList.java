import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

/** TodoList is a list of tasks for today.
 * If a task is done, mark the task as complete(move that into the list of completion).
*/

public class ToDoList implements Notification{
    private String listName;

    private ArrayList<String> tasksToDo = new ArrayList<>();
    private final ArrayList<String> tasksFinished = new ArrayList<>();
//    private String state = "Incomplete";

    public ToDoList(String listName, ArrayList<String> tasksToDo) {
        this.listName = listName;
        this.tasksToDo = tasksToDo;
    }

    public void addTask(String task) {
        this.tasksToDo.add(task);
    }

    public void removeTask(String task) {
        this.tasksToDo.remove(task);
    }

    public boolean modifyTask(String oldTask, String newTask) {
        for (int i = 0; i < tasksToDo.size(); i++) {
            if (tasksToDo.get(i).equals(oldTask)) {
                tasksToDo.set(i, newTask);
                return true;
            }
        }
        return false;
    }

    public boolean modifyName(String listName) {
        this.listName = listName;
        return true;
    }

    public ArrayList<String> getTasksToDo() {
        return tasksToDo;
    }

    public ArrayList<String> getTasksFinished() {
        return tasksFinished;
    }

    public String getListName() {
        return listName;
    }

    public void completion(String task) {
        for (int i = 0; i < tasksToDo.size(); i++) {
            if (tasksToDo.get(i).equals(task)) {
                tasksFinished.add(task);
                tasksToDo.remove(task);
                break;
            }
        }
    }

    // Todo Notification default


//    public ToDoList(String listName, ArrayList<String> tasksToDo) {
//        this.listName = listName;
//        this.tasksToDo = tasksToDo;
//        if(tasksToDo.isEmpty()){this.state = "Complete";}
//    }
//
//    public void finishOneThing(String oneThing) {
//        if(this.tasksToDo.contains(oneThing)){
//            this.tasksToDo.remove(oneThing);
//            this.tasksFinished.add(oneThing);
//        } else {
//            System.out.println("Error. Finished Thing not added!");
//        }
//        if(this.tasksToDo.isEmpty()){this.state = "Complete";}
//    }
//
//    public String getListName() {
//        return listName;
//    }
//
//    public String getState() {
//        return state;
//    }
//
//    public static void main(String[] args) {
//        ArrayList<String> tasksToDo = new ArrayList<>();
//        for(int i = 0; i<10;i++){
//            tasksToDo.add("" + i);
//        }
//        //ToDoList toDoList = new ToDoList(thingsToDo);
//        ToDoList toDoList = new ToDoList("thingsToDo");
//        System.out.println(toDoList.getListName());
//        System.out.println(toDoList.getTasksToDo());
//        System.out.println(toDoList.getTasksFinished());
//        System.out.println("#####");
//        //toDoList.finishOneThing("1");
//        toDoList.finishOneThing("thingsToDo");
//        toDoList.finishOneThing("thingsToDo");
//        System.out.println(toDoList.getTasksToDo());
//        System.out.println(toDoList.getTasksFinished());
//        System.out.println(toDoList.getState());
//    }
}
