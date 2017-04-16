package com.java.example;

public class SharedCounter {
    private static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        SharedCounter sharedCounter1 = new SharedCounter();
        SharedCounter sharedCounter2 = new SharedCounter();

        Thread thread1 = new Thread(() -> sharedCounter1.increment());
        thread1.start();
        Thread thread2 = new Thread(() -> sharedCounter2.increment());
        thread2.start();

        thread1.join();
        thread2.join();

        long endTime = System.currentTimeMillis();
        System.out.println("Counter: " + counter);
        System.out.println("Total time taken(in millis): " + (endTime - startTime));
    }

    public void increment() {
        for (int i = 0; i < 1000; i++) {
            synchronized (SharedCounter.class) {
                counter++;
            }
        }
    }
}
