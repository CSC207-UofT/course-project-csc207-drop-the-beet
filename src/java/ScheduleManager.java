import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class ScheduleManager{
    Map<String, ArrayList<Object>> schedules = new HashMap<String, ArrayList<Object>>();
    SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");

    public ScheduleManager(String time){
        this.schedules.put(time, new ArrayList<Object>());
    }

    public void addSchedule(String time, Schedule schedule){
        this.schedules.get(time).add(schedule);
    }

    public void removeSchedule(String time, Schedule schedule){
        this.schedules.get(time).remove(schedule);
    }

//    public void repeatSchedules(String info, int length){
//        for (int i = 0; i <= length; i++){
//
//        }
//    }
//
//    public void deleteRepeatSchedules(String info, int length) {
//        for (int i = 0; i <= length; i++) {
//
//        }
//    }

    public static void main(String[] args) {
//        Schedule a = new Schedule("13:00-14:00","Cook", "I need to cook");
//        ScheduleManager b = new ScheduleManager("2021-10-14");
//        b.addSchedule("2021-10-14", a);
//        System.out.println(b.schedules.get("2021-10-14").get(0)==a);
//        b.removeSchedule("2021-10-14", a);
//        System.out.println(b.schedules.get("2021-10-14").isEmpty());
    }
}