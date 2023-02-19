package org.core;

import java.util.Optional;

public class MainStream {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread() + ".....");

        //no thread pool, just old-fashioned another thread w/ stream usage
//        new Thread(() -> ITasks.taskTraditionalFor(Optional.of(LAST)), "main").start();
        new Thread(() -> ITasks.taskStream(Optional.of(ILast.LAST)), "task").start();
//        ITasks.taskParallelStream(Optional.of(LAST));

        System.out.println(Thread.currentThread() + ".....");
    }
}
