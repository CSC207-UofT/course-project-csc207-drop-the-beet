///** Setting all user's preferences.
// * Input registration information, email, password.
// */
//public class Setting {
//    UserManager um;
//
//    public Setting(UserManager userManager) {
//        this.um = userManager;
//    }
//
//    /**
//     * Register a user if the username does not exist in um. Upon a successful registration, the username and
//     * a User object will be stored in um
//     * @param username: input username
//     * @param email: input email
//     * @param password: input password
//     * @return true if a user is successfully registered; false otherwise.
//     */
//    public boolean userRegister(String username, String email, String password) {
//        if (this.um.containsUser(username)) {
//            System.out.println("This username has been taken!");
//            return false;
//        }
//        return this.um.addNewUser(username, email, password);
//    }
//
//    /**
//     * Log in a user with input username if:
//     * - no user currently logged in
//     * - the username exists
//     * - the password is correct
//     * After a user is logged in, um will change currentUser to the user associated with the input username
//     * @param inputUsername: input username
//     * @param inputPassword: input password
//     * @return true if a user is successfully logged in; false otherwise.
//     */
//    public boolean userLogIn(String inputUsername, String inputPassword) {
//        if (this.um.getCurrentUser() != null) {  // return false if any user is logged in
//            System.out.println("Another user is currently logged in!");
//            return false;
//        }
//
//        if (!this.um.containsUser(inputUsername)) {  // username exists?
//            System.out.println("This username doesn't exist! Please double check.");
//            return false;
//        }
//
//        String userPassword = this.um.getUser(inputUsername).getPassword();  // password stored in userManager
//        if (!userPassword.equals(inputPassword)) {  // check if user's password equals input password
//            System.out.println("Password is incorrect!");
//            return false;
//        }
//
//        this.um.changeCurrentUser(inputUsername);  // update current user
//        return true;
//    }
//
//    /**
//     * Log off the current user if there is a current user.
//     * @return true if successfully logged off.
//     */
//    public boolean userLogOff() {
//        if (this.um.getCurrentUser() == null) {
//            System.out.println("No user currently logged in!");
//            return false;
//        }
//        this.um.logOffCurrentUser();
//        return true;
//    }
//
//    /**
//     * Change the current user's password if:
//     * - current user is not null
//     * - input password matches the previously stored password
//     * @param inputPassword: input password
//     * @param newPassword: new password
//     * @return true if successful; false otherwise.
//     */
//    public boolean userChangePassword(String inputPassword, String newPassword) {
//        if (this.um.getCurrentUser() == null) {
//            System.out.println("No user currently logged in!");
//            return false;
//        }
//        String prevPassword = this.um.getCurrentUser().getPassword();
//        if (!inputPassword.equals(prevPassword)) {
//            System.out.println("Password is incorrect!");
//            return false;
//        }
//        this.um.currentUserChangePassword(newPassword);
//        return true;
//    }
//
////    public boolean userChangeEmail(String inputEmail, String newEmail) {  // TODO: Change this method
////        if (this.um.getCurrentUser() == null) {
////            System.out.println("No user currently logged in!");
////            return false;
////        }
////        String prevEmail = this.um.getCurrentUser().getEmail();
////        if (!inputEmail.equals(prevEmail)) {
////            System.out.println("Email is incorrect!");
////            return false;
////        }
////        this.um.currentUserChangePassword(newEmail);
////        return true;
////    }
//
////    public static void main(String[] args) {
////        UserManager um = new UserManager();
////        System.out.println(um.getCurrentUser());
////        Setting s = new Setting(um);
////        s.userLogIn("12321", "password");
////    }
//}
