import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

public class ToDoList{
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    Date date = new Date();
    private String listName = formatter.format(date); //当天的日期

    private ArrayList<String> thingsToDo = new ArrayList<>();
    private ArrayList<String> thingsFinished = new ArrayList<>();
    private String state = "Incomplete";

    public ToDoList(String thingToDo) {
        this.thingsToDo.add(thingToDo);
        if(thingToDo.equals("")){this.state = "Complete";} //代码重复了，可能有更好的写法
    }
    public ToDoList(ArrayList<String> thingsToDo) {
        this.thingsToDo = thingsToDo;
        if(thingsToDo.isEmpty()){this.state = "Complete";}
    }
    public ToDoList(String listName, ArrayList<String> thingsToDo) {
        this.listName = listName;
        this.thingsToDo = thingsToDo;
        if(thingsToDo.isEmpty()){this.state = "Complete";}
    }

    public void addThingToDo(String thingToDo) {
        this.thingsToDo.add(thingToDo);
    }

    public void removeThingToDo(String thingToDo) {
        this.thingsToDo.remove(thingToDo);
    }

    public void finishOneThing(String oneThing) {
        if(this.thingsToDo.contains(oneThing)){
            this.thingsToDo.remove(oneThing);
            this.thingsFinished.add(oneThing);
        } else {
            System.out.println("Error. Finished Thing not added!");
        }
        if(this.thingsToDo.isEmpty()){this.state = "Complete";}
    }

    public ArrayList<String> getThingsToDo() {
        return thingsToDo;
    }

    public ArrayList<String> getThingsFinished() {
        return thingsFinished;
    }

    public String getListName() {
        return listName;
    }

    public String getState() {
        return state;
    }

    public static void main(String[] args) {
        ArrayList<String> thingsToDo = new ArrayList<>();
        for(int i = 0; i<10;i++){
            thingsToDo.add("" + i);
        }
        //ToDoList toDoList = new ToDoList(thingsToDo);
        ToDoList toDoList = new ToDoList("thingsToDo");
        System.out.println(toDoList.getListName());
        System.out.println(toDoList.getThingsToDo());
        System.out.println(toDoList.getThingsFinished());
        System.out.println("#####");
        //toDoList.finishOneThing("1");
        toDoList.finishOneThing("thingsToDo");
        toDoList.finishOneThing("thingsToDo");
        System.out.println(toDoList.getThingsToDo());
        System.out.println(toDoList.getThingsFinished());
        System.out.println(toDoList.getState());
    }
}
