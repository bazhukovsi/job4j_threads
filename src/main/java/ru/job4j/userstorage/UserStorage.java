package ru.job4j.userstorage;

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

    public synchronized boolean delete(User user) {
        return users.remove(user.getId(), user);
    }

    public synchronized boolean update(User user) {
        return users.replace(user.getId(), user) != null;
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean retVal = false;
        User sender = users.get(fromId);
        User receiver = users.get(toId);
        if (sender != null && receiver != null && sender.getAmount() > amount) {
            sender.setAmount(sender.getAmount() - amount);
            receiver.setAmount(receiver.getAmount() + amount);
            retVal = true;
        }
        return retVal;
    }
}

