package MessageQueue.models;

import java.util.Objects;

public class Subscriber {
    private final String id;
    private IMessageConsumer consumer;

    public Subscriber(String id, IMessageConsumer consumer) {
        this.id = id;
        this.consumer = consumer;
    }

    public String getId() {
        return id;
    }

    public IMessageConsumer getConsumer() {
        return consumer;
    }

    public void setConsumer(IMessageConsumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscriber that = (Subscriber) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
