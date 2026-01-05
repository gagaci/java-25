package org.example;

// instead of this ⬇️

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// you can import things by modules ⬇️
import module java.base;

public class JEP511 {
    static void main() {
        String[] fruits = {"apple", "berry", "citrus"};
        Map<String, String> m = Stream.of(fruits)
                .collect(Collectors.toMap(s -> s.toUpperCase().substring(0,1),
                        Function.identity()));
    }
}
