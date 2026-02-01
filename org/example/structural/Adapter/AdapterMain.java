public class AdapterMain {
    public static void main(String[] args) {
        System.out.println("Adapter Pattern Example");
        PaymentPreprocessor paymentPreprocessor=new PhonePeAdapter();
        paymentPreprocessor.pay(5000);
        paymentPreprocessor=new PaypalAdapter();
        paymentPreprocessor.pay(6000);
    }
}