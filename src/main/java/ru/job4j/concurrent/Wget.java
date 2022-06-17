package ru.job4j.concurrent;

public class Wget {
    public static void main(String[] args) {
        Thread thread = new Thread(
                () -> {
                    try {
                        Thread.sleep(1000);
                        for (int i = 2; i <= 100; i++) {
                            System.out.print("\rLoading : " + i + "%");
                            Thread.sleep(1000);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        thread.start();
        System.out.print("\rLoading : 1 %");
    }
}
