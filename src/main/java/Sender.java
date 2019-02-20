import implementations.RabbitMQ.MessageSenderRabbitMQ;
import interfaces.IMessageSender;

public class Sender {
    public static void main(String[] args) {
        String queueName = "hello";
        IMessageSender sender = new MessageSenderRabbitMQ(queueName);
        sender.SendMessage("Hallo, luistert er iemand?");
    }
}
