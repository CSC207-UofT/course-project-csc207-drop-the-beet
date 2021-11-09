package planner;

import java.util.HashMap;

/** UserManager is to validate the information input from Setting
 * (and process input from PlanMaker).
 */

public class UserManager {
    private HashMap<String, User> userHashMap;
    private User currentUser;

    /**
     * Initialize a UserManager with:
     * - a hashmap whose keys are usernames and values are user objects
     * - a currentUser = null
     */
    public UserManager() {
        this.userHashMap = new HashMap<>();
        this.currentUser = null;
    }

    /**
     *
     * @param username: username
     * @return true if username is in the hashmap
     */
    public boolean containsUser(String username) {
        return this.userHashMap.containsKey(username);
    }

    /**
     * Add a new user to the hashmap if username not in hashmap
     * @param username: username
     * @param email: email
     * @param password: password
     * @return true if successful
     */
    public boolean addNewUser(String username, String email, String password) {
        if (this.containsUser(username)) {
            return false;
        }
        User user = new User(username, email, password);
        this.userHashMap.put(username, user);
        return true;
    }

    public boolean addNewUser(User user) {
        this.userHashMap.put(user.getName(), user);
        return true;
    }

    public User getCurrentUser() {
        return this.currentUser;
    }

    /**
     * Change the current user
     * @param username: username
     * @return the User object associated with username
     */
    public User changeCurrentUser(String username) {
        this.currentUser = this.getUser(username);
        return this.currentUser;
    }

    public void logOffCurrentUser() {
        this.currentUser = null;
    }

    public User getUser(String username) {
        return this.userHashMap.get(username);  // return null if username doesn't exist
    }

    public HashMap<String, User> getUserHashmap() {
        return this.userHashMap;
    }

    /**
     *
     * @param username: the user's preferred username
     * @param email: the user's preferred email
     * @param password: the user's preferred password
     * @return true if user has successfully created an account; false otherwise
     */
    public boolean userRegister(String username, String email, String password) {
        if (this.containsUser(username)) {
            System.out.println("This username has been used!");
            return false;
        }
        // create a new user object and save it to the userHashMap
        this.addNewUser(username, email, password);
        return true;
    }

    /**
     *
     * @param inputUsername: the input username
     * @param inputPassword: the input password
     * @return true if the input username exists and the input password is correct
     */
    public boolean userLogIn(String inputUsername, String inputPassword) {
        // check if the password corresponding to this user is the same as the input password
        if (!this.containsUser(inputUsername)) {
            System.out.println("This username doesn't exist! Please double check.");
            return false;
        }
        String userPassword = this.getUser(inputUsername).getPassword();
        return userPassword.equals(inputPassword);  // check if user's password equals input password
    }

//    public boolean loadUserTasks() {
//        // Todo
//    }

    public void currentUserChangePassword(String newPassword) {
        this.currentUser.changePassword(newPassword);
    }

    public void currentUserChangeEmail(String newEmail) {
        this.currentUser.changeEmail(newEmail);
    }

    public void userAddSchedule(Schedule schedule) {
        this.currentUser.addSchedule(schedule);
    }

    public void userCreateToDoList(String name) {
        this.currentUser.createToDoList(name);
    }

    public void userAddToDo(String name) {
        this.currentUser.addToDo(name);
    }

    public void userRemoveList(String listName) {
        this.currentUser.removeList(listName);
    }

    public void userRemoveSchedule(Schedule schedule) {
        this.currentUser.removeSchedule(schedule);
    }

    public static void main(String[] args) {
        UserManager um = new UserManager();
        System.out.println(um.getCurrentUser());

//        HashMap<String, Integer> hm = new HashMap<>();
//        System.out.println(hm.get("12"));
//        hm.put("123", 1234);
//        System.out.println(hm.get("123"));
    }
}
