package ru.job4j.cas;

import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicReference;

public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>(0);

    public void increment() {
        int ref;
        int temp;
        do {
            ref = count.get();
            temp = ref + 1;
        } while (!count.compareAndSet(ref, temp));
    }

    public int get() {
        return count.get();
    }

    public static void main(String[] args) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        final AtomicReference<Integer> count = new AtomicReference<>(0);
        CASCount casCount = new CASCount();
        Thread thread1 = new Thread(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        casCount.increment();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        stringJoiner.add(casCount.get() + "");
                        System.out.println(stringJoiner);
                    }
                }
        );
        Thread thread2 = new Thread(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        casCount.increment();
                        try {
                            Thread.sleep(150);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        stringJoiner.add(casCount.get() + "");
                        System.out.println(stringJoiner);
                    }
                }
        );
        thread1.start();
        thread2.start();
    }
}
