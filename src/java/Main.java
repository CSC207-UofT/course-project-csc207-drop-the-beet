
import java.util.*;

public class Main {

    public static void main(String[] args) {
        HashMap<String, User> users = new HashMap<>();
        UserManager userManager = new UserManager();
        Setting setting = new Setting();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Planner!");

        boolean isFlag = true;
        while (isFlag) {
            System.out.println("1. Login, 2. Register");
            String select = scanner.nextLine();
            if (select.equals("2")) {
                System.out.println("Please enter your username:");
                String userName = scanner.nextLine();
                System.out.println("Please enter your password:");
                String password = scanner.nextLine();
                System.out.println("Please enter your email:");
                String email = scanner.nextLine();
                if (userManager.userRegister(userName, email, password)) {
                    System.out.println("Back to the main page, please enter 1."); //should create schedule and todolist right after registration
                    String enter = scanner.nextLine();
                } else {
                break;}
            }
            if (select.equals("1")) {
                System.out.println("Please enter your username:");
                String userName = scanner.nextLine();
                System.out.println("Please enter your password:");
                String password = scanner.nextLine();
                setting.userLogIn(userName, password, userManager);
                System.out.println("1. schedule, 2. to-do list");
                String function = scanner.nextLine();
                if (function.equals("1")) {
                    System.out.println("Please enter the date: yyyy/mm/dd");
                    String date = scanner.nextLine();
                    System.out.println("Please enter the time: HH:mm");
                    String time = scanner.nextLine();
                    System.out.println("Please enter the event");
                    String task = scanner.nextLine();
                    PlanMaker planA = new PlanMaker(date, time, task); // all dates should be default in the schedule manager, shouldn't initialize here
                }
                if (function.equals("2")) { // should find list or create list first
                    System.out.println("Please create a name of the list:");
                    String name = scanner.nextLine();
                    PlanMaker planB = new PlanMaker(name);
                    System.out.println("Please add task");
                    String task = scanner.nextLine(); // haven't done add task in PlanMaker now
                }
            }

        }

    }
}