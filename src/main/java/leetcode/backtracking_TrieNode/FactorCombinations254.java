package leetcode.backtracking_TrieNode;

/*
Numbers can be regarded as product of its factors. For example,

8 = 2 x 2 x 2;
  = 2 x 4.
Write a function that takes an integer n and return all possible combinations of its factors.

Note:

You may assume that n is always positive.
Factors should be greater than 1 and less than n.
Example 1:

Input: 1
Output: []
Example 2:

Input: 37
Output:[]
Example 3:

Input: 12
Output:
[
  [2, 6],
  [2, 2, 3],
  [3, 4]
]
Example 4:

Input: 32
Output:
[
  [2, 16],
  [2, 2, 8],
  [2, 2, 2, 4],
  [2, 2, 2, 2, 2],
  [2, 4, 4],
  [4, 8]
]
 */

import java.util.*;
public class FactorCombinations254 {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> ret = new ArrayList<>();
        dfs (new ArrayList<>() ,  2 , n , ret);
        return ret;
    }

    private void dfs (List<Integer> path , int start , int  n , List<List<Integer>> ret) {
        if (n == 1) {
            if (path.size() > 1) {
                ret.add(new ArrayList<>(path));
            }
            return;
        }

        for (int i = start ; i <= n ; ++i) {
            if (n % i == 0) {
                path.add(i);
                dfs (path , i , n / i , ret);
                path.remove(path.size() - 1);
            }
        }
    }

    public static void main (String[] args) {
        FactorCombinations254 fc = new FactorCombinations254();
        System.out.println(fc.getFactors(66));
    }
}
