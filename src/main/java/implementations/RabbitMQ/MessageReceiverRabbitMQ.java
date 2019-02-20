package implementations.RabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import implementations.MessageConsumerImplementations.MessageAction;
import interfaces.IMessageConsumer;
import interfaces.IMessageReceiver;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MessageReceiverRabbitMQ implements IMessageReceiver {
    private final String exchangeName;

    private IMessageConsumer messageConsumer;

    private Connection connection;
    private Channel channel;

    public MessageReceiverRabbitMQ(String exchangeName, IMessageConsumer messageConsumer) {
        this.exchangeName = exchangeName;
        this.messageConsumer = messageConsumer;

        this.connectMessagingService();
    }

    private void connectMessagingService() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            this.connection = factory.newConnection();
            this.channel = connection.createChannel();

            channel.exchangeDeclare(exchangeName, "fanout");
            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, exchangeName, "");

            consumeMessage("Waiting for messages. To exit press CTRL+C");

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                this.consumeMessage(message);
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
        this.messageConsumer.ConsumeMessage(MessageAction.RECEIVE, message);
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
