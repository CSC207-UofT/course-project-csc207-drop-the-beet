import org.junit.*;
import static org.junit.Assert.*;

public class ScheduleTest {
    Schedule a;
    ScheduleManager b;
    @Before
    public void setUp() throws Exception {
        a = new Schedule("2021/10/14, 13:00", "2021/10/14, 14:00", "I need to cook some chicken noodle soup");
        b = new ScheduleManager("2021/10/14");
    }

    @Test(timeout = 50)
    public void TestAddSchedule() {
        b.addSchedule("2021-10-14", a);
        assertSame(b.schedules.get("2021-10-14").get(0), a);
    }

    @Test(timeout = 50)
    public void TestRemoveSchedule() {
        b.removeSchedule("2021-10-14", a);
        assertTrue(b.schedules.get("2021-10-14").isEmpty());
    }
}
