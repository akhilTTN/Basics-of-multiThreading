package com.java.example;

public class Demo1 implements Runnable {
    @Override
    public void run() {
        for (int i=1; i<=20; i++)
        {
            System.out.println(i);
        }
        System.out.println("Thread Start");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new Demo1());
        thread.start();
    }
}
