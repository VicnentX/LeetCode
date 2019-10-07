package Mathworks.MathworksVO;

/*
Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its elements.

Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.

Example 1:
Input: [1, 2, 2, 3, 1]
Output: 2
Explanation:
The input array has a degree of 2 because both elements 1 and 2 appear twice.
Of the subarrays that have the same degree:
[1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
The shortest length is 2. So return 2.
Example 2:
Input: [1,2,2,3,1,4,2]
Output: 6
Note:

nums.length will be between 1 and 50,000.
nums[i] will be an integer between 0 and 49,999.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DegreeOfAnArray697 {
    public int findShortestSubArray(int[] nums) {
        Map<Integer, List<Integer>> numberIndexesPair = new HashMap<>();
        int minLen = Integer.MAX_VALUE;
        int maxOccurence = -1;
        //fill the map
        for (int i = 0; i < nums.length; ++i) {
            int num = nums[i];
            if (!numberIndexesPair.containsKey(num)) {
                numberIndexesPair.put(num, new ArrayList<>());
            }
            numberIndexesPair.get(num).add(i);
        }
        //get the result
        for (List<Integer> indexList: numberIndexesPair.values()) {
            int curDegree = indexList.get(indexList.size() - 1) - indexList.get(0) + 1;
            if (indexList.size() > maxOccurence) {
                maxOccurence = indexList.size();
                minLen = curDegree;
            } else if(indexList.size() == maxOccurence ) {
                if (curDegree < minLen) {
                    minLen = curDegree;
                }
            }
        }
        return minLen;
    }

    public static void main(String[] args) {
        DegreeOfAnArray697 degreeOfAnArray697 = new DegreeOfAnArray697();
        //6
        System.out.println(degreeOfAnArray697.findShortestSubArray(new int[] {1,2,2,3,1,4,2}));
        //2
        System.out.println(degreeOfAnArray697.findShortestSubArray(new int[] {1,2,2,3,1}));
    }
}
