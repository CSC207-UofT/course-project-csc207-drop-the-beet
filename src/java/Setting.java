/** Setting all user's preferences.
 * Input registration information, email, password.
 */
public class Setting {

    public boolean userRegister(String username, String email, String password, UserManager userManager) {
        if (userManager.containsUser(username)) {
            System.out.println("This username has been used!");
            return false;
        }
        userManager.addNewUser(username, email, password);
        return true;
    }

    public boolean userLogIn(String inputUsername, String inputPassword, UserManager userManager) {
        if (!userManager.containsUser(inputUsername)) {
            System.out.println("This username doesn't exist! Please double check.");
            return false;
        }
        String userPassword = userManager.getUser(inputUsername).getPassword();
        return userPassword.equals(inputPassword);  // check if user's password equals input password
    }

//    public boolean userLogOff() {
//        // Todo
//    }
//
//    public boolean userChangePassword(UserManager userManager) {
//        // Todo
//    }
//
//    public boolean userChangeEmail() {
//        // Todo
//    }
}
