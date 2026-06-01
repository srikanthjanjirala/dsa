package com.dsa.practice.controller;

import com.dsa.practice.service.ThreadService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RequestMapping("/api/threads")
@RestController
@AllArgsConstructor
public class ThreadsController {

    private final ThreadService threadService;

    @GetMapping("/thread-info")
    public void ThreadTest(){
        System.out.println("Thread "+Thread.currentThread().getName());
    }

    @GetMapping("/thread-blocked")
    public void ThreadBlocked(){
        threadService.blockedExample();
    }

    // wait() and notify() example
    @GetMapping("/produce")
    public String produce() throws InterruptedException {
        threadService.produce();
        return "Produced!";
    }

    @GetMapping("/consume")
    public String consume() throws InterruptedException {
        threadService.consume();
        return "Consumed!";
    }

    @GetMapping("/thread-priority")
    public void threadPriority() throws InterruptedException {
        Thread t1 = new Thread(() -> System.out.println("Low priority task"));
        Thread t2 = new Thread(() -> System.out.println("High priority task"));

        t1.setPriority(1);  // priority 1
        t2.setPriority(2);  // priority 10
//      t1.setPriority(Thread.MIN_PRIORITY)  = 1  // lowest
//        Thread.NORM_PRIORITY = 5  // default
//        Thread.MAX_PRIORITY  = 10 // highest


        t1.start();
        t2.start();
    }

    // AtomicInteger
    @PostMapping("/book/{userName}")
    public String bookTicket(@PathVariable String userName) {
        return threadService.bookTicket(userName);
    }

    // Simulate 15 users trying to book at the SAME TIME
    @PostMapping("/simulate")
    public List<String> simulate() throws InterruptedException {

        threadService.resetTickets(10); // Reset to 10 tickets
        int totalUsers = 15;

        ExecutorService executor = Executors.newFixedThreadPool(15);
        List<Future<String>> futures = new ArrayList<>();
        List<String> results = new ArrayList<>();

        // 15 threads fire simultaneously
        for (int i = 1; i <= totalUsers; i++) {
            final String user = "User-" + i;
            futures.add(executor.submit(() -> threadService.bookTicket(user)));
        }

        // Collect all results
        for (Future<String> future : futures) {
            try {
                results.add(future.get());
            } catch (ExecutionException e) {
                results.add("Error: " + e.getMessage());
            }
        }

        executor.shutdown();
        results.add("--- Final available tickets: " + threadService.getAvailableTickets() + " ---");
        return results;
    }

    // Check remaining tickets
    @GetMapping("/available")
    public String available() {
        return "Available tickets: " + threadService.getAvailableTickets();
    }
}
