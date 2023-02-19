package org.core;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.OptionsBuilder;


import java.util.List;
import java.util.concurrent.TimeUnit;


@State(Scope.Benchmark)
public class EmployeeGeneratorServiceBenchmark {

    @Param({"1", "3"})
    private int size;

    private List<String> service, serviceOf;

    @Setup(Level.Trial)   //trial=warmup
    public void init() {

        service = IEmployeeGeneratorService.create();
        serviceOf = IEmployeeGeneratorService.of.get();
    }

    @Benchmark
    @BenchmarkMode({Mode.Throughput, Mode.AverageTime})
    @Fork(1)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void testCreate(Blackhole bh) {
        bh.consume(String.join(" ", service));
    }

    @Benchmark
    @BenchmarkMode({Mode.Throughput, Mode.AverageTime})
    @Fork(1)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void testOf(Blackhole bh) {
        bh.consume(String.join(" ", serviceOf));
    }

    //./gradlew clean build jmhJar
    // java -cp build/libs/java-fundamentals-1.0-SNAPSHOT-jmh.jar org.core.EmployeeGeneratorServiceBenchmark
    //   -rf json -rff result.json
    public static void main(String[] args) throws Exception {

        var opt = new OptionsBuilder()
                .include(EmployeeGeneratorServiceBenchmark.class.getName())
                .jvmArgs("-Xms2g", "-Xmx2g", "-XX:+UseG1GC")
                .warmupIterations(1)
                .measurementIterations(2)
                .forks(3)
                .resultFormat(ResultFormatType.JSON)
                .result("build/".concat(EmployeeGeneratorServiceBenchmark.class.getName()).concat(".json"))
                .build() ;

        new Runner(opt).run() ;
    }
}
