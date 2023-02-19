package org.core;

import java.util.*;
import java.util.function.Supplier;

/**
 * simulates sample employee data
 */
@FunctionalInterface
public interface IEmployeeGeneratorService {

    List<String> data = Arrays.asList("Ant", "Antelope", "Baboon", "Bat", "Beagle",
            "Eagle", "Fish", "Fly", "Fox", "Frog",
            "Bear", "Bird", "Butterfly", "Cat", "Caterpillar",
            "Honey Bee", "Horn Shark", "Horse", "Ibis", "Iguana",
            "Chicken", "Cow", "Dog", "Dolphin", "Donkey",
            "Frog", "Toad", "Gopher", "Gorilla", "Javanese",
            "Kakapo", "Kangaroo", "King Penguin", "Kiwi", "Koala",
            "Impala", "Jackal", "Jaguar", "Javanese", "Jellyfish",
            "Snake", "Swan", "Tuatara", "Turkey", "Zebra",
            "Mudskiper", "Tortoise", "Salamander", "Gharial",
            "Gerbil", "Goose", "Gopher", "Gorilla", "Heron",
            "Chicken", "Cow", "Dog", "Dolphin", "Donkey",
            "Mudskiper", "Tortoise", "Salamander", "Gharial",
            "Ant", "Antelope", "Dog", "Dolphin", "Chicken", "Cow",
            "Frog", "Toad", "Gopher", "Gorilla", "Javanese",
            "Lemming", "Lemur", "Leopard", "Saola", "Scorpion",
            "Frog", "Toad", "Crocodile", "Hippopotamus", "Crabs",
            "Frog", "Toad", "Gopher", "Gorilla", "Javanese",
            "Gerbil", "Goose", "Gopher", "Gorilla", "Heron",
            "Eagle", "Impala", "Dog", "Frog", "Donkey",
            "Frog", "Toad", "Crocodile", "Hippopotamus", "Crabs",
            "Impala", "Jackal", "Tortoise", "Salamander", "Gorilla", "Heron",
            "Frog", "Toad", "Gopher", "Gorilla", "Javanese",
            "Eagle", "Fish", "Fly", "Dolphin", "Donkey", "Turkey", "Scorpion");


    Supplier<List<String>> of = () -> data;

    static List<String> create() {
        return  data;
    }

    //functional interface
    List<String> off();
}
