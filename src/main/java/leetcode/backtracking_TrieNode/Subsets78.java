package leetcode.backtracking_TrieNode;

/*
Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
Seen this question in a real interview before?

 */

import java.util.*;

public class Subsets78 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        if (nums.length == 0) return ret;
        int n = nums.length;
        dfs (new ArrayList<>() , 0 , n , nums , ret);
        return ret;
    }

    private void dfs (List<Integer> path , int index , int n , int[] nums , List<List<Integer>> ret) {
        if (index == n) {
            ret.add(new ArrayList<>(path));
            return ;
        }
        dfs (path , index + 1 , n , nums , ret);
        path.add(nums[index]);
        dfs (path , index + 1 , n , nums , ret);
        path.remove(path.size() - 1);
    }
}
