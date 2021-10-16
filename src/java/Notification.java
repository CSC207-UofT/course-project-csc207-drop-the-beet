public interface Notification {

    boolean notificationOn = true;
    // TODO: notification is on by default?
    int[] notificationPeriod = new int[2];

    default void switchNotification() {
        // TODO: find a new name
    }

}