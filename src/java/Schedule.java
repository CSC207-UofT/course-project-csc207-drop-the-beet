import java.text.SimpleDateFormat;
import java.util.Date;

/** Schedule is a single task with start time and end time.
 */

public class Schedule implements Notification{
    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
    private String task;
    private String from;

    public Schedule(String from, String task){
        this.from = from;
        this.task = task;
    }

    public String getFrom() {
        return this.from;
    }

    public String getTask() {
        return this.task;
    }

    @Override
    public String toString() {
        return this.task.toString();
    }

}
