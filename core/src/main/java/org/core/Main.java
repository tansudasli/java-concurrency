package org.core;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Main {

    static IEmployeeGeneratorService employeesGeneratorFactoryService = () -> Arrays.asList("Sali", "Hali");

    static List<String> employees =  IEmployeeGeneratorService.create();

    /**
     * EmployeesGeneratorFactoryService has 3 same methods.
     * <li>off, as FunctionalInterface</li>
     * <li>of, as Supplier</li>
     * <li>create, as a traditional method</li>
     *
     * @param args ""
     */
    public static void main(String[] args) {

        //Todo: merge multithreaded concepts from java-fundamentals

        employeesGeneratorFactoryService.off().forEach(System.out::println);
        employees.stream().forEach(System.out::println);
        Stream.of(employees).forEach(System.out::println);

        //predicate boolOutput
        //consumer  void, input
        //supplier  output
        //function in, out
        //functional interface
        //Function generator = Arrays::asList;

    }
}