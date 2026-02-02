public class CompositeMain {
    public static void main(String []args){
        Product item1 = new Item(10.0,"Item 1");
        Product item2 = new Item(20.0,"Item 2");
        Product item3 = new Item(30.0,"Item 3");

        ProductBundle bundle1 = new ProductBundle("Bundle 1");
        bundle1.addToProductList(item1);
        bundle1.addToProductList(item2);

        ProductBundle bundle2 = new ProductBundle("Bundle 2");
        bundle2.addToProductList(bundle1);
        bundle2.addToProductList(item3);

        System.out.println("Description: " + bundle2.description());
        System.out.println("Total Price: " + bundle2.price());
    }
}
