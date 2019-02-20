package implementations.RabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import interfaces.IMessageConsumer;
import interfaces.IMessageReceiver;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MessageReceiverRabbitMQ implements IMessageReceiver {
    private final String queueName;

    private IMessageConsumer messageConsumer;

    private Connection connection;
    private Channel channel;

    public MessageReceiverRabbitMQ(String queueName, IMessageConsumer messageConsumer) {
        this.queueName = queueName;
        this.messageConsumer = messageConsumer;

        this.connectMessagingService();
    }

    private void connectMessagingService() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = null;
        try {
            connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.queueDeclare(queueName, false, false, false, null);


            this.consumeMessage(" [*] Waiting for messages. To exit press CTRL+C");

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                this.consumeMessage(" [M] Received message: " + message);
            };
            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
            });

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    private void consumeMessage(String message) {
        this.messageConsumer.ConsumeMessage(message);
    }

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
