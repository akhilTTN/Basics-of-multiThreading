package com.java.Exercise;

/* Solve problem 7 using BlockingQueue */

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Exercise8 {
    int nextInitial = 1;
    private int initialCapacity = 10;
    private BlockingQueue<Integer> list = new ArrayBlockingQueue<Integer>(initialCapacity);

    public static void main(String[] args) {
        Exercise8 threadSignaling = new Exercise8();
        Thread publisher1 = new Thread(() -> threadSignaling.publisher());
        publisher1.start();
        Thread publisher2 = new Thread(() -> threadSignaling.publisher());
        publisher2.start();
        Thread publisher3 = new Thread(() -> threadSignaling.publisher());
        publisher3.start();

        Thread subscriber1 = new Thread(() -> threadSignaling.subscriber());
        subscriber1.start();
        Thread subscriber2 = new Thread(() -> threadSignaling.subscriber());
        subscriber2.start();
        Thread subscriber3 = new Thread(() -> threadSignaling.subscriber());
        subscriber3.start();

    }

    public void subscriber() {
        int message = 0;
        try {
            message = list.take();
        } catch (InterruptedException e) {
        }
        System.out.println(Thread.currentThread().getName() + " " + nextInitial + ". Message subscribed..." + message);
    }

    public void publisher() {

        try {
            list.put(nextInitial);
        } catch (InterruptedException e) {
        }
        System.out.println(Thread.currentThread().getName() + " " + nextInitial + ". Message published..." + nextInitial);
        nextInitial++;
    }
}
