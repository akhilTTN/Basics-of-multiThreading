package com.java.example;

/*
public class MyCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        return Thread.currentThread().getName();
    }
    public static void main(String args[]){
        System.out.println("Get ExecutorService from Executors utility class, thread pool size is 10");
        ExecutorService executor = Executors.newSingleThreadExecutor();
        System.out.println("Create MyCallable instance");
        Callable<String> callable = new MyCallable();
        System.out.println("submit Callable tasks to be executed");
        Future<String> future = executor.submit(callable);
        try {
            System.out.println("print the return value of Future");
            // notice the output delay in console
            // because Future.get() waits for task to get completed
            System.out.println(new Date()+ "::"+future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("shut down the executor service now");
        executor.shutdown();
        //No threads can be execute further";
    }
}*/

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class MyCallable implements Callable<String> {
    public static void main(String args[]) {
        //Get ExecutorService from Executors utility class, thread pool size is 10
        ExecutorService executor = Executors.newFixedThreadPool(2);
        //create a list to hold the Future object associated with Callable
        List<Future<String>> list = new ArrayList<Future<String>>();
        //Create MyCallable instance
        Callable<String> callable = new MyCallable();
        for (int i = 0; i < 5; i++) {
            //submit Callable tasks to be executed by thread pool
            Future<String> future = executor.submit(callable);
            //add Future to the list, we can get return value using Future
            list.add(future);
        }
        for (Future<String> fut : list) {
            try {
                //print the return value of Future, notice the output delay in console
                // because Future.get() waits for task to get completed
                System.out.println(new Date() + "::" + fut.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        //shut down the executor service now
        executor.shutdown();
        // No threads can be execute further
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        //return the thread name executing this callable task
        return Thread.currentThread().getName();
    }
}
