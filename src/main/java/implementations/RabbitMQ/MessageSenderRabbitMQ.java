package implementations.RabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import interfaces.IMessageSender;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MessageSenderRabbitMQ implements IMessageSender {
    private final String queueName;

    private Connection connection;
    private Channel channel;

    public MessageSenderRabbitMQ(String queueName) {
        this.queueName = queueName;

        this.connectMessagingService();
    }

    private void connectMessagingService() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try {
            this.connection = factory.newConnection();
            this.channel = connection.createChannel();
            this.channel.queueDeclare(queueName, false, false, false, null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void SendMessage(String message) {
        try {
            this.channel.basicPublish("", queueName, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Disconnect() {
        try {
            this.channel.close();
            this.connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
