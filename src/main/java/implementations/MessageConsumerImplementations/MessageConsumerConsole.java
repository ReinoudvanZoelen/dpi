package implementations.MessageConsumerImplementations;

import interfaces.IMessageConsumer;

public class MessageConsumerConsole implements IMessageConsumer {

    @Override
    public void ConsumeMessage(String message) {
        System.out.println(message);
    }
}
