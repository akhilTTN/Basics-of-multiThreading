package com.java.Exercise;

/*Print pattern 1 2 3 1 2 3.... using three different threads*/

public class Exercise3 {
    private static int var = 1;

    public static void main(String[] args) {
        Exercise3 threadSignaling1 = new Exercise3();
        int num = 3;

        new Thread(() -> threadSignaling1.print1(num)).start();
        new Thread(() -> threadSignaling1.print2(num)).start();
        new Thread(() -> threadSignaling1.print3(num)).start();

    }

    public void print1(int num) {
        for (int i = 1; i <= num; i++) {
            synchronized (this) {
                try {
                    while (var % 3 != 1) { // always use loop, not if statement
                        this.wait();   // waits for other thread to release lock
                    }
                    System.out.println(Thread.currentThread().getName() + " " + var % 3);
                    var++;
                    this.notifyAll(); // notifying other threads to acquire lock
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public void print2(int num) {
        for (int i = 1; i <= num; i++) {
            synchronized (this) {
                try {
                    while (var % 3 != 2) { // always use loop, not if statement
                        this.wait();   // waits for other thread to release lock
                    }

                    System.out.println(Thread.currentThread().getName() + " " + var % 3);
                    var++;
                    this.notifyAll(); // notifying other threads to acquire lock
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public void print3(int num) {
        for (int i = 1; i <= num; i++) {
            synchronized (this) {
                try {
                    while (var % 3 != 0) { // always use loop, not if statement
                        this.wait();   // waits for other thread to release lock
                    }

                    System.out.println(Thread.currentThread().getName() + " " + 3);
                    var++;
                    this.notifyAll(); // notifying other threads to acquire lock
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
