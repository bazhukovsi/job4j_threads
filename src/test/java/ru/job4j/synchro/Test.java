package ru.job4j.synchro;

public class Test {
    public static void main(String[] args) {
        Cache cache1 = Cache.instOf();
        Cache cache2 = Cache.instOf();
        System.out.println(cache1 == cache2);
    }
}
