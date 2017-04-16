package com.java.example;

public class Demo extends Thread{
    @Override
    public void run() {
        for (int i=1; i<=20; i++)
        {
            System.out.println(i);
        }
        System.out.println("Thread Start");
    }

    public static void main(String[] args) {
        Demo demo = new Demo();
        demo.start();
    }
}
