package ru.job4j.cas;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CASCountTest {

    @Test
    public void testCASCounter() throws InterruptedException {
        final CASCount counter = new CASCount();
        Runnable task = () -> {
            for (int i = 0; i < 10; i++) {
                counter.increment();
            }
        };
        Thread thread = new Thread(task);
        thread.start();
        thread.join();
        assertThat(counter.get()).isEqualTo(10);
    }
}