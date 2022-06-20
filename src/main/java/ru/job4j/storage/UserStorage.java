package ru.job4j.storage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private final Map<Integer, User> users = new HashMap<>();

    public synchronized boolean add(User user) {
        return users.putIfAbsent(user.getId(), user) == null;
    }

    public synchronized void delete(User user) {
        users.remove(user.getId(), user);
    }

    public synchronized boolean update(User user) {
        return users.replace(user.getId(), user) != null;
    }

    public synchronized void transfer(int fromId, int toId, int amount) {
        User sender = users.get(fromId);
        User receiver = users.get(toId);
        if (sender != null && receiver != null && sender.getAmount() > amount) {
            delete(sender);
            delete(receiver);
            add(new User(fromId, sender.getAmount() - amount));
            add(new User(toId, receiver.getAmount() + amount));
        } else {
            System.out.println("Недостаточно средств для перевода.");
        }
    }

    public synchronized Map<Integer, User> getUsers() {
        return users;
    }
}
