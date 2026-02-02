public class StrategyMain {
    public static void main(String []args){
        System.out.println("Strategy Pattern Example");
        ShoppingCart cart = new ShoppingCart(new LoyaltyDiscountStrategy());
        System.out.println("discounted price"+cart.checkout(100.0));
    }
}
