package BankMessengers;

public class UserMessenger {
    public static void main(String[] args) {
        new MyMessenger(MMP.UserQueueName, MMP.RaboQueueName);
    }
}
