package ru.job4j.storage;

public class Test {
    public static void main(String[] args) {
        UserStorage storage = new UserStorage();
        storage.add(new User(1, 100));
        storage.add(new User(2, 300));
        System.out.println(storage.getUsers());
        System.out.println("--------------Transfer----------------");
        storage.transfer(1, 2, 50);
        System.out.println(storage.getUsers());
        System.out.println("--------------Insufficient funds----------------");
        storage.transfer(1, 2, 100);
        System.out.println(storage.getUsers());
    }
}
