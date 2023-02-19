package org.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

public class ITaskForkSum extends RecursiveTask<Integer> {

    private static final int slice = 2;
    private static int THRESHOLD;
    int last, first, end;

    public ITaskForkSum(int last, int first, Integer... splitPoint) {
        /*
        f                     end      last
        0 ..... 0+(10-0)/2   =  5 ..... 10
        10..... 10+(20-10)/2 = 15 ..... 20
         */
        this.last = last;
        this.first = first;

        this.end = first + (last-first)/slice;

        THRESHOLD = splitPoint.length > 0 ? splitPoint[0] : 5;
    }


    public Integer baseSum() {

       return IntStream.range(first, last)
                .peek(i -> System.out.println("baseSum: " + ITasks.logContextHolder.get() + i ))
                .sum();

    }

    @Override
    protected Integer compute() {
        //iml. base-sum func.
        //create 2 sub-tasks that will run base-sum
        //so, we need a logic base-sum vs creation of sub-tasks
        int length = last - first;

        if (length > THRESHOLD) {
            List<ITaskForkSum> tasks = new ArrayList<>();

            ITaskForkSum t1 = new ITaskForkSum(end, first);
            ITaskForkSum t2 = new ITaskForkSum(last, end);

            tasks.add(t1);
            tasks.add(t2);

            return ForkJoinTask.invokeAll(tasks).stream()
                    .mapToInt(ForkJoinTask::join)
                    .sum();
        }
        else
            return baseSum();
    }
}
