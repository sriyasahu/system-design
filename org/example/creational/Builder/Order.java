//import lombok.Builder;
//@Builder
//public class Order {
//    private String product;
//    private String size;
//    private boolean giftWrapped;
//    private String color;
//    private boolean bankOfferApplicable;
//    private boolean primeDelivery;
//
//    @Override
//    public String toString() {
//        return "Order{" +
//                "product='" + product + '\'' +
//                ", size='" + size + '\'' +
//                ", giftWrapped=" + giftWrapped +
//                ", color='" + color + '\'' +
//                ", bankOfferApplicable=" + bankOfferApplicable +
//                ", primeDelivery=" + primeDelivery +
//                '}';
//    }
//
//}
//
//

public class Order{
    private String product;
    private String size;
    private boolean giftWrapped;
    private String color;
    private boolean bankOfferApplicable;
    private boolean primeDelivery;

    private Order(OrderBuilder builder){
        this.product = builder.product;
        this.size = builder.size;
        this.giftWrapped = builder.giftWrapped;
        this.color = builder.color;
        this.bankOfferApplicable = builder.bankOfferApplicable;
        this.primeDelivery = builder.primeDelivery;
    }

    @Override
    public String toString() {
        return "Order{" +
                "product='" + product + '\'' +
                ", size='" + size + '\'' +
                ", giftWrapped=" + giftWrapped +
                ", color='" + color + '\'' +
                ", bankOfferApplicable=" + bankOfferApplicable +
                ", primeDelivery=" + primeDelivery +
                '}';
    }

    public static OrderBuilder builder(){
        return new OrderBuilder();
    }

    public static class OrderBuilder {
        private String product;
        private String size;
        private boolean giftWrapped;
        private String color;
        private boolean bankOfferApplicable;
        private boolean primeDelivery;

        public OrderBuilder product(String product){
            this.product = product;
            return this;
        }

        public OrderBuilder size(String size){
            this.size = size;
            return this;
        }

        public OrderBuilder color(String color){
            this.color = color;
            return this;
        }

        public OrderBuilder giftWrapped(boolean giftWrapped){
            this.giftWrapped = giftWrapped;
            return this;
        }

        public OrderBuilder bankOfferApplicable(boolean bankOfferApplicable){
            this.bankOfferApplicable = bankOfferApplicable;
            return this;
        }

        public OrderBuilder primeDelivery(boolean primeDelivery){
            this.primeDelivery = primeDelivery;
            return this;
        }

        public Order build(){
            return new Order(this );
        }

    }
}