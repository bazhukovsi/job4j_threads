package ru.job4j.userstorage;

public class Test {
    public static void main(String[] args) {
        UserStorage storage = new UserStorage();
        storage.add(new User(1, 100));
        storage.add(new User(2, 300));
        storage.transfer(1, 2, 50);
    }
}
