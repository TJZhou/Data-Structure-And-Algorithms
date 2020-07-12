package com.tianju.threads;

/**
 * Implement Producer and Consumer model
 * Tianju Zhou July 10 2020
 */
public class ProducerConsumer {

    static class Node {
        int counter = 0;
        boolean occupied = false;

        public synchronized int put() {
            while(occupied)
                try { wait(); } catch (Exception ignored){}
            try { Thread.sleep(1000); } catch (Exception ignored) { }
            occupied = true;
            notify();
            return ++counter;
        }

        public synchronized int get() {
            while (!occupied)
                try { wait(); } catch (Exception ignored) { }
            occupied = false;
            notify();
            return counter;
        }
    }

    static class Producer {
        public Producer(Node n) {
            new Thread(() -> {
                while (true)
                    System.out.println("Producer:" + n.put());
            }).start();
        }
    }

    static class Consumer {
        public Consumer(Node n) {
            new Thread(() -> {
                while(true)
                    System.out.println("Consumer:" + n.get());
            }).start();
        }
    }

    public static void main(String[] args) {
        Node n = new Node();
        new Producer(n);
        new Consumer(n);
    }
}
