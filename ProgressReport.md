# Progress Report

## Specification Summary

In our program, the user can log into their accounts, modify their own information, and perform the following operations：
* The user can add/edit/remove schedules from the calendar and set up notifications for each schedule.
* The user can create to-do lists, add/edit/remove and view tasks in each list. Once they have done a task, they may mark the task as completed.

## CRC Model Summary
We have created:
* Three entity classes: Schedule, ToDoList, User
* Three Use case Classes: ScheduleManager, ToDoListManager, UserManager
* Two controllers: PlanMaker, Setting
* One interface: Notification

We decided to use different use case classes to modify each entity class. Since we have found that all of the three 
entities are operated and implemented differently in most cases, one use case class per entity is easier to manage.

## Scenario Walkthrough Summary
The scenario walkthrough is created based on the user’s view. It demonstrates how a user will use this application.

There are two main functions: schedule and to-do list. Schedule is a time-based task. The goal for Schedule is to 
arrange the user’s time. Notification on Schedule is to remind the user that there is a task coming at a specific time. 
The ToDoList is not time-based, the goal for ToDoList is to motivate the user to finish their tasks. The user can also 
set up Notification for ToDoList, so that the user can be motivated to finish these tasks. In general, the Schedule is 
for long-term planning of day-to-day events/tasks. The ToDoList is for short-term daily notes.

## Skeleton program Summary
We have created all the classes and interface described in the scenario walkthrough and all of the commands in 
specification. The classes include the entity classes including Schedule, ToDoList, User. Use case classes including 
ScheduleManager. ToDoListManager, UserManager. As well as an interface Notification that is implemented by those 
classes. In addition, we included controllers PlanMaker and Setting for the user to edit the Schedules/ToDoList they 
wish to edit and change their profile settings. The commands includes Delect, Edit, Flag, List, Man, Past, PastDue, 
Select, Today, Add_List, New_Reminder. We have built the skeleton including constructors, some complete methods and 
method signatures that need to be implemented. We also have tests for Schedule and ToDoList and their use case classes, 
and they are all passed.

## Open-ended questions
* How would the users interact with our program? Android or text UI?
* Should we compose User with ToDoLishManager(use case) or ToDoList(entity)? (We are considering letting Users have an array of ToDoLists.)
* Where can we store a current user object, UserManager(use case) or Setting(controller)?
* How are we supposed to store the data of each user? Should we store all user login data separate from their schedules or todolist data or should they be together? Is there a difference in memory usage? Efficiency?
* Since we are putting logIn and register methods in Setting, which is a controller, we cannot refer to User directly. Are we supposed to take a UserManager(use case) object as a parameter in logIn and register? Moreover, are we supposed to implement these two methods in UserManager and just make one-line function calls in logIn and register?
* Should we let the Schedule and ToDoList classes implement Notification or should ScheduleManager and ToDoList classes implement Notification?

## What has worked well for the team so far with your design as you have started implementing the code?
We think making the Notification class an interface is well designed because Schedule and ToDoList have different 
notification and notification settings. Thus, making the notification class an interface allows us to polymorph the 
relevant methods for notification purposes in our use case classes.
When implementing the classes, we followed the single responsibility to make sure each class only does things that are 
closely related to it. Those are what we have worked well so far.
## Summary of what each group member has worked
### We have been working on
Yi: scenario walkthrough, CRC Model, skeleton (User, UserManager, Setting), progress report

Vincent: scenario walkthrough, CRC Model, skeleton (Schedule, ScheduleManager, ScheduleTest), progress report

Zheng: CRC Model, Skeleton (ToDoList, ToDoListManager), progress report

Hao: specification, command line design, ideas for front-end design, progress report

Sunny: specification, command line design, progress report

Yilin: scenario walkthrough, CRC Model, skeleton integration and (PlanMaker, ToDoListTest), progress report

### We plan to work on next
Yi: Compose User with ToDoListManager and ScheduleManager; implement User,
UserManager and Setting; let UserManager and Setting implement Notification if needed.

Vincent: Implement the Schedule, ScheduleManager, and any Notification related problems.

Zheng:  Implement the ToDoList, ToDoListManager, and any Notification related problems. And are responsible for keeping 
in touch with our TA.

Hao: Implement the Command-line interface including word parsing,  building syntax tree, and semantic analysis. Build 
the command line application and the manual page of the commands.

Sunny:Implement the GUI interface and DBMS to manage the data storage. Build the web-based application interface. And 
build software documentation for the GUI interface.

Yilin: Implement PlanMaker. Push and assist other members to work. Integration of all classes, ensure the consistency 
of code between classes, ensure our work is on track.  

From phase 0, we have found each member’s role in this team. Yi, Vincent and mainly do back-end programs. Hao and Sunny 
perform more on front-end work. Zheng works in back-end programs and is responsible for communicating with our TA. 
Yilin works in back-end programs and performs as a project manager in this group. Any of us helped and assisted in each 
other’s work if necessary. In the future phases, we will carry out this division of labor. 