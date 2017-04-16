package com.java.example;

public class Demo3 {
    private static boolean isInterrupted = false;
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> print(), "Child");
        t.start();
        Thread.sleep(2000);
        isInterrupted = true;
        t.interrupt();
        t.join();
        System.out.println(Thread.currentThread().getName()+" :: Execution complete..");
    }
    public static void print(){

        while(!Thread.currentThread().isInterrupted() && !isInterrupted){
            System.out.println(Thread.currentThread().getName()+" thread sleeping...");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" Active...");

        }
        System.out.println(Thread.currentThread().getName()+" :: Execution complete..");
    }
}
