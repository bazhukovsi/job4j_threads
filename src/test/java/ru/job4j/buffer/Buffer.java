package ru.job4j.buffer;

public class Buffer {
    private StringBuilder buffer = new StringBuilder();

    public void add(int value) {
        synchronized (this) {
            System.out.print(value);
            buffer.append(value);
        }
    }

    @Override
    public synchronized String toString() {
        return buffer.toString();
    }
}
