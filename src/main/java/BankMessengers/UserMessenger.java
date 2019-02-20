package BankMessengers;

public class UserMessenger extends MyMessenger {

    public UserMessenger(String TargetBankName) {
        super(MessengerProperties.UserQueueName, TargetBankName);
    }
}
