package BankMessengers;

public class RaboMessenger extends MyMessenger{

    public RaboMessenger(String TargetUserName) {
        super(MessengerProperties.RaboQueueName, TargetUserName);
    }
}
