package org.core;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.function.BiConsumer;


public class MainFork implements ILast {

    //invoke waits the result
//    public static ForkJoinPool pool = ForkJoinPool.commonPool();
    public static ForkJoinPool pool = new ForkJoinPool(ITaskForkShout.threadCount.get());
    public static BiConsumer<Integer, Integer> spark = (last, first) -> pool
                                                        .invoke(new ITaskForkShout(last, first));

    public static void main(String[] args) throws ExecutionException, InterruptedException {

/*
        ForkJoinPool.commonPool()
                .submit(ITasks::taskTraditionalFor)
                .get();
        TaskTraditionalFor taskTraditionalFor = new TaskTraditionalFor();
        new ForkJoinPool().execute(new TaskTraditionalFor());          //execute = void
        new ForkJoinPool().execute(new TaskTraditionalFor(5, 10, 0));
        ForkJoinPool.commonPool().execute(new TaskTraditionalFor(500, 1000, 0));
        ForkJoinPool.commonPool().invoke(new TaskTraditionalFor());    //commonPool
*/

/*
           T's are parallel and independent
           t's are dependent of T

           so that's parallel & concurrent !

           T                   T            T
        0.....10           10....20      30....40
          0..5  t1            t1            t1
          5..10 t2            t2            t2

*/

        spark.accept(10, 0);  //T
        spark.accept(20, 10); //T
        spark.accept(40, 30); //T


/*
        do {
            System.out.println("Main parallelism: " + pool.getParallelism());

            try { TimeUnit.MILLISECONDS.sleep(5); } catch (InterruptedException e) { throw new RuntimeException(e);}

        } while (!pool.isShutdown());
        pool.shutdown();
        Thread.sleep(5000);
*/

    }



}
