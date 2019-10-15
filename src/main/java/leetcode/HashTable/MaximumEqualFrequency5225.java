package leetcode.HashTable;

/*
Given an array nums of positive integers, return the longest possible length of an array prefix of nums, such that it is possible to remove exactly one element from this prefix so that every number that has appeared in it will have the same number of occurrences.

If after removing one element there are no remaining elements, it's still considered that every appeared number has the same number of ocurrences (0).



Example 1:

Input: nums = [2,2,1,1,5,3,3,5]
Output: 7
Explanation: For the subarray [2,2,1,1,5,3,3] of length 7, if we remove nums[4]=5, we will get [2,2,1,1,3,3], so that each number will appear exactly twice.
Example 2:

Input: nums = [1,1,1,2,2,2,3,3,3,4,4,4,5]
Output: 13
Example 3:

Input: nums = [1,1,1,2,2,2]
Output: 5
Example 4:

Input: nums = [10,2,8,9,3,8,1,5,2,3,7,6]
Output: 8


Constraints:

2 <= nums.length <= 10^5
1 <= nums[i] <= 10^5
 */

import java.util.*;

public class MaximumEqualFrequency5225 {

    public int maxEqualFreq(int[] nums) {

        int ret = 0;
        int maxOccurence = 0;
        //num and occurrence
        Map<Integer, Integer> map = new HashMap<>();
        //occurrence and set of num
        Map<Integer, Set<Integer>> occurrenceNumsMap = new HashMap<>();
        for (int i = 0 ; i < nums.length; ++i) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            int occurrence = map.get(nums[i]);
            maxOccurence = Math.max(maxOccurence, occurrence);

            if (occurrence > 1) {
                occurrenceNumsMap.get(occurrence - 1).remove(nums[i]);
                if (occurrenceNumsMap.get(occurrence - 1).size() == 0) {
                    occurrenceNumsMap.remove(occurrence - 1);
                }
            }

            if (!occurrenceNumsMap.containsKey(occurrence)) {
                occurrenceNumsMap.put(occurrence, new HashSet<>());
            }
            occurrenceNumsMap.get(occurrence).add(nums[i]);

            if (occurrenceNumsMap.size() == 1 && (occurrenceNumsMap.containsKey(1) || map.size() == 1)) {
                ret = i + 1;
            } else if (occurrenceNumsMap.size() == 2){
                if (occurrenceNumsMap.get(maxOccurence).size() == 1 && occurrenceNumsMap.containsKey(maxOccurence - 1) || occurrenceNumsMap.containsKey(1) && occurrenceNumsMap.get(1).size() == 1) {
                    ret = i + 1;
                }
            }
        }
        return ret;
    }

}
