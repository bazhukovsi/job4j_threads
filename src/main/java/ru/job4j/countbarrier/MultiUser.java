package ru.job4j.countbarrier;

public class MultiUser {
    public static void main(String[] args) {
        CountBarrier barrier = new CountBarrier(3);
        Thread master = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    barrier.count();
                },
                "MasterOne"
        );
        Thread master1 = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    barrier.count();
                },
                "MasterTwo"
        );
        Thread master2 = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    barrier.count();
                },
                "MasterThree"
        );
        Thread slave = new Thread(
                () -> {
                    barrier.await();
                    System.out.println(Thread.currentThread().getName() + " started");
                },
                "Slave"
        );
        master.start();
        master1.start();
        master2.start();
        slave.start();
    }
}
