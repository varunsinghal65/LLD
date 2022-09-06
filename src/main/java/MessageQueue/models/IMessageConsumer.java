package MessageQueue.models;

@FunctionalInterface
public interface IMessageConsumer {

    void consume(String message, Subscriber subscriber);
}
