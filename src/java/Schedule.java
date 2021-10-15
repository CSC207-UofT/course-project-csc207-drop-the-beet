import java.text.SimpleDateFormat;

public class Schedule {
    String title;
    String info;
    String fromTo = new String("HH:mm-HH:mm");

    public Schedule(String fromTo, String title, String info){
        this.fromTo = fromTo;
        this.title = title;
        this.info = info;
    }
}
