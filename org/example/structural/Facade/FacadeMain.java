public class FacadeMain {
    public static void main(String args[]) {
        System.out.println("--- Facade Pattern Example ---");

        // 1. Create the product (using your Decorator pattern if you want!)
        // Let's create a Basic Product with a Gift Wrap
        Product myLaptop = new GiftWrap(new BasicProduct("MacBook Pro", 2000.00));

        // 2. Initialize the Facade
        // The client doesn't need to know about Shipping, Payment, or Inventory
        OrderFacade orderFacade = new OrderFacade();

        // 3. Place the order with one single call
        // This triggers the sequential steps inside the Facade
        orderFacade.placeOrder(myLaptop);
    }
}