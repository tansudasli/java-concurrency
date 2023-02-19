package org.core;

import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.IntStream;


/**
 * simulates operations/task on employee data
 */
public interface ITasks {

    /**
     * When we make below line shared by all threads, it gets crazy :)
     * <p>to ensure thread-safety:
     *
     * <li>1- sync             :: lock & synchronized
     * <li>2- use thread-safe version
     * <li>3- 1 obj = 1 thread :: ThreadLocal<>
     * <li>4- 1 obj = 1 task   :: too much memory, expensive, probably obj creation inside the task
     */
     static String getLog() {
        String log;

        synchronized (ITasks.class) {
            log = ILast.log ? Thread.currentThread().getName() + ": " : "";
        }

        return log;
     }

//     This will cause not thread-safe operation. every thread will access same shared value
//     String log2 = ILast.log ? Thread.currentThread().getName() + ": " : "";

     Supplier<String> log  = () -> ILast.log ? Thread.currentThread().getName() + ": " : "";

     ThreadLocal<String> logContextHolder = ThreadLocal.withInitial(() -> ILast.log ? Thread.currentThread().getName() + ": " : "");

     static void taskStream(Optional<Integer> last) {
        System.out.println(Thread.currentThread() + "..begin");

        String log = ILast.log ? Thread.currentThread().getName() + ": "  : "";

        System.out.println(
                "t:" + IntStream.range(0, last.orElse(10))
                        .boxed()
                        .peek(i -> System.out.println(log + i))
                        .toList()
        );

        System.out.println(Thread.currentThread() + "..end");
     }
     static void taskParallelStream(Optional<Integer> last) {
        System.out.println(Thread.currentThread() + "..begin");

        String log = ILast.log ? Thread.currentThread().getName() + ": "  : "";

        IntStream.range(0, last.orElse(10))
                .boxed()
                .parallel()
//                        .peek(i -> log + i))
                .forEach(i -> System.out.println(log + i));

        System.out.println(Thread.currentThread() + "..end");
     }

     Consumer<Integer> forThreadLocal = (l) -> IntStream.range(0, l)
                                                        .forEach((i) -> System.out.println(logContextHolder.get() + i));
     Consumer<Integer> forSynchronized = (l) -> {
         for (int i = 0; i < l; i++)
            synchronized (ITasks.class) { System.out.println(log.get() + i); }
     };

    Consumer<Integer> forLock = (l) -> {
        String log = ILast.log ? Thread.currentThread().getName() + ": "  : "";

//        Lock lock = new ReentrantLock();
        ReentrantReadWriteLock.ReadLock readLock = new ReentrantReadWriteLock().readLock();

//        lock.lock();
        readLock.lock();
        try {
            for (int i = 0; i < l; i++)
                System.out.println(log + i);
        } finally {
//            lock.unlock();
            readLock.unlock();
        }
    };

     Consumer<Integer> forTaskLocal = (l) -> {
        String log = ILast.log ? Thread.currentThread().getName() + ": "  : "";

        for (int i = 0; i < l; i++)
            System.out.println(log + i);
     };



     static void taskTraditionalFor(Optional<Integer> last) {
        System.out.println(Thread.currentThread() + "...begin");

//        1 obj = 1 task
//        String log = ILast.log ? Thread.currentThread().getName() + ": "  : "";
//        for (int i = 0; i < last.orElse(10); i++)
//            System.out.println(log + i);

//         sync
//        for (int i = 0; i < last.orElse(10); i++)
//            synchronized (ITasks.class) { System.out.println(log.get() + i); }

//         ThreadLocal<T>, 1 obj = 1 thread
//         for (int i = 0; i < last.orElse(10); i++)
//             System.out.println(threadLocalLog.get() + i);
//        forThreadLocal.accept(last.orElse(10));

//         lock
        forLock.accept(last.orElse(10));

        System.out.println(Thread.currentThread() + "...end");
     }
}
