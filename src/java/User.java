import java.io.Serializable;

/** User contains basic information of this user.
 */

public class User implements Serializable {
    public String name;
    private String email;
    private String password;
    private ScheduleManager mySchedule;
    private ToDoListManager myToDOList;

    /**
     *
     * Create a User.
     * @param name: the user's name
     * @param email: the user's email
     * @param password: the user's password
     */
    public User (String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.mySchedule = new ScheduleManager();
        this.myToDOList = new ToDoListManager();
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public void changeEmail(String newEmail) {
        this.email = newEmail;
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    public void addSchedule(Schedule schedule) {
        this.mySchedule.addSchedule(schedule);
    }

    public void removeSchedule(Schedule schedule) {
        this.mySchedule.removeSchedule(schedule);
    }

    public void createToDoList(String name) {
        this.myToDOList.addNewList(name);
    }

    public void addToDo(String name) {
        ToDoList toDoList = this.myToDOList.getToDoList(name);
        toDoList.addTask(name);
    }

    public void removeList(String listName) {
        myToDOList.removeList(listName);
    }

    public ScheduleManager getMySchedules() {
        return mySchedule;
    }

    public ToDoListManager getMyToDOLists() {
        return myToDOList;
    }
}