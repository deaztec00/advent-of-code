package org.example.aoc2025.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static java.util.Collections.emptyList;

/*
50 - initial pointer
   (99) L   0   R (1)
        <   ^   >
            X
L19 = X-19  || R12 = X+12
Answer - number of times the dial pointed at 0
 */
public class SecretEntrance {
    private static Integer DIAL_MAX = 100;
    private static Integer DIAL_MIN = 0;
    private static final Integer INITIAL_POINTER = 50;
    private static final String PATH_TO_SAMPLE = "src/main/java/org/example/aoc2025/day1/input1.txt";
    private static final String PATH_TO_ACTUAL = "src/main/java/org/example/aoc2025/day1/input.txt";

    private static String LEFT = "L";

    private record Instruction(String direction, Integer clickCount) {
        @Override
        public String toString() {
            return direction + " " + clickCount;
        }
    }

    private static List<Instruction> extractInstructions(String pathToInstructions) {
        try (BufferedReader br = new BufferedReader(new FileReader(pathToInstructions))) {
            return br.lines()
                    .filter(line -> !line.isEmpty())
                    .map(line -> new Instruction(line.substring(0, 1), Integer.parseInt(line.substring(1))))
                    .toList();

        } catch (IOException e) {
            e.printStackTrace();
            return emptyList();
        }
    }

    private static int getPassword(List<Instruction> instructions) {
        int pwCounter = 0;
        int cp = INITIAL_POINTER;
        for (Instruction instruction : instructions) {
            if (LEFT.equals(instruction.direction)) {
                int scrollResult = cp - instruction.clickCount;
                if (scrollResult == 0) {
                    pwCounter++;
                }
                if (scrollResult < 0) {
                    cp = scrollResult + DIAL_MAX;
                } else {
                    cp = scrollResult;
                }
            } else {
                int scrollResult = cp + instruction.clickCount;
                if (scrollResult > DIAL_MAX) {
                    cp = scrollResult - DIAL_MAX;
                } else {
                    cp = scrollResult;
                    if (scrollResult == 0 || scrollResult == 100) {
                        pwCounter++;
                        cp = 0;
                    }
                }
            }
        }
        return pwCounter;
    }

    public static void main(String[] args) {
        List<Instruction> instructions = extractInstructions(PATH_TO_ACTUAL);
        System.out.println(getPassword(instructions));
    }
}
