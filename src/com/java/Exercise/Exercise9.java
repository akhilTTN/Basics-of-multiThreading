package com.java.Exercise;

/*A program consists of n thread printing 1 to 10.
Below is the sample output of executing 4 concurrent threads.
Thread 1: 1
Thread 1: 5
Thread 1: 9
Thread 3: 3
Thread 3: 7
Thread 2: 2
Thread 4: 4
Thread 2: 6
Thread 4: 8
Thread 2: 10
Identify the pattern and create a program to get the output for N concurrent threads.
*/

public class Exercise9 {
    static int threadCount = 5;

    public static void main(String[] args) {

        for (int i = 1; i <= threadCount; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    int thread = (int) Thread.currentThread().getId() - 10 + 1;
                    print(thread);
                }
            });
            thread.start();
        }
    }

    public static void print(int thread) {
        int temp = thread;
        int i = 1;
        while (temp <= 10) {
            System.out.println("Thread-" + thread + " : " + temp);
            temp = thread + threadCount * i++;
        }
    }
}
