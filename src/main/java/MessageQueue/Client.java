package MessageQueue;

import MessageQueue.models.IMessageConsumer;
import MessageQueue.models.Subscriber;


/**
 * @see https://www.youtube.com/watch?v=4BEzgPlLKTo
 * @see https://workat.tech/machine-coding/practice/design-distributed-queue-cuudq0sk0v14
 */

public class Client {

    public static void main(String[] args) {
        MessageQueue mq = new MessageQueue();

        mq.createTopic("topic1");
        mq.createTopic("topic2");

        IMessageConsumer messageConsumer = ((msg, sub)-> System.out.println(sub.getId() + ": " + msg + " received."));
        Subscriber s1 = new Subscriber("s1", messageConsumer);
        Subscriber s2 = new Subscriber("s2", messageConsumer);
        Subscriber s3 = new Subscriber("s3", messageConsumer);
        Subscriber s4 = new Subscriber("s4", messageConsumer);
        Subscriber s5 = new Subscriber("s5", messageConsumer);

        mq.subscribe(s1, "topic1");
        mq.subscribe(s2, "topic1");
        mq.subscribe(s3, "topic1");
        mq.subscribe(s4, "topic1");
        mq.subscribe(s5, "topic1");

        mq.subscribe(s1, "topic2");
        mq.subscribe(s3, "topic2");
        mq.subscribe(s4, "topic2");

        Thread p1 = new Thread(()->{
           mq.publish("Message1", "topic1");
           mq.publish("Message2", "topic1");
           mq.publish("Message4", "topic2");
        });
        Thread p2 = new Thread(()->{
           mq.publish("Message3", "topic1");
           mq.publish("Message5", "topic2");
        });
        p1.start();
        p2.start();
    }

}
