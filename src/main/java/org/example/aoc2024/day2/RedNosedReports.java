package org.example.aoc2024.day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class RedNosedReports {
    private static final int MIN_DISTANCE = 1;
    private static final int MAX_DISTANCE = 3;
    private static final Predicate<int[]> validReportPredicate = ints -> {
        int initialDifference = ints[0] - ints[1];
        int initialDistance = Math.abs(initialDifference);
        if (!isValidDistance(initialDistance)) return false;
        boolean sequenceDecreasing = initialDifference > 0;
        for (int i = 0; i < ints.length - 1; i++) {
            int currentDifference = ints[i] - ints[i + 1];
            // check if the sequence is either increasing or decreasing at all times
            if (!sequenceHasSameDirection(currentDifference > 0, sequenceDecreasing)) return false;
            int currentDistance = Math.abs(currentDifference);
            if (!isValidDistance(currentDistance)) return false;
        }
        return true;
    };
    private static final Predicate<int[]> validRportCountTolerantPredicate = ints -> {
        if (!validReportPredicate.test(ints)) {
            List<Integer> report = Arrays.stream(ints).boxed().toList();
            for (int i = 0; i < report.size(); i++) {
                //create a copyOfReport.remove(currentElement)
                List<Integer> reportCopy = new ArrayList<>(report);
                reportCopy.remove(i);
                //turn it into an array of integers
                int[] validateReducedReports = reportCopy.stream().mapToInt(Integer::intValue).toArray();
                //validate against predicate
                if (validReportPredicate.test(validateReducedReports)) return true;
            }
        }
        return false;
    };

    private static boolean isValidDistance(int distance) {
        return (distance <= MAX_DISTANCE) && (distance >= MIN_DISTANCE);
    }

    private static boolean sequenceHasSameDirection(boolean currentDirection, boolean previousDirection) {
        return currentDirection == previousDirection;
    }

    private static List<int[]> extractAndFilterReports(Predicate<int[]>... predicates) {
        List<int[]> validReports = Collections.emptyList();
        Predicate<int[]> combinedPredicate = Arrays.stream(predicates).reduce(p -> false, Predicate::or);
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/example/day2/input1.txt"))) {
            validReports = br.lines()
                    .map(line -> line.split("\\s+"))
                    .map(line -> Arrays.stream(line).mapToInt(Integer::parseInt).toArray())
                    .filter(combinedPredicate)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            return Collections.emptyList();
        }
        return validReports;
    }

    public static void main(String[] args) {
        System.out.println(extractAndFilterReports(validReportPredicate).size());
        System.out.println(extractAndFilterReports(validReportPredicate, validRportCountTolerantPredicate).size());
    }
}