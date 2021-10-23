import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/** ToDoListManager is to modify any of the TodoLists of the user.
 * eg. set notification for one of the to-do lists
 */

public class ToDoListManager implements Notification{
    Map<String, ToDoList> toDoLists = new HashMap<>();
//    public ArrayList<ToDoList> toDoLists = new ArrayList<>();

    public ToDoListManager() {
    }

//    private static String getDate(){
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//        Date date = new Date();
//        return formatter.format(date);
//    }

    private ToDoList ifAlreadyExists(String name){
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
        return toDoLists.toString();
    }

    public static void main(String[] args) {
        ArrayList<String> c = new ArrayList<>();
        ToDoList a = new ToDoList("csc207", c);
        ToDoListManager b = new ToDoListManager();
        b.addNewList("csc207");
        a.addTask("Project phase 0");
        a.addTask("shop grocery");
        System.out.println(b.toDoLists);

    }
}
