package MessageQueue.services;

import MessageQueue.models.Subscriber;

import java.util.ArrayList;
import java.util.List;

public class FanoutService {

    public static void fanout(String msgToFanout, List<Subscriber> targetSubscribers) {
        List<Thread> fanoutWorkers = new ArrayList<>();
        targetSubscribers.forEach(subscriber -> {
            fanoutWorkers.add(new Thread(()->{
                subscriber.getConsumer().consume(msgToFanout, subscriber);
            }));
        });
        fanoutWorkers.forEach(Thread::start);
    }
}
