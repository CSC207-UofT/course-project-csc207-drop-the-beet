import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ToDoListManager implements Notification{

    public ArrayList<ToDoList> toDoLists = new ArrayList<>();

    public ToDoListManager() {}

    private static String getDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return formatter.format(date);
    }

    private ToDoList ifAlreadyExists(String name){
        for(int i = 0; i < this.toDoLists.size(); i++){
            if(toDoLists.get(i).getListName().equals(name)) {return toDoLists.get(i);}
        }
        return null;
    }

    public void addThingsToDo(String thingToDo){
        if(ifAlreadyExists(getDate()) != null){
            ifAlreadyExists(getDate()).addThingToDo(thingToDo);
        }else {
            this.toDoLists.add(new ToDoList(thingToDo));
        }
    }

    @Override
    public String toString() {
        String all = "";
        for(int i = 0; i < this.toDoLists.size(); i++){
            if(toDoLists.get(i).getState().equals("Incomplete")){
                all += "Date: " + toDoLists.get(i).getListName() + ", ToDoList: " + toDoLists.get(i).getThingsToDo();
            }else {
                all += "Date: " + toDoLists.get(i).getListName() + ", " + toDoLists.get(i).getState() + "!";
            }
            all += "\n";
        }
        return all;
    }

    public static void main(String[] args) {
        ToDoListManager toDoListManager = new ToDoListManager();
        toDoListManager.addThingsToDo("Phase 0");
        toDoListManager.toDoLists.add(new ToDoList("13/10/2021", new ArrayList<String>())); //作弊
        System.out.println(toDoListManager);

    }
}
