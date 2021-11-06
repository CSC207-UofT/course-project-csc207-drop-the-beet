import java.util.Date;

/** Receive the input from user, and initialize toDoListManager and scheduleManager.
 */

public class PlanMaker {
    ToDoListManager toDoListManager;
    ScheduleManager scheduleManager;
    UserManager userManager = new UserManager();

    public PlanMaker (Schedule schedule) {
        this.scheduleManager = new ScheduleManager();
        this.scheduleManager.addSchedule(schedule);
    }

    public PlanMaker (String name) {
        this.toDoListManager = new ToDoListManager();
        toDoListManager.addNewList(name);
    }

//    public boolean setNotification() {
//        // Todo general setup for notification
//    }

    public void getAllTasks() {
        // Todo get all schedule and to-do for this user
    }

}

