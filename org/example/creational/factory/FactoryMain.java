public class FactoryMain {
    public static void main(String[] args) {
        System.out.println("--- Factory Design Pattern Example ---\n");

        // 1. Ask the Factory for an EMAIL notification
        Notification email = NotificationFactory.createNotification("EMAIL");
        if (email != null) {
            email.notifyUser("Your order has been shipped!");
        }

        // 2. Ask the Factory for an SMS notification
        Notification sms = NotificationFactory.createNotification("SMS");
        if (sms != null) {
            sms.notifyUser("Your package will be delivered today.");
        }

        // 3. Testing an invalid type
        Notification unknown = NotificationFactory.createNotification("PUSH");
        if (unknown == null) {
            System.out.println("\nNotification type 'PUSH' is not supported yet.");
        }
    }
}