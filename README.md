# gradle fundamentals - concurrency 

This project is about async programming concepts such as 
- multithreading, 
- parallel programming,
- streams,
- concurrency, and
- reactive programming


## more on gradle
Project scope and java command scopes have different things! So Some key points to consider are:

- ` ./gradlew clean jar jmhJar`
- `java -cp core/build/libs/core-1.0-SNAPSHOT.jar org.core.Main..... ` to run Mainxxx classes.
- ` java -cp core/build/libs/core-1.0-SNAPSHOT-jmh.jar org.core.TasksThreadSafeBenchmark` to run JMH benchmark tests. Or you can 
run via IDE (install jetbrain-jmh plugin).
 