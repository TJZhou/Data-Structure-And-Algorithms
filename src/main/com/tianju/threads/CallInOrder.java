package com.tianju.threads;

import java.util.concurrent.Semaphore;

/**
 * Use Semaphore to solve call in order problem
 * Tianju Zhou July 10 2020
 */
public class CallInOrder {

    public Semaphore s1, s2;

    public CallInOrder() {
        s1 = new Semaphore(1);
        s2 = new Semaphore(1);
        try {
            s1.acquire();
            s2.acquire();
        } catch (InterruptedException ignored) { }
    }

    public void first() {
        try {
            Thread.sleep(1000);
            System.out.println("First method called");
            s1.release();
        } catch (InterruptedException ignored) { }
    }

    public void second() {
        try {
            s1.acquire();
            s1.release();
            Thread.sleep(1000);
            System.out.println("Second method called");
            s2.release();
        } catch (InterruptedException ignored) { }
    }

    public void third() {
        try {
            s2.acquire();
            s2.release();
            Thread.sleep(1000);
            System.out.println("Third method called");
        } catch (InterruptedException ignored) { }
    }

    public static void main(String[] args) {
        CallInOrder cio = new CallInOrder();
        new Thread(cio::third).start();
        new Thread(cio::second).start();
        new Thread(cio::first).start();
    }
}
