public class PhonePe {
    private static volatile PhonePe phonePeInstance;

    private PhonePe(){
        System.out.println("Initializing one Phonepe instance ........");
    }

    public static PhonePe getPhonePeInstance(){
        if(phonePeInstance==null){
            synchronized (PhonePe.class){
                if(phonePeInstance==null){
                    phonePeInstance=new PhonePe();
                }
            }
        }
        return phonePeInstance;
    }

    public void payViaPhonePe(double amount){
        System.out.println("Payment of : " + amount + " has been paid by PhonePe!");
    }
}
