package MessageQueue.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Topic {

    private String id;
    Set<Subscriber> subscribers;
    List<String> messages;
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    Lock readLock = rwLock.readLock();
    Lock writeLock = rwLock.writeLock();

    public Topic(String id) {
        this.id = id;
        subscribers = new HashSet<>();
    }

    public void addSubscriber(Subscriber sub) {
        try {
            writeLock.lock();
            if (subscribers.contains(sub))
                throw new IllegalArgumentException("Subscriber with id" + sub.getId() + " already exists int he system");
            subscribers.add(sub);
        } finally {
            writeLock.unlock();
        }
    }

    public List<Subscriber> getSubscribers() {
        try {
            readLock.lock();
            return new ArrayList<>(subscribers);
        } finally {
            readLock.unlock();
        }
    }

    public synchronized void addMessage(String msg) {
        messages.add(msg);
    }

    public synchronized List<String> getMessages() {
        return new ArrayList<>(messages);
    }
}
