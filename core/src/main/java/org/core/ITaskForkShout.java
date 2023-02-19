package org.core;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.function.Supplier;

class ITaskForkShout extends RecursiveAction {

    /*
     let's assume we will count to 1m. we will split into smart task.
    To do splitting, we need to define either counter mechanism or the thread count!
     Then, at compute() method, we will
     */
    int base, last, first, end;
    public final static Supplier<Integer> threadCount = () -> 2;

    public ITaskForkShout(Integer last, Integer first) {

        this.base = (last - first) / threadCount.get();
        this.last = last;
        this.first = first;
        this.end = first + base;
    }

    public ITaskForkShout(Integer base, Integer last, Integer first) {

        this.base = base;
        this.last = last;
        this.first = first;

        this.end = first + base;
    }

    private void base() {
        String log = ILast.log ? Thread.currentThread().getName() + ": "  : "";

        for (int i = first; i < end; i++)
            System.out.println(log + i);
    }

    /**
     * threadCount == new Task count !
     * <p>
     * First                                          ILast<p>
     * 0.........base.................................10000
     * <p>
     * <p>at main body, invoke/execute or submit big-chunky-Task
     * <p> - create a Task class that extends RecursiveTask or RecursiveAction (void)
     * <p> - define a Base method
     * <p> - divide into sub-tasks that has own Base method, then invoke/fork or join the sub-tasks again
     */

    @Override
    protected void compute() {

        //Todo: impl. THRESHOLD mechanism like in ITaskForSum
        if ((last - first) > base) {
            //whole-task sliced into 2 small-task

            System.out.println("Pending tasks: " + getQueuedTaskCount());

            ITaskForkShout t1 = new ITaskForkShout((last-first)/threadCount.get(), end, first);
            ITaskForkShout t2 = new ITaskForkShout((last-first)/threadCount.get(), last, end);

            ForkJoinTask.invokeAll(t1, t2);
        } else {
            base();
        }
    }
}
