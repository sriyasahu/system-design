public class Main {
    public static void main(String[] args) {

        // 1. Get the first instance
        PaymentGateway paymentGateway1 = PaymentGateway.getPaymentGatewayInstance();
        paymentGateway1.processPayment("ORD123", 250.00);

        // 2. Try to get another instance
        PaymentGateway paymentGateway2 = PaymentGateway.getPaymentGatewayInstance();
        paymentGateway2.processPayment("ORD456", 100.50);

        // 3. Verify they are the exact same object in memory
        if (paymentGateway1 == paymentGateway2) {
            System.out.println("\nSUCCESS: Both variables point to the same instance.");
        } else {
            System.out.println("\nFAILURE: Multiple instances were created.");
        }

        // Printing hashcodes to double-check
        System.out.println("Instance 1 Hash: " + paymentGateway1.hashCode());
        System.out.println("Instance 2 Hash: " + paymentGateway2.hashCode());
    }
}