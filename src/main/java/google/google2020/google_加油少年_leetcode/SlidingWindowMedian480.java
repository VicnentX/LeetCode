package google.google2020.google_加油少年_leetcode;

/*
Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

Examples:
[2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Your job is to output the median array for each window in the original array.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Median
---------------               -----
[1  3  -1] -3  5  3  6  7       1
 1 [3  -1  -3] 5  3  6  7       -1
 1  3 [-1  -3  5] 3  6  7       -1
 1  3  -1 [-3  5  3] 6  7       3
 1  3  -1  -3 [5  3  6] 7       5
 1  3  -1  -3  5 [3  6  7]      6
Therefore, return the median sliding window as [1,-1,-1,3,5,6].

Note:
You may assume k is always valid, ie: k is always smaller than input array's size for non-empty array.
 */

import leetcode.Number.SlidingWindowMaximum;

import java.util.*;

public class SlidingWindowMedian480 {

    //O(nk)
    public double[] medianSlidingWindow(int[] nums, int k) {

        List<Integer> sortedList = new ArrayList<>();
        double [] result = new double[nums.length - k + 1];
        for(int i = 0; i < nums.length; i++) {
            int current = nums[i];
            add(sortedList, current);
            if(sortedList.size() == k) {
                int leftMostIdx = i - k + 1;
                double median;
                if(k % 2 == 0) {
                    median = ((long)sortedList.get(k / 2 - 1) + (long)sortedList.get(k / 2))/2.0;
                } else {
                    median = sortedList.get(k / 2);
                }
                result[leftMostIdx] = median;
                remove(sortedList, nums[leftMostIdx]);
            }
        }
        return result;
    }

    private void add(List<Integer> sortedList, int value) {
        int index = Collections.binarySearch(sortedList, value);
        if(index < 0) {
            sortedList.add(index * -1 - 1, value);
        } else {
            sortedList.add(index, value);
        }
    }

    private void remove(List<Integer> sortedList, int value) {
        int index = Collections.binarySearch(sortedList, value);
        sortedList.remove(index);
    }

    public static void main(String[] args) {
        SlidingWindowMedian480 slidingWindowMedian480 = new SlidingWindowMedian480();
        double[] ret = slidingWindowMedian480.medianSlidingWindow(new int[] {1,3,-1,-3,5,3,6,7}, 3);
        for (double d: ret) {
            System.out.println(d);
        }
    }
}
