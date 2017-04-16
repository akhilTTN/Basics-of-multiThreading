package com.java.Exercise;

/*Write a program called ReverseHello.java that creates a thread (let's call it Thread 1).
Thread 1 creates another thread (Thread 2); Thread 2 creates Thread 3; and
so on, up to Thread 10. Each thread should print "Hello from Thread <num>!",
but you should structure your program such that the threads print their greetings in reverse order*/

public class Exercise4 implements Runnable {
    static int threadCount = 1;

    public static void main(String[] args) {
        reverseHello();
    }

    public static void reverseHello() {
        if (threadCount <= 10) {
            Thread thread = new Thread(new Exercise4());
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        threadCount++;
        reverseHello();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Hello from " + Thread.currentThread().getName());
    }

}
