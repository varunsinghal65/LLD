package MessageQueue;

import MessageQueue.models.Subscriber;
import MessageQueue.models.Topic;
import MessageQueue.services.FanoutService;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MessageQueue {

    private final HashMap<String, Topic> topics;
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock readLock = rwLock.readLock();
    private final Lock writeLock = rwLock.readLock();

    MessageQueue(){
        topics = new HashMap<>();
    }

    public void createTopic(String id) {
        try {
            writeLock.lock();
            Topic newTopic = new Topic(id);
            topics.put(id, newTopic);
        } finally {
            writeLock.unlock();
        }
    }

    //publish reads map
    public void publish(String msgToPublish, String topicId) {
        try {
            readLock.lock();
            Topic targetTopic = topics.get(topicId);
            if (targetTopic == null)
                throw new RuntimeException("topicid:" + topicId + "does not exist in the system");
            List<Subscriber> subs = targetTopic.getSubscribers();
            FanoutService.fanout(msgToPublish, subs);
        } finally {
            readLock.unlock();
        }
    }

    public void subscribe(Subscriber toAdd, String topicId) {
        try {
            readLock.lock();
            Topic targetTopic = topics.get(topicId);
            if (targetTopic == null)
                throw new RuntimeException("topicid:" + topicId + "does not exist in the system");
            targetTopic.addSubscriber(toAdd);
        } finally {
            readLock.unlock();
        }
    }
}
