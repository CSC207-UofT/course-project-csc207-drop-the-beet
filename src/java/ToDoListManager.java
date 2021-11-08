import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/** ToDoListManager is to modify any of the TodoLists of the user.
 * eg. set notification for one of the to-do lists
 */

public class ToDoListManager{
    private Map<String, ToDoList> toDoLists = new HashMap<>();

    private ArrayList<String> completedTasks = new ArrayList<>();

    public ToDoListManager() {
    }

    public void printAllNames(){
        System.out.println(toDoLists.keySet());
    }

    private static String getDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return formatter.format(date);
    }

    public ToDoList ifAlreadyExists(String name){
        if (toDoLists.containsKey(name)) {
            return toDoLists.get(name);
        }
//        for (ToDoList toDoList : this.toDoLists) {
//            if (toDoList.getListName().equals(name)) {
//                return toDoList;
//            }
//        }
        return null;
    }

    public void addNewList(String name) {
        ArrayList<String> list = new ArrayList<>();
        ToDoList toDoList = new ToDoList(name, list);
        toDoLists.put(name, toDoList);
    }

    public void removeList(String listName) {
        toDoLists.remove(listName);
    }

    public ToDoList getToDoList(String name) {
        return toDoLists.get(name);
    }

    public void modifyListName(ToDoList toDoList, String newName){
        toDoLists.remove(toDoList.getListName());
        toDoList.modifyName(newName);
        toDoLists.put(toDoList.getListName(), toDoList);
    }

    public void completeTask(ToDoList toDoList, String content) {
        toDoList.completion(content);
        completedTasks.add(content + " completed at " + getDate());
    }
    // Todo Notification

//    public void addThingsToDo(String thingToDo){
//        if(ifAlreadyExists(getDate()) != null){
//            ifAlreadyExists(getDate()).addThingToDo(thingToDo);
//        }else {
//            this.toDoLists.add(new ToDoList(thingToDo));
//        }
//    }

//    @Override
//    public String toString() {
//        String all = "";
//        for(int i = 0; i < this.toDoLists.size(); i++){
//            if(toDoLists.get(i).getState().equals("Incomplete")){
//                all += "Date: " + toDoLists.get(i).getListName() + ", ToDoList: " + toDoLists.get(i).getThingsToDo();
//            }else {
//                all += "Date: " + toDoLists.get(i).getListName() + ", " + toDoLists.get(i).getState() + "!";
//            }
//            all += "\n";
//        }
//        return all;
//    }

    @Override
    public String toString() {
        return "Your todo lists: " + toDoLists.toString() + "\nYour works done: " + completedTasks.toString();
    }

    public static void main(String[] args) {

        ToDoListManager toDoListManager = new ToDoListManager();
        toDoListManager.addNewList("csc207");
        ToDoList toDoList = toDoListManager.getToDoList("csc207");
        toDoList.addTask("Project phase 0");
        toDoList.addTask("Project phase 1");
        toDoList.addTask("Project phase 2");
        System.out.println(toDoListManager);
        toDoListManager.modifyListName(toDoList, "CSC207");
        System.out.println(toDoListManager);
        toDoListManager.completeTask(toDoList, "Project phase 0");
        toDoListManager.completeTask(toDoList, "Project phase 1");
        System.out.println(toDoListManager);
        //toDoListManager.addNewList("CSC207");
        toDoListManager.addNewList("CSC236");
        toDoListManager.addNewList("CSC258");
        System.out.println(toDoListManager);
        toDoListManager.printAllNames();
    }
}
