package org.example.random;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NumbersDisappearedInArray {
    public static List<Integer> disappearedNumbers(int[] nums) {
        Set<Integer> seen = Arrays.stream(nums)
                .boxed()
                .collect(Collectors.toSet());
        return IntStream.rangeClosed(1, nums.length)
                .filter(n -> !seen.contains(n))
                .boxed().toList();
//        alternative solution
//        Set<Integer> seen = new HashSet<>(nums.length);
//        for (int i : nums) {
//            seen.add(i);
//        }
//        List<Integer> missing = new ArrayList<>();
//        for (int i = 1; i < nums.length; i++) {
//            if (!seen.contains(i)) missing.add(i);
//        }
//        return missing;
    }

    public static void main(String[] args) {
        int[] i = {4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println(disappearedNumbers(i));
    }
}
