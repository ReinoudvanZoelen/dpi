package implementations.MessageConsumerImplementations;

import interfaces.IMessageConsumer;

public class MessageConsumerConsole implements IMessageConsumer {

    @Override
    public void ConsumeMessage(MessageAction action, String message) {
        System.out.println("[" + action.name() + "] " + message);
    }
}

