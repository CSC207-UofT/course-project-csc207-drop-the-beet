import java.util.HashMap;

public class UserManager {
    private HashMap<String, User> userHashMap;
    private User currentUser;

    public UserManager() {
        this.userHashMap = new HashMap<>();
        this.currentUser =
    }

    /**
     *
     * @param username: the user's preferred username
     * @param email: the user's preferred email
     * @param password: the user's preferred password
     * @return true if user has successfully created an account; false otherwise
     */
    public boolean UserRegister (String username, String email, String password) {
        if (this.userHashMap.containsKey(username)) {
            System.out.println("This username has been used!");
            return false;
        }
        // create a new user object and save it to the userHashMap
        User user = new User(username, email, password);
        this.userHashMap.put(username, user);
        return true;
    }

    /**
     *
     * @param inputUsername: the input username
     * @param inputPassword: the input password
     * @return true if the input username exists and the input password is correct
     */
    public boolean UserLogIn (String inputUsername, String inputPassword) {
        // check if the password corresponding to this user is the same as the input password
        if (!this.userHashMap.containsKey(inputUsername)) {
            System.out.println("This username doesn't exist! Please double check.");
            return false;
        }
        String userPassword = this.userHashMap.get(inputUsername).getPassword();
        return userPassword.equals(inputPassword);  // check if user's password equals input password
    }

    public boolean LoadUserTasks() {

    }
}
