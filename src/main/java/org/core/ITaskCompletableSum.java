package org.core;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 * 0 to LAST integers will be summed.
 *
 * And problem is framed as dependent (sum1 & sum2) tasks. Normally these tasks are independent.
 * So, More suitable for ForkJoin operation!
 */
public class ITaskCompletableSum {

    private static final int slice = 2;

    public static Function<Integer, Integer> sum  = (last) -> IntStream.range(0, last).sum();

    public static Supplier<Integer> sum1 =
            () -> IntStream.range(0, ILast.LAST/slice)
                           .peek(i -> System.out.println("sum1: " + ITasks.logContextHolder.get() + i ))
                           .sum();

    public static Supplier<Integer> sum2 =
            () -> IntStream.range(ILast.LAST/slice, ILast.LAST)
                    .peek(i -> System.out.println("sum1: " + ITasks.logContextHolder.get() + i ))
                    .sum();

}
