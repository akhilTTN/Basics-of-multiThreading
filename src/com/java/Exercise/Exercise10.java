package com.java.Exercise;

/*Write a program called SharedCounter.java in which 5 threads each increment a shared int counter 1000 times.
When all the threads have finished, print the final value of the counter.
If the nextInitial value is zero, do you always get 5000?
Arrange your code to get the correct output. Note: - use a thread pool of 2 threads.*/

public class Exercise10 {
    private int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        Exercise10 sharedCounter1 = new Exercise10();

        Thread thread1 = new Thread(() -> sharedCounter1.increment());
        thread1.start();
        Thread thread2 = new Thread(() -> sharedCounter1.increment());
        thread2.start();
        Thread thread3 = new Thread(() -> sharedCounter1.increment());
        thread3.start();
        Thread thread4 = new Thread(() -> sharedCounter1.increment());
        thread4.start();
        Thread thread5 = new Thread(() -> sharedCounter1.increment());
        thread5.start();

        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        thread5.join();

        long endTime = System.currentTimeMillis();
        System.out.println("Counter: " + sharedCounter1.counter);
        System.out.println("Total time taken(in millis): " + (endTime - startTime));
    }

    public void increment() {
        for (int i = 0; i < 1000; i++) {
            synchronized (Exercise10.class) {
//                System.out.println(Thread.currentThread().getName());
                counter++;
            }
        }
    }
}
