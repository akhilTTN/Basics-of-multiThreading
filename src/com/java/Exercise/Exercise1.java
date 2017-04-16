package com.java.Exercise;
/*Print Odd and even numbers from 1 to 20 in ascending order using two threads.*/
public class Exercise1 {
    private static boolean checkEven = true;

    public static void main(String[] args) {
        Exercise1 threadSignaling = new Exercise1();
        Thread t = new Thread(() -> threadSignaling.printEven());
        t.start();
        Thread t1 = new Thread(() -> threadSignaling.printOdd());
        t1.start();
    }

    public void printEven() {
        for(int i = 0; i <= 10; i= i+2){
            synchronized (this) {
                try {
                    while (!checkEven) { // always use loop, not if statement
                        this.wait();   // waits for other thread to release lock
                    }
                    checkEven = false;
                    System.out.println(Thread.currentThread().getName() + " Even : " + i);
                    this.notify(); // notifying other threads to acquire lock
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public void printOdd() {
        for(int i = 1; i <= 9; i= i+2){
            synchronized (this) {
                try {
                    while (checkEven) {
                        this.wait();   // waits for other thread to release lock
                    }
                    checkEven = true;
                    System.out.println(Thread.currentThread().getName() + " Odd : " + i);
                    this.notify(); // notifying other threads to acquire lock
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
