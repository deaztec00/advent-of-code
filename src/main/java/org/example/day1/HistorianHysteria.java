package org.example.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistorianHysteria {
    private static int distanceCounterPart1() {
        int distance = 0;
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/example/day1/input.txt"))) {
            br.lines().map(line -> line.split("\\s+"))
                    .forEach(vals -> {
                        left.add(Integer.valueOf(vals[0]));
                        right.add(Integer.valueOf(vals[1]));
                    });
        } catch (IOException e) {
        }
        while (left.size() != 0) {
            Integer minLeft = Collections.min(left);
            Integer minRight = Collections.min(right);
            left.remove(minLeft);
            right.remove(minRight);
            int distanceChange = minLeft - minRight;
            distanceChange = distanceChange < 0 ? distanceChange * -1 : distanceChange;
            distance = distance + distanceChange;
        }

        return distance;
    }

    private static int similarityScore() {
        int similarityScore = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/example/day1/input.txt"))) {
            List<Integer> left = new ArrayList<>();
            List<Integer> right = new ArrayList<>();
            br.lines().map(line -> line.split("\\s+"))
                    .forEach(vals -> {
                        left.add(Integer.valueOf(vals[0]));
                        right.add(Integer.valueOf(vals[1]));
                    });
            similarityScore = left.stream()
                    .map(leftNumber -> leftNumber * Collections.frequency(right, leftNumber))
                    .reduce(0, Integer::sum);
        } catch (IOException e) {

        }
        return similarityScore;
    }

    public static void main(String[] args) {
        System.out.println(distanceCounterPart1());
        System.out.println(similarityScore());
    }
}