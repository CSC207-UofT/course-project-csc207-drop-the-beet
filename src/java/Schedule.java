import java.text.SimpleDateFormat;
import java.util.ArrayList;

/** Schedule is a single task with start time and end time.
 */

public class Schedule{
    private String date;
    private String task;
    private String from;
    private String to;
//    private ArrayList<Object> repeat = new ArrayList<>();

    /**
     * A simpler constructor of the Schedule
     */
    public Schedule(){
        this.date = "";
        this.from = "";
        this.to = "";
        this.task = "";
//        this.repeat.add(false);
    }

    /**
     * Constructor of the Schedule
     * @param date is the day of the schedule in yyyy/MM/dd
     * @param from is the start time in HH:mm (24hrs)
     * @param to is the end time in HH:mm (24hrs)
     * @param task is the title of the task
     */
    public Schedule(String date, String from, String to, String task){
        this.date = date;
        this.from = from;
        this.to = to;
        this.task = task;
    }

    /**
     * This method returns the current date
     * @return this schedules' date
     */
    public String getDate() {
        return this.date;
    }

    /**
     * This method updates a new date
     * @param date is the new date
     */
    public void setDate(String date) {
        this.date = date;
    }


    /**
     * This method returns the current start time
     * @return this schedules' start time
     */
    public String getFrom() {
        return this.from;
    }

    /**
     * This method updates a new start time
     * @param from is the new start time
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * This method returns the current end time
     * @return this schedules' end time
     */
    public String getTo() {
        return this.to;
    }

    /**
     * This method updates a new end time
     * @param to is the new end time
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * This method returns the current task descriptions
     * @return this schedules' task
     */
    public String getTask() {
        return this.task;
    }

    /**
     * This method updates a new task description
     * @param task is the new task descriptions
     */
    public void setTask(String task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return this.task;
    }

}
