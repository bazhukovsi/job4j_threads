package ru.job4j.consumer;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

class SimpleBlockingQueueTest {
    @Test
    public void whenConsumerAndProducer() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(8);
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    queue.offer(i);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 4; i++) {
                try {
                    queue.poll();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        producer.start();
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(queue.poll(), equalTo(4));
    }
}