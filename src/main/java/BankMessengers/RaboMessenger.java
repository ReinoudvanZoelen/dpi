package BankMessengers;

public class RaboMessenger{

    public static void main(String[] args) {
        new MyMessenger(MMP.RaboQueueName, MMP.UserQueueName);
    }
}
