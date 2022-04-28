package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
        int i = 0;
        String[] process = new String[]{"|", "/", "\\"};
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(500);
                System.out.print("\r load: " + process[i++]);
                if (i == 3) {
                    i = 0;
                }
            }
        } catch (InterruptedException e) {
            System.out.print("");
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new ConsoleProgress());
        thread.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
