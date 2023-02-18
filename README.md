# gradle fundamentals  -  concurrency 

This project is about async programming concepts such as 
- multithreading, 
- parallel programming,
- streams,
- concurrency, and
- reactive programming


## Thread concepts  -  
[frame problem  - > create  - > start  - > join]

  - pooling
  - accessing same field :: locking (synchronized), Atomic Ops.
  - Inter-communication  :: accessing respectively (producer-consumer) :: (wait & notify)
  - flow (join, sleep)
  - strategy             :: how to define the problem for multi-threading
  - computations vs IO


<details>
<summary>Runnable         :: 1995      - > thread = task</summary>
 
 
 new Thread(Class::task).start();

</details>

<details>
<summary>
<T> Callable <T> :: 2004      - > pooling for better thread management 
</summary>
 
  -  better @ thread creation via pooling than new Thread().....
  -  We can use executors for Callable & also, for Runnable interfaces!
  -  Callable returns something!
  -  Runnable is void!

  executorService = Executors.newFixedThreadPool(n)
  executorService.submit(Class::task).get()    - > returns Future
 
  .submit()
  .execute()
  .invokeAll()
 
  executorService.shutdown()
 
</details>

<details>
<summary>
Fork/Join        :: 2011      - > dividing into smart subtasks & merging for parallel programming
</summary>
 
 pool = new ForkJoinPool()      //a kind of Executors! ... ForkJoinPool.commonPool()
 pool.execute(task)       void (async), fire & forget
 pool.invoke(task)        waits, then returns result immediately (sync),
 pool.submit(task).get()  waits, then returns Future (async)

 subtask = ...
 subTask.fork()
 subTask.join() or subTask.invoke() or invokeAll()

```
              Future<T>
                 |
            ForkJoinTask<T>
        |                   |
 RecursiveAction        RecursiveTask<T>
 (void)

```

</details>

<details>
<summary>
parallelStreams  :: 2011      - > parallel programming, processing in-memory data, mostly non-blocking,
</summary>

    -  uses ForkJoinPool.commonPool() behind the scenes!
 
 
</details>

<details>
<summary
 CompletableFuture :: 2014      - > async computations & trigger dependant computations
</summary>

   -  better @ functional programming than ForkJoinPool & parallelStreams
   -  better @ basic exceptional cases than ForkJoinPool
   -  uses ForkJoinPool.commonPool() behind the scenes!
  <p>

```
  where t1,t2.. are dependent tasks of T
  
  T ... T ...  T ... T            :::independent Tasks
  :
 t1    t1
  :
 t2    t2
  :
 t2
 
```
 
  CompletableFuture.supplyAsync(::getT1)
            .thenApply(::getT2)
            .exceptionally(e  - > new handleTException(e))
            .
  if you use your custom pool use thenApplyAsync  - > .thenApplySync(::getTx, ioPool)
  for more complex exception handling use  - > .thenCombine(........)

</details> 

<details>
<summary>
reactiveStreams   :: 2015      ->
</summary>

   -  better @ complex exception handling than CompletableFuture
 
 
</details>

<details>
<summary>
 Project loom :: 2022           ->  lightweight thread == task, handling numerous blocking requests/responses
</summary>

  -  jdk19 preview
  -  lightweight thread == task,  no way to cut this bound!!
  -  creating 1m thread {now, it costs 2tb ram, 20min startup time & context switching}
  -  CompletionState/CompletableFuture
  -  async  - > reactive programming
  -  Mono/Multi (spring framework)
 Problem is callback hell, hard to test & profile & debug where Loom is to rescue :)

 So, threads will be two types :: platform or virtual

</details>

## how to run
Project scope and java command scopes have different things! So Some key points to consider are:

- ` ./gradlew clean jar jmhJar`
- `java  - cp core/build/libs/core-1.0-SNAPSHOT.jar org.core.Main..... ` to run Mainxxx classes.
- `java  - cp core/build/libs/core-1.0-SNAPSHOT-jmh.jar org.core.TasksThreadSafeBenchmark` to run JMH benchmark tests. Or you can 
run via IDE (install jetbrains-jmh plugin).
 