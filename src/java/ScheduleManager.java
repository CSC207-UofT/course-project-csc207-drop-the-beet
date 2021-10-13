import java.util.ArrayList;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ScheduleManager{
    Map<LocalDate, ArrayList<String>> schedules = new HashMap<>();

    public ScheduleManager(){
        this.schedules.put(LocalDate.now(), new ArrayList<>());
    }

    public void addSchedule(String info){
        this.schedules.get(LocalDate.now()).add(info);
    }

    public void removeSchedule(String info){
        this.schedules.get(LocalDate.now()).remove(info);
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
}