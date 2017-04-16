package com.java.example;

public class UserProfile {
    public static void main(String[] args) {
        // Printing 0,2,4,6,8,10
        Thread t = new Thread(() -> printEven());
        t.start();
        // Printing 1,3,5,7,9
        Thread t1 = new Thread(() -> printOdd());
        t1.start();
    }
    public static void printEven(){
        for(int i = 0; i <= 10; i= i+2){
            // sleeping for 500 millis
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Even : "+i);
        }
    }
    public static void printOdd(){
        for(int i = 1; i <= 9; i= i+2){
            System.out.println("Odd : "+i);
            // sleeping for 500 millis
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
