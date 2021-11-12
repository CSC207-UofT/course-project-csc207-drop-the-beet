import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class SendNotification {
    private String name;
    private boolean changed = false;
    private List<GetNotification> observers = new ArrayList<>();

    public SendNotification(String name){
        this.name = name;
    }

    public void setChanged(){
        changed = !changed;
    }

    public void addObserver(GetNotification observer){
        if(observer.getState() && !observers.contains(observer)) observers.add(observer);
    }

    public void deleteObserver(GetNotification observer){
        if(observer.getState() && observers.contains(observer)) observers.remove(observer);
    }

    public void sendNotification(String newNotification){
        for (int i = observers.size()-1; i>=0; i--)
            observers.get(i).update(this, newNotification);
    }

    public String toString(){
        return name;
    }


}