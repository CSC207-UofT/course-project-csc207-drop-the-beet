import org.junit.*;

import static org.junit.Assert.*;

public class ScheduleTest {
    Schedule s1;
    Schedule s2;
    ScheduleManager sm;

    @Before
    public void setUp(){
        s1 = new Schedule("2021/10/14", "11:00", "12:00", "I need to cook");
        s2 = new Schedule("2021/10/14", "13:00", "14:00", "I need to study");
        sm = new ScheduleManager();
    }

    // Test Schedule
    @Test(timeout = 50)
    public void TestToString() {
        String expected = "I need to cook";
        String actual = s1.toString();
        assertEquals(expected, actual);
    }

    @Test(timeout = 50)
    public void TestGetter() {
        assertEquals("2021/10/14", s1.getDate());
        assertEquals("11:00", s1.getFrom());
        assertEquals("12:00", s1.getTo());
        assertEquals("I need to cook", s1.getTask());
    }

    @Test(timeout = 50)
    public void TestSetter() {
        s1.setDate("2021/10/14");
        s1.setFrom("17:00");
        s1.setTo("18:00");
        s1.setTask("I need to cook dinner");
        assertEquals("2021/10/14", s1.getDate());
        assertEquals("17:00", s1.getFrom());
        assertEquals("18:00", s1.getTo());
        assertEquals("I need to cook dinner", s1.getTask());
    }

    // Test ScheduleManager Methods
    @Test(timeout = 50)
    public void TestAddSchedule() {
        sm.addSchedule(s1);
        sm.addSchedule(s2);
        assertSame(sm.schedules.get("2021/10/14").get(0), s1);
        assertSame(sm.schedules.get("2021/10/14").get(1), s2);
    }

    @Test(timeout = 50)
    public void TestRemoveSchedule() {
        sm.removeSchedule(s1);
        assertEquals(sm.schedules.get("2021/10/14"), sm.schedules.get(s2.getDate()));
        sm.removeSchedule(s2);
        assertNull(sm.schedules.get("2021/10/14"));
    }

    @Test(timeout = 50)
    public void TestModifyMethods() {
        sm.modifyDate(s1, "2021/10/16");
        assertEquals("2021/10/16", s1.getDate());
        sm.modifyFrom(s1, "09:00");
        assertEquals("09:00", s1.getFrom());
        sm.modifyTo(s1, "16:00");
        assertEquals("16:00", s1.getTo());
        sm.modifyTask(s1, "School");
        assertEquals("School", s1.getTask());
    }


    @Test(timeout = 50)
    public void TestViewMethods() {
        assertEquals("2021/10/14", sm.viewDate(s1));
        assertEquals("11:00", sm.viewFrom(s1));
        assertEquals("12:00", sm.viewTo(s1));
        assertEquals("I need to cook", sm.viewTask(s1));
    }
}
