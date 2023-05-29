package ru.job4j.pool;

import ru.job4j.concurrent.ConsoleProgress;
import ru.job4j.consumer.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final int size = Runtime.getRuntime().availableProcessors();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(size);

    public ThreadPool() throws InterruptedException {
        System.out.println("Start constructor");
        for (int i = 0; i < size; i++) {
            threads.add(new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        tasks.poll().run();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }));
        }
        threads.forEach(Thread::start);
        Thread.sleep(1000);
        System.out.println("End constructor");
    }

    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    public void shutdown() throws InterruptedException {
        for (int i = 0; i < size; i++) {
            threads.get(i).interrupt();
            System.out.println(threads.get(i).getName() + " interrupted.");
            Thread.sleep(500);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPool threadPool = new ThreadPool();
        Thread thread = new Thread(
                () -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                            threadPool.work(new Job());
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        thread.start();
        Thread.sleep(6000);
        thread.interrupt();
        threadPool.shutdown();
    }
}
