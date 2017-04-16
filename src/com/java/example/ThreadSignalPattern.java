package com.java.example;

public class ThreadSignalPattern {
    int number=3;
    boolean one = true;
    boolean two = false;
    boolean three = false;

    public void print1(int number){
        for (int i = 1; i < number+1; i++) {
            synchronized (this){
                try {
                    while (!one )
                        this.wait();
                    System.out.println("Thread1 - "+i);
                    one = false;
                    two = true;
                    three = false;
                    this.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void print2(int number){
        for (int i = 1; i < number+1; i++) {
            synchronized (this){
                try {
                    while (!two )
                        this.wait();
                    System.out.println("Thread2 - "+i);
                    one = false;
                    two = false;
                    three = true;
                    this.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void print3(int number){
        for (int i = 1; i < number+1; i++) {
            synchronized (this){
                try {
                    while (!three )
                        this.wait();
                    System.out.println("Thread3 - "+i);
                    one = true;
                    two = false;
                    three = false;
                    this.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ThreadSignalPattern test = new ThreadSignalPattern();
        new Thread( ()-> test.print1(test.number)).start();
        new Thread( ()-> test.print2(test.number)).start();
        new Thread( ()-> test.print3(test.number)).start();
    }
}
