package leetcode.backtracking;

/*
Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

The same repeated number may be chosen from candidates unlimited number of times.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]
Example 2:

Input: candidates = [2,3,5], target = 8,
A solution set is:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
 */

import java.util.*;

public class CombinationSum39 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ret = new ArrayList<>();
        if (candidates.length == 0) return ret;
        int n = candidates.length;
        dfs (0 , n , candidates , 0 , target , new ArrayList<>() , ret);
        return ret;
    }

    private void dfs (int start , int n , int[] nums , int sum , int target , List<Integer> path , List<List<Integer>> ret) {
        if (sum > target || start == n) return ;
        if (sum == target) {
            ret.add(new ArrayList<>(path));
            return;
        }
        path.add(nums[start]);
        dfs(start , n , nums , sum + nums[start] , target , path , ret);
        path.remove(path.size() - 1);
        dfs(start + 1 , n , nums , sum , target , path , ret);
    }
}
