package Mathworks.MathworksVO;

/*
Given a non-empty array of integers, return the k most frequent elements.

Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Example 2:

Input: nums = [1], k = 1
Output: [1]
Note:

You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n),
where n is the array's size.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * clarify:
 *      is answer is unique?
 *      123, 2
 *      12 23 13 all could be the answer
 *
 *      need sort?
 *
 * O(n) bucket sort
 * this is an O(n) time complexity
 */

public class TopKFrequentElements347 {
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer>[] bucket = new List[nums.length + 1];
        List<Integer> ret = new ArrayList<>();
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        for (int n: nums) {
            frequencyMap.put(n, frequencyMap.getOrDefault(n, 0)  +1);
        }

        for (int key: frequencyMap.keySet()) {
            int frequency = frequencyMap.get(key);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }

        for (int pos = bucket.length - 1; pos >= 0 && ret.size() < k; pos--) {
            if (bucket[pos] != null) {
                ret.addAll(bucket[pos]);
            }
        }

        return ret.subList(0, k);
    }
}
