package leetcode.SlideWindow_TwoPointers;
/*
Given two arrays, write a function to compute their intersection.

Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2,2]
Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [4,9]
Note:

Each element in the result should appear as many times as it shows in both arrays.
The result can be in any order.
Follow up:

What if the given array is already sorted? How would you optimize your algorithm?
What if nums1's size is small compared to nums2's size? Which algorithm is better?
What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
 */

/**
 * What if elements of nums2 are stored on disk, and the memory is
 * limited such that you cannot load all elements into the memory at
 * once?
 *
 * If only nums2 cannot fit in memory, put all elements of nums1 into a HashMap, read chunks of array that fit into the memory, and record the intersections.
 *
 * If both nums1 and nums2 are so huge that neither fit into the memory, sort them individually (external sort), then read 2 elements from each array at a time in memory, record intersections.
 */


import java.util.*;
public class IntersectionofTwoArraysII350 {
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) return new int[] {};
        Map<Integer , Integer> map = new HashMap<>();
        for (int k : nums1) {
            map.put(k , map.getOrDefault(k , 0) + 1);
        }
        List<Integer> list = new ArrayList<>();
        for (int k : nums2) {
            if (map.containsKey(k) && map.get(k) > 0) {
                list.add(k);
                map.put(k , map.get(k) - 1);
            }
        }
        int[] ret = new int[list.size()];
        int index = 0;
        for (int k : list) {
            ret[index] = k;
            ++index;
        }
        return ret;
    }
}
