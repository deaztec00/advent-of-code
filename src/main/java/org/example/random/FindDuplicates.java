package org.example.random;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindDuplicates {
    public static boolean containsDuplicates(List<?> input) {
        return input.size() != input.stream().distinct().count();
    }

    public static List<?> getDuplicates(List<?> input) {
        if (input.isEmpty()) return Collections.emptyList();
        Map<?, Long> duplicatesCount = input.stream().collect(
                Collectors.groupingBy(Function.identity(), Collectors.counting())
        );
        return duplicatesCount.entrySet().stream()
                .filter(pair -> pair.getValue() > 1)
                .map(Map.Entry::getKey)
                .toList();
    }

    public static void main(String[] args) {
        List<Integer> dup = List.of(1, 2, 2, 3, 4, 4, 4);
        System.out.println(containsDuplicates(dup));
        System.out.println(getDuplicates(dup));
    }
}
