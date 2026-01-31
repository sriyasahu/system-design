public class EmailNotification implements  Notification {
    @Override
    public void notifyUser(String message) {
        System.out.println("Sending Email notification with message: " + message);
    }
}
