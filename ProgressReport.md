#Progress Report

##Specification Summary:

In our program, the user can log into their accounts, modify their own information, and perform the following operations：
* The user can add/edit/remove schedules from the calendar and set up notifications for each schedule.
* The user can create to-do lists, add/edit/remove and view tasks in each list. Once they have done a task, they may mark the task as completed.

##CRC Model Summary:
We have created:
* Three entity classes: Schedule, ToDoList, User
* Three Use case Classes: ScheduleManager, ToDoListManager, UserManager
* Two controllers: PlanMaker, Setting
* One interface: Notification

We decided to use different use case classes to modify each entity class. Since we have found that all of the three entities are operated and implemented differently in most cases, one use case class per entity is easier to manage.

##Scenario Walkthrough Summary:
The scenario walkthrough is created based on the user’s view. It demonstrates how a user will use this application.
There are two main functions: schedule and to-do list. Schedule is a time-based task. The goal for Schedule is to arrange the user’s time. Notification on Schedule is to remind the user that there is a task coming at a specific time. The ToDoList is not time-based, the goal for ToDoList is to motivate the user to finish their tasks. The user can also set up Notification for ToDoList, so that the user can be motivated to finish these tasks.

##Skeleton program Summary:

##Open-ended questions:
##What has worked well for the team so far?
##Summary of what each group member has worked:
Yi:

Vincent:

Zheng:

Hao:

Sunny:

Yilin:
