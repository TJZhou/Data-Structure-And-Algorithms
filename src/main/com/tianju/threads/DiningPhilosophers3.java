package com.tianju.threads;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Dining Philosopher Problem
 * Priority solution to solve Deadlock problem
 * Tianju Zhou July 10 2020
 */
public class DiningPhilosophers3 {

    static class Chopsticks {
        private Lock lock = new ReentrantLock();
        private Semaphore semaphore = new Semaphore(1);
        private int priority;

        public Chopsticks(int priority) {
            this.priority = priority;
        }

        public void pickUp() {
            lock.lock();
        }

        public void putDown() {
            lock.unlock();
        }
    }

    static class Philosopher implements Runnable {

        Chopsticks left, right;

        public Philosopher(Chopsticks left, Chopsticks right) {
            this.left = left;
            this.right = right;
        }

        private void pickup() {
            if (left.priority < right.priority)
                pickUp(left, right);
            else
                pickUp(right, left);
        }

        private void pickUp(Chopsticks firstPick, Chopsticks secondPick) {
            firstPick.pickUp();
            doAction("Pick Up " + firstPick.priority + " Chopsticks");
            secondPick.pickUp();
            doAction("Pick Up " + secondPick.priority + " Chopsticks");
        }

        private void putDown() {
            left.putDown();
            doAction("Put Down Left Chopsticks");
            right.putDown();
            doAction("Put Down Right Chopsticks");
        }

        @Override
        public void run() {
            while (true) {
                doAction("Think");
                pickup();
                doAction("Eating");
                putDown();
            }
        }

        public void doAction(String actionName) {
            System.out.println(Thread.currentThread().getName() + " " + actionName);
            try {
                Thread.sleep(((int) (Math.random() * 100)));
            } catch (InterruptedException ignored) {
            }
        }
    }

    public static void main(String[] args) {
        int philosopherNum = 3;
        Chopsticks[] chopsticks = new Chopsticks[philosopherNum];
        Philosopher[] philosophers = new Philosopher[philosopherNum];

        for (int i = 0; i < philosopherNum; i++)
            chopsticks[i] = new Chopsticks(i);
        for (int i = 0; i < philosopherNum; i++)
            philosophers[i] = new Philosopher(chopsticks[i], chopsticks[(i + 1) % philosopherNum]);
        for (int i = 0; i < philosopherNum; i++)
            new Thread(philosophers[i]).start();
    }
}
