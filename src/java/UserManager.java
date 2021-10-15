import java.util.HashMap;

public class UserManager {
    private HashMap<String, User> userHashMap;
    private User currentUser;

    public UserManager() {
        this.userHashMap = new HashMap<>();
        this.currentUser =
    }

    public boolean containsUser(String username) {
        return this.userHashMap.containsKey(username);
    }

    public void addNewUser(String username, String email, String password) {
        // TODO: report an exception if username exists
        User user = new User(username, email, password);
        this.userHashMap.put(username, user);
    }

    public User getUser(String username) {
        // TODO: report an exception if username does not exist
        return this.userHashMap.get(username);
    }

//    /**
//     *
//     * @param username: the user's preferred username
//     * @param email: the user's preferred email
//     * @param password: the user's preferred password
//     * @return true if user has successfully created an account; false otherwise
//     */
//    public boolean userRegister(String username, String email, String password) {
//        if (this.containsUser(username)) {
//            System.out.println("This username has been used!");
//            return false;
//        }
//        // create a new user object and save it to the userHashMap
//        User user = new User(username, email, password);
//        this.addUser(username, user);
//        return true;
//    }

//    /**
//     *
//     * @param inputUsername: the input username
//     * @param inputPassword: the input password
//     * @return true if the input username exists and the input password is correct
//     */
//    public boolean userLogIn(String inputUsername, String inputPassword) {
//        // check if the password corresponding to this user is the same as the input password
//        if (!this.containsUser(inputUsername)) {
//            System.out.println("This username doesn't exist! Please double check.");
//            return false;
//        }
//        String userPassword = this.getUser(inputUsername).getPassword();
//        return userPassword.equals(inputPassword);  // check if user's password equals input password
//    }

//    public boolean userLogOff() {
//
//    }
//
//    public boolean loadUserTasks() {
//
//    }
//
//    public boolean userChangePassword() {
//
//    }
//
//    public boolean userChangeEmail() {
//
//    }

//    public static void main(String[] args) {
//
//    }
}
