package leetcode.backtracking;

/*
Given a collection of distinct integers, return all possible permutations.

Example:

Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
Seen this question in a real interview before?

 */

import java.util.*;

public class Permutations46 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        if (nums.length == 0) return ret;
        int n = nums.length;
        dfs (new ArrayList<Integer>() , n , nums , new HashSet<Integer>() , ret);
        return ret;
    }

    private void dfs (List<Integer> path , int n , int[] nums , Set<Integer> set , List<List<Integer>> ret) {
        if (path.size() == n) {
            ret.add(new ArrayList<>(path));
            return ;
        }

        for (int i = 0 ; i < n ; ++i) {
            if (!set.contains(i)) {
                set.add(i);
                path.add(nums[i]);
                dfs(path , n , nums , set , ret);
                path.remove(path.size() - 1);
                set.remove(i);
            }
        }
    }
}
