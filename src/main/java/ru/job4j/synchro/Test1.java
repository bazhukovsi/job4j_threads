package ru.job4j.synchro;

public class Test1 {
    public static void main(String[] args) {
        DCLSingleton dclSingleton1 = DCLSingleton.instOf();
        DCLSingleton dclSingleton2 = DCLSingleton.instOf();
        System.out.println(dclSingleton1 == dclSingleton2);
    }
}
