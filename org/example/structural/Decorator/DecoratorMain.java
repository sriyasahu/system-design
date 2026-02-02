public class DecoratorMain {
    public static void main(String []args){
        System.out.println("--- Decorator Pattern Example ---");

        // 1. Start with a basic product
        Product myOrder = new BasicProduct("Gaming Laptop", 1000.00);

        // 2. Wrap it with Gift Wrap
        myOrder = new GiftWrap(myOrder);

        // 3. Wrap it again with Extended Warranty
        // Now 'myOrder' contains a Warranty, which contains a GiftWrap, which contains a Laptop
        myOrder = new ExtendedWarranty(myOrder);

        // 4. Print results
        System.out.println("Final Description: " + myOrder.description());
        System.out.println("Total Price: " + myOrder.price());
    }
}