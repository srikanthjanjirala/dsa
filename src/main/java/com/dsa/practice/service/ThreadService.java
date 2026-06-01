package com.dsa.practice.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ThreadService {

    private final Object lock = new Object();
    private boolean dataReady = false;
    private AtomicInteger availableTickets = new AtomicInteger(10);

    public  void blockedExample(){
        Thread t1 = new Thread(() -> blocked(), "Thread-1");
        Thread t2 = new Thread(() -> blocked(), "Thread-1");
        t1.start();
        t2.start();
    }

    public synchronized void  blocked(){
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(10000);
        } catch (Exception e) {}
    }


    // PRODUCER — adds data wait() and notify() example
    public void produce() throws InterruptedException {
        synchronized (lock) {
            System.out.println("Producing data...");
            Thread.sleep(2000);         // simulate work
            dataReady = true;
            lock.notify();              // wake up waiting thread
            System.out.println("Data ready! Consumer notified.");
        }
    }

    // CONSUMER — waits for data
    public void consume() throws InterruptedException {
        synchronized (lock) {
            while (!dataReady) {
                System.out.println("No data yet... waiting");
                lock.wait();            // thread sleeps here
            }
            System.out.println("Data received! Processing...");
        }
    }


    // AtomicInteger
    public String bookTicket(String userName) {
        int current;
        do {
            current = availableTickets.get();
            if (current <= 0) {
                return userName + " -> FAILED: No tickets left!";
            }
        } while (!availableTickets.compareAndSet(current, current - 1));

        return userName + " -> SUCCESS: Ticket booked! Remaining tickets: " + availableTickets.get();
    }

    public int getAvailableTickets() {
        return availableTickets.get();
    }

    public void resetTickets(int count) {
        availableTickets.set(count);
    }
}