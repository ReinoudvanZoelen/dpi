import BankMessengers.MessengerProperties;
import BankMessengers.MyMessenger;
import BankMessengers.RaboMessenger;

public class RaboStarter {
    public static void main(String[] args) {
        MyMessenger RaboMessenger = new RaboMessenger(MessengerProperties.UserQueueName);
    }
}
