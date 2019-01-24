package leetcode.backtracking_TrieNode;

/*
Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10n.

Example:

Input: 2
Output: 91
Explanation: The answer should be the total numbers in the range of 0 ≤ x < 100,
             excluding 11,22,33,44,55,66,77,88,99
 */

public class CountNumberswithUniqueDigits357 {
    private int ret = 0;
    public int countNumbersWithUniqueDigits(int n) {
        dfs (n);
        dfs0(n);
        return ret;
    }

    private void dfs (int n) {
        int num = 9;
        int sum = 1;
        if (n-- > 0) {
            sum = 9;
        }
        while (n-- > 0) {
            sum *= num--;
        }
        ret += sum;
    }

    private void dfs0 (int n) {
        for (int i = 1 ; i <= n ; ++i) {
            dfs(n - i);
        }
    }

    public static void main (String[] args) {
        CountNumberswithUniqueDigits357 cn = new CountNumberswithUniqueDigits357();
        System.out.println(cn.countNumbersWithUniqueDigits(3));
    }
}
