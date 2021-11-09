package planner;

import java.util.*;

public class Main {

    private UserManager userManager = new UserManager();
    Setting setting = new Setting(userManager);

    public void enterMainMenu(){

        boolean isFlag = true;
        while(isFlag){
            System.out.println("\n___________________Welcome to Planner_______________________");
            System.out.println("                      1. User Login");
            System.out.println("                      2. User Register");
            System.out.println("                      3. Close Planner\n");
            System.out.println("                    Please Choose（1-3）");

            char menu = CMUtility.readMenuSelection();
            switch(menu){
                case '1':
                    userLogin();
                    break;
                case '2':
                    userRegister();
                    break;
                case '3':
                    System.out.println("Are You Sure?（Y/N)");
                    char isExit = CMUtility.readConfirmSelection();
                    if(isExit == 'Y'){
                        isFlag = false;
                    }
            }
        }
    }

    public void userRegister(){
        System.out.println("_______________________Register_________________________");
        System.out.println("Username(max 20 letters)：");
        String username = CMUtility.readString(20);
        System.out.println("Password(max 20 letters)：");
        String password = CMUtility.readString(20);
        String email;
        for(;;){
            System.out.println("Email：");
            email = CMUtility.readString(20);
            if(! email.contains("@")){
                System.out.println("Please Enter A Real Email!\n");
            }else{
                break;
            }
        }
        boolean isSuccess = setting.userRegister(username,email,password);

        if(isSuccess){
            System.out.println("\n_______________________Register Completed_________________________");
        }else{
            System.out.println("\n_________________Register Failed, Too Much Users____________________");
        }
    }

    public void userLogin(){
        System.out.println("_______________________Login_________________________");
        System.out.println("Username：");
        String username = CMUtility.readString(20);
        System.out.println("Password：");
        String password = CMUtility.readString(20);

        boolean isSuccess = setting.userLogIn(username,password);
        if(isSuccess){
            planMaker(username);
        }else{
            System.out.println("\n_________________________Login Failed__________________________");
        }
    }

    public void planMaker(String username){
        PlanMaker planMaker = new PlanMaker(username);
        boolean isFlag = true;
        while(isFlag){
            System.out.println("\n___________________Login, Now Start______________________");
            System.out.println("                      1. Schedule (To Be Continue)");
            System.out.println("                      2. ToDoList");
            System.out.println("                      3. Logout\n");
            System.out.println("                    Please Choose（1-3）");

            char menu = CMUtility.readMenuSelection();
            switch(menu){
                case '1':
                    // Schedule
                    break;
                case '2':
                    useToDoList(planMaker);
                    break;
                case '3':
                    System.out.println("Are You Sure?（Y/N)");
                    char isExit = CMUtility.readConfirmSelection();
                    if(isExit == 'Y'){
                        setting.userLogOff();
                        isFlag = false;
                    }
            }
        }
    }

    public void useToDoList(PlanMaker planMaker){
        boolean isFlag = true;
        while(isFlag){
            System.out.println("\n_______________________ToDoList______________________");
            System.out.println("                      1. New ToDOList");
            System.out.println("                      2. View All ToDOLists");
            System.out.println("                      3. Back\n");
            System.out.println("                     Please Choose（1-3）");

            char menu = CMUtility.readMenuSelection();
            switch(menu){
                case '1':
                    System.out.println("What is the name of this ToDoList：");
                    String name = CMUtility.readString(20);
                    planMaker.toDoListManager.addNewList(name);

//                    boolean isFlag2 = true;
//                    while(isFlag){
//                        System.out.println("\n___________________Login, Now Start______________________");
//                        System.out.println("                      1. Schedule (To Be Continue)");
//                        System.out.println("                      2. ToDoList");
//                        System.out.println("                      3. Logout\n");
//                        System.out.println("                    Please Choose（1-3）");
//
//                        char menu = CMUtility.readMenuSelection();
//                        switch(menu){
//                            case '1':
//                                // Schedule
//                                break;
//                            case '2':
//                                useToDoList(planMaker);
//                                break;
//                            case '3':
//                                System.out.println("Are You Sure?（Y/N)");
//                                char isExit = CMUtility.readConfirmSelection();
//                                if(isExit == 'Y'){
//                                    isFlag = false;
//                                }

                    System.out.println("What is the content, be brief:");
                    String content = CMUtility.readString(40);
                    planMaker.toDoListManager.getToDoList(name).addTask(content);
                    System.out.println("\n____________________ToDoList Added______________________");
                    break;
                case '2':
                    System.out.println(planMaker.toDoListManager);
                    break;
                case '3':
                    System.out.println("Are You Sure?（Y/N)");
                    char isExit = CMUtility.readConfirmSelection();
                    if(isExit == 'Y'){
                        isFlag = false;
                    }
            }
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.enterMainMenu();
    }

//    public static void main(String[] args) {
//        HashMap<String, User> users = new HashMap<>();
//        UserManager userManager = new UserManager();
//        Setting setting = new Setting(userManager);
//
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Welcome to Planner!");
//
//        boolean isFlag = true;
//        while (isFlag) {
//            System.out.println("1. Login, 2. Register");
//            String select = scanner.nextLine();
//            if (select.equals("2")) {
//                System.out.println("Please enter your username:");
//                String userName = scanner.nextLine();
//                System.out.println("Please enter your password:");
//                String password = scanner.nextLine();
//                System.out.println("Please enter your email:");
//                String email = scanner.nextLine();
//                if (setting.userRegister(userName, email, password)) {
//                    System.out.println("Back to the main page, please enter 1."); //should create schedule and todolist right after registration
//                    String enter = scanner.nextLine();
//                } else {
//                break;}
//            }
//            if (select.equals("1")) {
//                System.out.println("Please enter your username:");
//                String userName = scanner.nextLine();
//                System.out.println("Please enter your password:");
//                String password = scanner.nextLine();
//                setting.userLogIn(userName, password);
//                System.out.println("1. schedule, 2. to-do list");
//                String function = scanner.nextLine();
//                if (function.equals("1")) {
//                    System.out.println("Please enter the date: yyyy/mm/dd");
//                    String date = scanner.nextLine();
//                    System.out.println("Please enter the time: HH:mm");
//                    String time = scanner.nextLine();

//                    System.out.println("Please enter the event");
//                    String task = scanner.nextLine();
//                    PlanMaker planA = new PlanMaker(date, time, task); // all dates should be default in the schedule manager, shouldn't initialize here
//                }
//                if (function.equals("2")) { // should find list or create list first
//                    System.out.println("Please create a name of the list:");
//                    String name = scanner.nextLine();
//                    PlanMaker planB = new PlanMaker(name);
//                    System.out.println("Please add task");
//                    String task = scanner.nextLine(); // haven't done add task in PlanMaker now
//                }
//            }
//
//        }
//
//    }
}