import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

/** ScheduleManager is to modify any of the schedules of the user.
 */

public class ScheduleManager implements Notification{
    Map<String, ArrayList<Schedule>> schedules = new HashMap<>();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
    String date;

    public ScheduleManager(String date){
        this.date = date;
        this.schedules.put(this.date, new ArrayList<Schedule>());
    }

    public void addSchedule(String date, Schedule schedule){
        this.schedules.get(date).add(schedule);
    }

    public void removeSchedule(String date, Schedule schedule){
        this.schedules.get(date).remove(schedule);
    }

    public void modifyFrom(Schedule schedule, String from) {
        // Todo modify start time
    }

    public void modifyEnd(Schedule schedule, String end) {
        // Todo modify end time
    }

    public void modifyTask(Schedule schedule, String task) {
        // Todo modify task
    }

    public void repeatSchedules(String info, int length){
        // Todo
        // eg. repeat every wednesday from Feb 1st to Jul 15th
    }

    public void deleteRepeatSchedules(String info, int length) {
        // Todo
    }

    // Todo Notification in ScheduleManager or in Schedule

//    public static void main(String[] args) {
//        Schedule a = new Schedule("13:00-14:00","Cook", "I need to cook");
//        ScheduleManager b = new ScheduleManager("2021-10-14");
//        b.addSchedule("2021-10-14", a);
//        System.out.println(b.schedules.get("2021-10-14").get(0)==a);
//        b.removeSchedule("2021-10-14", a);
//        System.out.println(b.schedules.get("2021-10-14").isEmpty());
//    }
}