package com.java.Exercise;

/*Print Prime and non-prime numbers from 1 to 20 in ascending order using two threads*/
public class Exercise2 {
    private static boolean checkPrime = true;
    private static int i = 1;

    public static void main(String[] args) {
        Exercise2 threadSignaling = new Exercise2();
        Thread t = new Thread(() -> threadSignaling.printPrime());
        t.start();
        Thread t1 = new Thread(() -> threadSignaling.printNonPrime());
        t1.start();
    }

    public boolean checkNumberNonPrime(int n) {
        int i, m = 0;
        boolean flag = false;
        m = n / 2;
        for (i = 2; i <= m; i++) {
            if (n % i == 0) {
                flag = true;
                break;
            }
        }
        return flag;
    }


    public void printPrime() {
        for (; i <= 20; i++) {
            synchronized (this) {
                try {
                    while (!checkPrime) {
                        this.wait();
                    }
                    if (!checkNumberNonPrime(i))
                        System.out.println(Thread.currentThread().getName() + " Number is prime: " + i);
                    else
                        System.out.println(Thread.currentThread().getName() + " Number is not prime: " + i);
                    checkPrime = false;
                    this.notify();
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public void printNonPrime() {
        for (; i <= 20; i++) {
            synchronized (this) {
                try {
                    while (checkPrime) {
                        this.wait();
                    }
                    if (!checkNumberNonPrime(i))
                        System.out.println(Thread.currentThread().getName() + " Number is prime: " + i);
                    else
                        System.out.println(Thread.currentThread().getName() + " Number is not prime: " + i);
                    checkPrime = true;
                    this.notify();
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
