import java.text.SimpleDateFormat;
import java.util.Date;

/** Schedule is a single task with start time and end time.
 */

public class Schedule implements Notification{
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd, HH:mm");
    private String task;
    private String from;
    private String end;

    public Schedule(String from, String end, String task){
        this.from = from;
        this.end = end;
        this.task = task;
    }

    public String getFrom() {
        return this.from;
    }

    public String getEnd() {
        return this.end;
    }

    public String getTask() {
        return this.task;
    }

    @Override
    public String toString() {
        return this.task.toString();
    }

}
