import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class GetNotification{
    private String name;
    List<String> content = new ArrayList<>();

    private boolean state = true; //默认开启

    public boolean getState(){
        return state;
    }

    public GetNotification(String name){
        this.name = name;
    }

    public void switchState(){
        state = !state;
    } //开关

    public void update(SendNotification s, String newNotification){
        if(state){
            content.add(newNotification + " (from " + s +")");
        }
    }

    public void checkNotification(){
        System.out.println(content);
        content.clear();
    }
}

class Demo{
    public static void main(String[] args) {
        User user = new User("dz","@","123");
        ToDoList todolist = new ToDoList("csc207");
        todolist.addTask("phase 0");
        todolist.observable.addObserver(user.observer);
        todolist.observable.sendNotification("due");
        user.observer.checkNotification();
        user.observer.checkNotification();

    }
}
