import BankMessengers.MessengerProperties;
import BankMessengers.UserMessenger;

public class UserStarter {
    public static void main(String[] args) {
        UserMessenger RaboMessenger = new UserMessenger(MessengerProperties.RaboQueueName);
    }
}
