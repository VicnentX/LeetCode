package leetcode.backtracking;

/*
Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

Note:

All numbers will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: k = 3, n = 7
Output: [[1,2,4]]
Example 2:

Input: k = 3, n = 9
Output: [[1,2,6], [1,3,5], [2,3,4]]
 */

import java.util.*;

public class CombinationSumIII216 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ret = new ArrayList<>();
        if (n < 0 || k <= 0) return ret;
        dfs (1 , new ArrayList<>() , 0 , 0 , k , n , ret);
        return ret;
    }

    private void dfs (int cur , List<Integer> path , int level , int sum , int k , int n , List<List<Integer>> ret) {
        if (level == k) {
            if (sum == n) {
                ret.add(new ArrayList<>(path));
            }
            return;
        }

        /**
         * 这里cur的值要设置下 不然就会overstack
         */
        if (sum >= n || cur >= 10) return;

        dfs (cur + 1 , path , level , sum , k , n , ret);
        path.add(cur);
        dfs (cur + 1 , path , level + 1 , sum + cur , k , n , ret);
        path.remove(path.size() - 1);
    }

    public static void main (String[] args) {
        CombinationSumIII216 cs = new CombinationSumIII216();
        System.out.println(cs.combinationSum3(3,7));
    }
}
