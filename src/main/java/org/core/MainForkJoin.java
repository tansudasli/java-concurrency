package org.core;

import java.util.Optional;
import java.util.concurrent.ForkJoinPool;

public class MainForkJoin {

    public static ForkJoinPool pool = new ForkJoinPool(ITaskForkShout.threadCount.get());

    public static void main(String[] args) {

//        var x = pool.invoke(new ITaskForkSum(10, 0));
        var x = pool.invoke(new ITaskForkSum(20, 10, 5));

        System.out.println(x);
    }
}
