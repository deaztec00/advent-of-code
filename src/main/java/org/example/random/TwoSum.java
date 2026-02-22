package org.example.random;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
    Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 */
public class TwoSum {
    public static int[] twoSum(int[] nums, int target) throws IllegalArgumentException {
        if (nums.length < 2) throw new IllegalArgumentException("Not enough input data");
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int remainder = target - nums[i];
            if (map.containsKey(remainder)) {
                return new int[]{map.get(remainder), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No solution found");
    }

    public static void main(String[] args) {

        try {
            System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9))); //0,1
            System.out.println(Arrays.toString(twoSum(new int[]{3, 3}, 6)));//0,1
            System.out.println(Arrays.toString(twoSum(new int[]{3, 1, 3}, 6)));//0,2
            System.out.println(Arrays.toString(twoSum(new int[]{-1, -2, -3, -4, -5}, -8)));//2,4
            System.out.println(Arrays.toString(twoSum(new int[]{0, 1, 2, 0}, 0)));//0,3
            System.out.println(Arrays.toString(twoSum(new int[]{Integer.MAX_VALUE, 0, Integer.MIN_VALUE}, -1)));//0,2
            System.out.println(Arrays.toString(twoSum(new int[]{1, 2}, 4)));
//            System.out.println(Arrays.toString(twoSum(new int[]{1}, 4)));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
