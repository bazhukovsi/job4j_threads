package ru.job4j.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class Wget {
    public static void main(String[] args) {
        AtomicInteger index = new AtomicInteger(1);
        Thread thread = new Thread(
                () -> {
                    try {
                        Thread.sleep(1000);
                        for (int i = 2; i <= 100; i++) {
                            System.out.print("\rLoading : " + index.incrementAndGet() + "%");
                            Thread.sleep(1000);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        thread.start();
        System.out.print("\rLoading : " + index  + "%");
    }
}
