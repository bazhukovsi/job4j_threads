package ru.job4j.cashe;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        BiFunction<Integer, Base, Base> biFunction = (key, base) -> {
            if (model.getVersion() != base.getVersion()) {
                throw new OptimisticException("Versions are not equal");
            }
            String name = model.getName();
            Base newBase = new Base(key, base.getVersion() + 1);
            newBase.setName(name);
            return newBase;
        };
        return memory.computeIfPresent(model.getId(), biFunction) != null;
    }

    public void delete(Base model) {
        memory.remove(model.getId(), model);
    }

    public Map<Integer, Base> getMemory() {
        return new HashMap<>(memory);
    }

    public static void main(String[] args) {
        Cache test = new Cache();
        Base base1 = new Base(1, 1);
        base1.setName("Base1");
        Base base2 = new Base(2, 1);
        base2.setName("Base2");
        Base base3 = new Base(3, 1);
        base3.setName("Base3");
        test.memory.put(1, base1);
        test.memory.put(2, base2);
        test.memory.put(3, base3);
        for (Map.Entry<Integer, Base> entry : test.memory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        System.out.println("-------------------------");
        test.update(test.memory.get(1));
        for (Map.Entry<Integer, Base> entry : test.memory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        System.out.println("-------------------------");
        test.delete(test.memory.get(2));
        for (Map.Entry<Integer, Base> entry : test.memory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
