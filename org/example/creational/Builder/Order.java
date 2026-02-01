import lombok.Builder;
@Builder
public class Order {
    private String product;
    private String size;
    private boolean giftWrapped;
    private String color;
    private boolean bankOfferApplicable;
    private boolean primeDelivery;

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

}
