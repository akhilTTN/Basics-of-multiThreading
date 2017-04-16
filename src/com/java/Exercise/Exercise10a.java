package com.java.Exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exercise10a {
    private static int counter = 0;

    public static void increment() {
        for (int i = 0; i < 1000; i++) {
            synchronized (Exercise10a.class) {
                counter++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();

        ExecutorService executor = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 5; i++) {
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName());
                increment();
            });
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
        }

        System.out.println("Finished all threads");
        System.out.println("Counter: " + counter);

        long endTime = System.currentTimeMillis();
        System.out.println("Total time taken(in millis): " + (endTime - startTime));
    }


}
