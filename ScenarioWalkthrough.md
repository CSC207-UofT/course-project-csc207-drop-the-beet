#Scenario Walkthrough

* Schedule: task, start time, duration, due date, alarmAt, repeated(boolean)
* ToDoList: tasks(ArrayList<String>), due date, alarmAt(ArrayList<LocalDate>), repeated(boolean)
* User: name(String), password(String), email(String)
* Notification: Schedule, ToDoList, Holidays,  Birthday, (alarm sound)

A user can log in/register (with username, email, password) and create schedules and to-do lists stored in the user’s 
information (hashmap/ArrayList). All of their information can be stored and back-uped on different devices. A schedule 
can be repeatable in a certain time interval. The user can arrange their schedule on a calendar, check the user’s 
birthday and public holiday. They will get a notification of their schedule. The user can also make up their to-do list 
and once they complete their tasks they can mark the tasks as complete. Then it is removed from the user’s tasks and 
added to the user’s completed tasks. The to-do list has no time limit, but the user can still set up the notification 
at a specific time about how many tasks are waiting for completion.