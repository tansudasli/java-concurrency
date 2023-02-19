package org.core;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainCompletable {

    /**
     * CompletableFuture  ::: async computation -> trigger dependent computation
     *
     * threadCount == new Task count !
     * <p>
     *     wholeTask = T
     * First                                          ILast<p>
     * 0.........base.................................10000
     * ...........T1 .............T2
     *
     * Frame as completable
     *
     * One way:
     * T      T
     * t1     t1
     * t2     t2
     *
     * or Second Way:
     * T/2    T/2
     * t1     t1
     * t2     t2
     * <p>
     *
     */


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        int threadCount = 2;
        ExecutorService pool = Executors.newFixedThreadPool(threadCount);

        //single thread, loop then sum w/ streams
        System.out.println("expected: " + ITaskCompletableSum.sum.apply(ILast.LAST));


        CompletableFuture<Integer> e = CompletableFuture.supplyAsync(ITaskCompletableSum.sum1)
                                                        .thenApply((result) -> result + ITaskCompletableSum.sum2.get());
        System.out.println("calculated: " + e.get());

        pool.shutdown();


    }
}
