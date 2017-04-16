package com.java.example;

public class Demo4 extends Thread {

    static int threadCount = 4;

    public static void main(String[] args) {
        Demo4 demo1 = new Demo4();
        Demo4 demo2 = new Demo4();
        Demo4 demo3 = new Demo4();
        Demo4 demo4 = new Demo4();
        demo1.setName("1");
        demo2.setName("2");
        demo3.setName("3");
        demo3.setName("4");
        demo1.start();
        demo2.start();
        demo3.start();
        demo4.start();


    }

    public static void print(int thread) {
        int temp = thread;
        int i = 1;
        while (temp <= 10) {
            System.out.println("Thread-" + thread + " : " + temp);
            temp = thread + threadCount * i++;
        }
    }

    @Override
    public void run() {
        int thread = Integer.parseInt(Thread.currentThread().getName());
        print(thread);
    }
}
