## Planner


**Specification** 

Running the project starts a web-based service that allows users to interact with a daily planner web-application. While running, the web-application will run a daily planner with a graphic user interface. On the interface, users can add, delete, search, or modify their daily plans by clicking on the buttons –  selecting the cascader and using the checkbox, and input data with a basic widget to interact with the system.
The web-application is to support multi-users and mysql database search as well as the message queues.

* Login: This feature will prompt a widget which allows users to enter their username and password. The user identity is then authenticated.

* New Reminder: Add a new plan to the to do list of the current user. This will prompt a widget that allows users to input the event, place, flagged or not, and due date, the time remaining is then being calculated and will appear on this event.

* Check Box: This feature allows users to mark any completed events. The event is then marked as past and the time before due will not be calculated.

* Delete: This button allows users to delete an event. The event will be marked as deleted and will appear in the trash bin. The time remaining is still being calculated and the users are still able to check this event in the trash bin.

* Edit: This button will enable the users to edit any events they wish to modify. A widget is prompted with contents recorded in the database. The users are able to modify the event, place, and due date. Then, it will be updated in the database.

* Add List: This feature allows users to add a custom to-do list. This will prompt a widget that allows users to input the name of the to-do list, and the priority of the list. Then a to-do list will be created in the system.

* All: This feature allows users to retroact all the reminders. A new page will be prompted and list all the reminders including the past ones.

* Today: This feature allows users to retroact all the reminders that will be due by today. A new page will be prompted and list all the events that will be due by today in the user's to-do list.

* Flagged: This feature allows users to retroact all the reminders that are flagged. A new page will be prompted and list all the reminders that are flagged.

* Past: This feature allows users to retroact all the reminders that have passed due. A new page will be prompted and list all the reminders that are past events.

* Past due: This feature allows users to retroact all the reminders that are not completed and have passed due. A new page will be prompted and list all the reminders that are not completed and have passed due.


**Scenario Walkthrough**

* Schedule: task, start time, duration, due date, alarmAt, repeated(boolean)
* ToDoList: tasks(ArrayList<String>), due date, alarmAt(ArrayList<LocalDate>), repeated(boolean)
* User: name(String), password(String), email(String)
* Notification: Schedule, ToDoList, Holidays,  Birthday, (alarm sound)

A user can log in/register (with username, email, password) and create schedules/to-do lists stored in the user’s information (hashmap/ArrayList). All of their information can be stored and back-uped on different devices. A schedule can be repeatable in a certain time interval. The user can arrange their schedule on a calendar, check the user’s birthday and public holiday. They will get a notification of their schedule. The user can also make up their to-do list and once they complete their tasks they can mark the tasks as complete. Then it is removed from the user’s tasks and added to the user’s completed tasks. The to-do list has no time limit, but the user can still set up the notification at a specific time about how many tasks are waiting for completion.  Unfinished to-do lists can pass to the next day.