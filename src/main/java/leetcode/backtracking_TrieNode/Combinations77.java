package leetcode.backtracking_TrieNode;

/*
 Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

Example:

Input: n = 4, k = 2
Output:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
Seen this question in a real interview before?

 */

import java.util.*;

public class Combinations77 {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ret = new ArrayList<>();
        if (n < 1 || k <= 0) return ret;
        dfs (new ArrayList<>() , 1 , 0 , k  , n , ret);
        return ret;
    }

    private void dfs (ArrayList<Integer> path , int start , int len , int k , int n , List<List<Integer>> ret) {
        if (len == k) {
            ret.add(new ArrayList<>(path));
            return ;
        }

        for (int i = start ; i <= n ; ++i) {
            path.add(i);
            dfs (path , i + 1 , len + 1 , k , n , ret);
            path.remove(path.size() - 1);
        }
    }
}
