package Wish;

/*
Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

Each number in candidates may only be used once in the combination.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [10,1,2,7,6,1,5], target = 8,
A solution set is:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
Example 2:

Input: candidates = [2,5,2,1,2], target = 5,
A solution set is:
[
  [1,2,2],
  [5]
]
 */

import java.util.*;

public class CombinationSumII40 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        //sort first then dfs_bfs with breaking when target < 0
        List<List<Integer>> ret = new ArrayList<>();
        if(target <= 0 || candidates == null || candidates.length == 0) return ret;
        Arrays.sort(candidates);
        dfs(ret , new ArrayList<>() , candidates , 0 , target);
        return ret;
    }
    private void dfs(List<List<Integer>> ret , ArrayList<Integer> tem , int[] candidates , int start , int target){
        if(target < 0) return ;
        if(target  == 0){
            ret.add(new ArrayList<>(tem));
        }
        for(int i = start ; i < candidates.length ; ++i){
            if(i > start && candidates[i] == candidates[i - 1]) continue; //avoid same number in the same position when dfs_bfs
            tem.add(candidates[i]);
            dfs(ret , tem , candidates , i + 1 , target - candidates[i]);
            tem.remove(tem.size() - 1);
        }
    }
}
