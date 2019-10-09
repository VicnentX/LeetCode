package Amazon.full_time2020.final_round_加油少年_coding;

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

import java.util.ArrayList;
import java.util.List;

/**
 * dfs
 *
 * 就是只能用k个数 凑齐n 有多少种方法
 *
 * 数组是从1-n
 */

public class CombinationSumIII216 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> combinationList = new ArrayList<>();
        dfs(1, 0, k, n, new ArrayList<>(), combinationList);
        return combinationList;
    }

    private void dfs(int start, int curSum, int remain, int sum, List<Integer> path, List<List<Integer>> combinationList) {
        if (remain == 0) {
            if (curSum == sum) {
                combinationList.add(new ArrayList<>(path));
                return;
            }
        }

        if (curSum >= sum) return;

        for (int i = start; i <= 9; ++i) {
            path.add(i);
            dfs(i + 1, curSum + i, remain - 1, sum, path, combinationList);
            path.remove(path.size() - 1);
        }

    }
}
