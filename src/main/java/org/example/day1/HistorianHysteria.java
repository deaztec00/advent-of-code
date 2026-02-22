package org.example.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistorianHysteria {
    private record Columns(List<Integer> left, List<Integer> right) {
    }

    private static Columns readFile() {
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/example/day1/input.txt"))) {
            br.lines().map(line -> line.split("\\s+"))
                    .forEach(vals -> {
                        left.add(Integer.valueOf(vals[0]));
                        right.add(Integer.valueOf(vals[1]));
                    });
        } catch (IOException ignored) {
        }
        return new Columns(left, right);
    }

    private static int distanceCounter() {
        var columns = readFile();
        List<Integer> left = columns.left;
        List<Integer> right = columns.right;
        int distance = 0;
        while (left.size() != 0) {
            Integer minLeft = Collections.min(left);
            Integer minRight = Collections.min(right);
            left.remove(minLeft);
            right.remove(minRight);
            int distanceChange = Math.abs(minLeft - minRight);
            distance += distanceChange;
        }

        return distance;
    }

    private static int similarityScore() {
        var columns = readFile();
        List<Integer> left = columns.left;
        List<Integer> right = columns.right;
        int similarityScore = 0;
        similarityScore = left.stream()
                .map(leftNumber -> leftNumber * Collections.frequency(right, leftNumber))
                .reduce(0, Integer::sum);
        return similarityScore;
    }

    public static void main(String[] args) {
        System.out.println(distanceCounter());
        System.out.println(similarityScore());
    }
}