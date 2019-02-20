import implementations.MessageConsumerImplementations.MessageConsumerConsole;
import implementations.RabbitMQ.MessageReceiverRabbitMQ;
import interfaces.IMessageConsumer;
import interfaces.IMessageReceiver;

public class Receiver {
    public static void main(String[] args) {
        String queueName = "hello";
        IMessageConsumer consumer = new MessageConsumerConsole();
        IMessageReceiver receiver = new MessageReceiverRabbitMQ(queueName, consumer);
    }
}
