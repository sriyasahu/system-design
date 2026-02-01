public class PhonePeAdapter implements PaymentPreprocessor{
    private PhonePe phonePe;

    @Override
    public void pay(double amount) {
        phonePe=PhonePe.getPhonePeInstance();
        phonePe.payViaPhonePe(amount);
    }
}
