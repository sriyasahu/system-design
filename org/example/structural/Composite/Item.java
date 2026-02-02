public class Item implements Product{
    double price;
    String description;

    public Item (double price, String description) {
        this.price = price;
        this.description = description;
    }


    @Override
    public String description() {
        return description;
    }

    @Override
    public double price() {
        return price;
    }
}
