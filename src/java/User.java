import java.util.ArrayList;

/** User contains basic information of this user.
 */

public class User {
    public String name;
    private String email;
    private String password;
//    public ArrayList<>

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
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    public String getName() { return this.name;}
}
