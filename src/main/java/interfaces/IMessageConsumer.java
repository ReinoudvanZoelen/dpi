package interfaces;

import implementations.MessageConsumerImplementations.MessageAction;

public interface IMessageConsumer {
    public void ConsumeMessage(MessageAction action, String message);
}
