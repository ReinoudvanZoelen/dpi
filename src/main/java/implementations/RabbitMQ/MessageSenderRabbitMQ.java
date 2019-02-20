package implementations.RabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import implementations.MessageConsumerImplementations.MessageAction;
import interfaces.IMessageConsumer;
import interfaces.IMessageSender;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MessageSenderRabbitMQ implements IMessageSender {
    private final String exchangeName;

    private IMessageConsumer messageConsumer;

    private Connection connection;
    private Channel channel;

    public MessageSenderRabbitMQ(String exchangeName, IMessageConsumer messageConsumer) {
        this.exchangeName = exchangeName;
        this.messageConsumer = messageConsumer;
        this.connectMessagingService();
    }

    private void connectMessagingService() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try {
            this.connection = factory.newConnection();
            this.channel = connection.createChannel();

            channel.exchangeDeclare(exchangeName, "fanout");
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void SendMessage(String message) {
        try {
            this.messageConsumer.ConsumeMessage(MessageAction.SEND, message);
            channel.basicPublish(exchangeName, "", null, message.getBytes("UTF-8"));
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
