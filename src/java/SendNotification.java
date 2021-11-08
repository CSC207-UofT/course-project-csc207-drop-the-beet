import java.util.ArrayList;
import java.util.List;

public interface SendNotification {


    List<String> content = new ArrayList<>();

    boolean state = true; //默认开启

    public void switchState(); //开关

}