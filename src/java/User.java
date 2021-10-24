import java.util.ArrayList;

/** User contains basic information of this user.
 */

public class User {
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
        this.mySchedule = new ScheduleManager("2021/10/14");
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
}