package BankMessengers;

import implementations.MessageConsumerImplementations.MessageConsumerConsole;
import implementations.RabbitMQ.MessageReceiverRabbitMQ;
import implementations.RabbitMQ.MessageSenderRabbitMQ;
import interfaces.IMessageConsumer;
import interfaces.IMessageReceiver;
import interfaces.IMessageSender;

import java.util.Scanner;

public abstract class MyMessenger {
    final String ReceiverQueueName;
    final String SenderQueueName;

    public MyMessenger(String myQueueName, String targetQueueName) {
        ReceiverQueueName = myQueueName;
        SenderQueueName = targetQueueName;

        IMessageConsumer consumer = new MessageConsumerConsole();
        IMessageReceiver receiver = new MessageReceiverRabbitMQ(this.ReceiverQueueName, consumer);

        IMessageSender sender = new MessageSenderRabbitMQ(this.SenderQueueName, consumer);
        sender.SendMessage("First message");


        System.out.println("Enter your message: ");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String message = scanner.nextLine();
            sender.SendMessage(message);
        }
    }
}
