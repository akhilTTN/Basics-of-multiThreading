package com.java.Exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Exercise7a {
    int nextInitial = 1;
    private int initialCapacity = 10;
    private List<Integer> list = new ArrayList<>(initialCapacity);

    public static void main(String[] args) {
        Exercise7a threadSignaling = new Exercise7a();
        int mPublisher, nSubscriber;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Number of Publisher: ");
        mPublisher = scanner.nextInt();

        System.out.print("Enter Number of Subscriber: ");
        nSubscriber = scanner.nextInt();

        for (int i = 0; i < mPublisher; i++) {
            Thread publisher1 = new Thread(() -> threadSignaling.publisher());
            publisher1.start();
        }

        for (int i = 0; i < nSubscriber; i++) {
            Thread subscriber1 = new Thread(() -> threadSignaling.subscriber());
            subscriber1.start();
        }

    }

    public void subscriber() {

        synchronized (this) {
            try {
                while (list.isEmpty()) {
                    this.wait();
                }
                System.out.println(Thread.currentThread().getName() + " " + nextInitial + ". Message subscribed..." + list.get(list.size() - 1));
                list.remove(list.size() - 1);
                this.notifyAll();
            } catch (InterruptedException e) {
            }
        }
    }

    public void publisher() {

        synchronized (this) {
            try {
                while (!list.isEmpty()) {
                    this.wait();
                }
                list.add(nextInitial);
                System.out.println(Thread.currentThread().getName() + " " + nextInitial + ". Message published..." + list.get(list.size() - 1));
                nextInitial++;
                this.notifyAll();
            } catch (InterruptedException e) {
            }
        }
    }
}
