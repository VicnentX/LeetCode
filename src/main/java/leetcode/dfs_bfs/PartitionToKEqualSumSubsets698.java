package leetcode.dfs_bfs;

/*
Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.

Example 1:
Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
Output: True
Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
Note:

1 <= k <= len(nums) <= 16.
0 < nums[i] < 10000.
 */

/**
 * 这题经典在于 不是简单的放进去 而是trackback true/false 这点有些让人能想到

 这样会更快一些。
 */

import java.util.*;

public class PartitionToKEqualSumSubsets698 {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for(int i : nums){
            sum += i;
        }
        if(sum % k != 0 || nums == null || nums.length == 0 || k == 0){
            return false;
        }
        Arrays.sort(nums);
        if(nums[nums.length - 1] > sum / k){
            return false;
        }
        int[] bucket = new int[k];
        return dfs(bucket , nums , sum / k , nums.length - 1);
    }
    private boolean dfs(int[] bucket , int[] nums , int target , int index){
        if(index == -1){
            return true;
        }
        for(int i = 0 ;  i < bucket.length ; ++i){
            if(bucket[i] + nums[index] <= target){
                bucket[i] += nums[index];
                if(dfs(bucket , nums , target , index - 1)){
                    return true;
                }
                bucket[i] -= nums[index];
            }
            if(bucket[i] == 0){
                break;
                /**这里很有意思 就是当我有一层退完了 就结束了 不用继续往前退了
                 *
                 */
            }
        }
        return false;
    }

    public static void main(String[] args){
        PartitionToKEqualSumSubsets698 pts = new PartitionToKEqualSumSubsets698();
        System.out.println(pts.canPartitionKSubsets(new int[]{4,3,2,3,5,2,1} , 4));
    }
}
