package com.java.example;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PubSubWithReentrant {
    private List<String> queue = new ArrayList<>();
    ReentrantLock mutex = new ReentrantLock(true);// fair lock

    public static void main(String[] args) throws InterruptedException {
        PubSubWithReentrant pubSub = new PubSubWithReentrant();
        Condition reader = pubSub.mutex.newCondition();
        Condition writer = pubSub.mutex.newCondition();

        ExecutorService executor = Executors.newFixedThreadPool(5);
        executor.execute(() -> pubSub.publish(reader, writer));
        executor.execute(() -> pubSub.subscribe(reader, writer));

        executor.shutdown();
    }

    private void publish(Condition reader, Condition writer){
        String message;
        for(int i = 0; i < 10; i++) {

            mutex.lock();

            while (queue.size() == 3) {
// wait
                try {
                    writer.await();
                } catch (InterruptedException e) {}
            }
            message = UUID.randomUUID().toString();
            System.out.println(Thread.currentThread().getName()+": Publish......"+message);
            queue.add(message);
// notify sub
            reader.signal();

            mutex.unlock();
        }
    }

    private void subscribe(Condition reader, Condition writer){
        for(int i = 0; i < 10; i++) {

            mutex.lock();

            while (queue.size() == 0) {
// wait
                try {
                    reader.await();
                } catch (InterruptedException e) {}
            }
            System.out.println(Thread.currentThread().getName() + ": Subscribe......" + queue.remove(0));
// notify pub
            writer.signal();

            mutex.unlock();
        }
    }
}