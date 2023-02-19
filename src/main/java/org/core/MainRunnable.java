package org.core;

import java.util.Optional;

public class MainRunnable {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread() + ".....");

        //no thread pool, just old-fashioned another thread
        new Thread(() -> ITasks.taskTraditionalFor(Optional.of(ILast.LAST))).start();
        new Thread(() -> ITasks.taskTraditionalFor(Optional.of(ILast.LAST))).start();
        new Thread(() -> ITasks.taskTraditionalFor(Optional.of(ILast.LAST))).start();
//        new Thread(() -> ITasks.taskStream(Optional.of(LAST)), "task").start();
//        ITasks.taskParallelStream(Optional.of(LAST));

        System.out.println(Thread.currentThread() + ".....");
    }
}
