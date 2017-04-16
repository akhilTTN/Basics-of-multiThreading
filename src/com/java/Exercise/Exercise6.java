package com.java.Exercise;

/*Write a program called MaxValue.java that
finds the maximum value in an array of ints using 4 threads.*/

public class Exercise6 {
    static int a[] = {2, 10, 12, 1, 2, 3, 4, 23, 87, 5, 6, 7, 8, 9, 10};
    static int max = 0;
    static int i = 0;

    public static void main(String[] args) {

        Exercise6 exercise6 = new Exercise6();
        Thread thread1 = new Thread(() -> exercise6.arrayMax());
        Thread thread2 = new Thread(() -> exercise6.arrayMax());
        Thread thread3 = new Thread(() -> exercise6.arrayMax());
        Thread thread4 = new Thread(() -> exercise6.arrayMax());

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Max: " + max);
    }

    public void arrayMax() {

        while (i < a.length) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (this) {
                if (i < a.length) {
                    if (a[i] > max) {
                        max = a[i];
                    }
                    i++;
                    System.out.println(Thread.currentThread().getName() + " " + max);
                }
            }
        }
    }
}
