import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/** ToDoListManager is to modify any of the TodoLists of the user.
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

    public void addNewList(ToDoList toDoList) {
        toDoLists.put(toDoList.getListName(), toDoList);
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

//    public static void main(String[] args) {
//        ToDoListManager toDoListManager = new ToDoListManager();
//        toDoListManager.addThingsToDo("Phase 0");
//        toDoListManager.toDoLists.add(new ToDoList("13/10/2021", new ArrayList<String>())); //作弊
//        System.out.println(toDoListManager);
//
//    }
}
