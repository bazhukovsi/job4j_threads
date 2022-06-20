package ru.job4j.storage;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserStorageTest {
    @Test
    public void testTrueTransfer() {
        UserStorage storage = new UserStorage();
        storage.add(new User(1, 100));
        storage.add(new User(2, 300));
        assertTrue(storage.transfer(1, 2, 70));
    }

    @Test
    public void testFalseTransfer() {
        UserStorage storage = new UserStorage();
        storage.add(new User(1, 100));
        storage.add(new User(2, 300));
        assertFalse(storage.transfer(1, 2, 120));
    }
}