package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Wget implements Runnable {
    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("min.jpg")) {
            long size = new URL(url).openConnection().getContentLength();
            System.out.println(size);
            byte[] dataBuffer = new byte[8192];
            int bytesRead;
            long sec = 8192 / speed;
            int currentSize = 0;
            long start = System.currentTimeMillis();
            System.out.print("\rDownloaded " + currentSize + " from " + size);
            while ((bytesRead = in.read(dataBuffer, 0, 8192)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                long finish = System.currentTimeMillis();
                if ((finish - start) * 1000 < sec) {
                    try {
                        Thread.sleep(sec * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                currentSize += bytesRead;
                System.out.print("\rDownloaded " + currentSize + " from " + size);
                start = System.currentTimeMillis();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }
}
