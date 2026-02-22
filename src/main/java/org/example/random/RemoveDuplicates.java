package org.example.random;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RemoveDuplicates {
    public static int[] removeDup(int[] nums) {
        return Arrays.stream(nums)
                .distinct()
                .toArray();
    }

    public static void main(String[] args) {
        var ints = new ArrayList<>(List.of(1, 2, 3, 3, 4, 2, 1));
        int[] res = removeDup(ints.stream().mapToInt(i -> i).toArray());
        System.out.println(Arrays.toString(res));
    }
}
