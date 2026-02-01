public class BuilderMain {
    public static void main(String args[]){
        System.out.println("Builder Pattern Example");

        // Calling the Lombok-generated builder
        Order myOrder = Order.builder()
                .product("Gaming Laptop")
                .size("15-inch")
                .giftWrapped(true)
                .color("Space Grey")
                .bankOfferApplicable(false)
                .primeDelivery(true)
                .build();

        // Testing the output
        System.out.println("Product: " + myOrder.toString());
    }
}