package com.tianju.threads;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Fizz Buzz Problem in multi thread way
 * Tianju Zhou July 11 2020
 */
public class FizzBuzz {
    static class FizzBuzzThread extends Thread {
        static int num = 1;
        boolean mod3, mod5;
        Lock lock = new ReentrantLock();
        String threadName;

        public FizzBuzzThread(boolean mod3, boolean mod5, String threadName) {
            this.mod3 = mod3;
            this.mod5 = mod5;
            this.threadName = threadName;
            Thread.currentThread().setName(threadName);
        }

        @Override
        public void run() {
            while(true) {
                lock.lock();
                if((num % 3 == 0) == mod3 && (num % 5 == 0) == mod5) {
                    System.out.println(threadName + ":" +num);
                    num++;
                    try { Thread.sleep(500); }
                    catch (InterruptedException ignored) { }
                }
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[] {
                // mod 15
                new FizzBuzzThread(true, true, "FizzBuzz"),
                // mod 5
                new FizzBuzzThread(false, true, "Fizz"),
                // mod 3
                new FizzBuzzThread(true, false, "Buzz"),
                // mod 1
                new FizzBuzzThread(false, false, "Number"),
        };
        int a  = 0b10000000000000000000000000000000;
        for(Thread r : threads)
            r.start();
    }
}
