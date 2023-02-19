package org.core;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

public class TasksSumBenchmark {
    private  static int LAST = 10;

    @Benchmark
    @BenchmarkMode({Mode.AverageTime})
    @Measurement(iterations = 1, time = 10, timeUnit = TimeUnit.MILLISECONDS)
    @Warmup(iterations = 1, time = 1, timeUnit = TimeUnit.MILLISECONDS)
    @Fork(0)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public static void testTraditionalSum(Blackhole bh) {
       bh.consume(ITaskCompletableSum.sum.apply(LAST));
    }

    //Todo: impl. multithreaded sum (completable sum) & (ForkJoin sum)

    public static void main(String[] args) throws RunnerException {

        /* jmh multi threading
         *   .threads(n) defines thread count. But JMH runs test sequentially.
         * So we can only multithreading One function at a time.
         *      So, every function must be divided into smart tasks !
         */
        System.out.println("..." + Thread.currentThread());

        var opt = new OptionsBuilder()
                .include(TasksSumBenchmark.class.getName())
                .jvmArgs("-Xms1g", "-Xmx1g", "-XX:+UseG1GC")
//                .threads(2)   //increases jmh-threads, if your test is already multithreaded, it may badly affect your perf.
                .warmupIterations(1)
                .measurementIterations(1)
                .forks(1)
                .build() ;

        new Runner(opt).run() ;

        System.out.println("..." + Thread.currentThread());

    }
}
