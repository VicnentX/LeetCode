package leetcode.HashTable;

/*
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

Your algorithm should run in O(n) complexity.

Example:

Input: [100, 4, 200, 1, 3, 2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 */

import java.util.*;

public class LongestConsecutiveSequence128 {
    public int longestConsecutive(int[] nums) {
        int ret = 0;
        if (nums == null || nums.length == 0) return ret;
        Set<Integer> set = new HashSet<>();
        for (int k : nums) {
            set.add(k);
        }
        for (int k : nums) {
            if (set.remove(k)) {
                int cnt = 1;
                int val = k;
                while (set.remove(val - 1)) --val;
                cnt += (k - val);

                val = k;
                while (set.remove(val + 1)) ++val;
                cnt += (val - k);

                ret = Math.max(ret , cnt);
            }
        }
        return ret;
    }

    public static void main (String[] args) {
        LongestConsecutiveSequence128 lc = new LongestConsecutiveSequence128();
        System.out.println(lc.longestConsecutive(new int[] {100,4,200,1,3,2,5}));
    }
}
