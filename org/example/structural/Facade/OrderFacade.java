public class OrderFacade {
    private Shipping shipping;
    private Payment payment;
    private Inventory inventory;

    public OrderFacade() {
        shipping = new Shipping();
        payment = new Payment();
        inventory = new Inventory();
    }

    public void placeOrder(Product product) {
        try {
            System.out.println(" Now processing : " + product.description());
            inventory.updateInventory(product.description());
            Thread.sleep(2000);
            shipping.arrangeForShipping(product.description());
            Thread.sleep(2000);
            payment.processPayment(product.price());
            Thread.sleep(2000);

            System.out.println("Order is successfully placed!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
