package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Predicate;

public class GetContent {
    public synchronized String get(ParseFile parseFile, Predicate<Character> filter) {
        StringBuilder sb = new StringBuilder();
        try (InputStream i = new FileInputStream(parseFile.getFile())) {
            int data;
            while ((data = i.read()) != -1) {
                if (filter.test((char) data)) {
                    sb.append((char) data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}
