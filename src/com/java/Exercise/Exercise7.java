package com.java.Exercise;

/*Implement a Pub-Sub model using following criteria:
There is a queue (i.e list) with a certain capacity (say 10)
M number of publisher and N number of subscriber sharing the queue.
Publisher has to wait if queue capacity is full
Subscriber will wait if queue is empty
Use thread signaling (wait, notify / notifyAll)
*/

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Exercise7 {
    private int initialCapacity = 10;
    private List<String> list = new ArrayList<>(initialCapacity);
    int nextInitial = 0;

    public static void main(String[] args) {
        Exercise7 threadSignaling = new Exercise7();

        Thread publisher1 = new Thread(() -> threadSignaling.publisher());
        publisher1.start();

        Thread subscriber1 = new Thread(() -> threadSignaling.subscriber());
        subscriber1.start();

    }

    public void subscriber() {

        while (nextInitial < initialCapacity) {
            synchronized (this) {
                try {
                    while (list.isEmpty()) {
                        this.wait();
                    }
                    System.out.println(Thread.currentThread().getName() + " " + nextInitial + ". Message subscribed..." + list.remove(0));
                    this.notifyAll();
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public void publisher() {

        while (nextInitial < initialCapacity) {
            String message;
            synchronized (this) {
                try {
                    while (!list.isEmpty()) {
                        this.wait();
                    }
                    message = UUID.randomUUID().toString();
                    list.add(message);
                    System.out.println(Thread.currentThread().getName() + " " + nextInitial + ". Message published..." + message);
                    nextInitial++;
                    this.notifyAll();
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
