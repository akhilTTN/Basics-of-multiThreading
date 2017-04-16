package com.java.Exercise;

/*Create a program that calculates the sum of all the elements of an Array by using 4 threads.*/

public class Exercise5 {
    static int a[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    static int sum = 0;
    static int i = 0;

    public static void main(String[] args) {

        Exercise5 exercise5 = new Exercise5();
        Thread thread1 = new Thread(() -> exercise5.arraySum());
        Thread thread2 = new Thread(() -> exercise5.arraySum());
        Thread thread3 = new Thread(() -> exercise5.arraySum());
        Thread thread4 = new Thread(() -> exercise5.arraySum());

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

        System.out.println("Sum: " + sum);
    }

    public void arraySum() {

        while (i < a.length) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (this) {
                if (i < a.length) {
                    sum = sum + a[i++];

                    System.out.println(Thread.currentThread().getName() + " " + sum);
                }
            }
        }
    }
}
