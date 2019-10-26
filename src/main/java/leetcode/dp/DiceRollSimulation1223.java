package leetcode.dp;

/*
A die simulator generates a random number from 1 to 6 for each roll. You introduced a constraint to the generator such that it cannot roll the number i more than rollMax[i] (1-indexed) consecutive times.

Given an array of integers rollMax and an integer n, return the number of distinct sequences that can be obtained with exact n rolls.

Two sequences are considered different if at least one element differs from each other. Since the answer may be too large, return it modulo 10^9 + 7.



Example 1:

Input: n = 2, rollMax = [1,1,2,2,2,3]
Output: 34
Explanation: There will be 2 rolls of die, if there are no constraints on the die, there are 6 * 6 = 36 possible combinations. In this case, looking at rollMax array, the numbers 1 and 2 appear at most once consecutively, therefore sequences (1,1) and (2,2) cannot occur, so the final answer is 36-2 = 34.
Example 2:

Input: n = 2, rollMax = [1,1,1,1,1,1]
Output: 30
Example 3:

Input: n = 3, rollMax = [1,1,1,2,2,3]
Output: 181


Constraints:

1 <= n <= 5000
rollMax.length == 6
1 <= rollMax[i] <= 15
 */

public class DiceRollSimulation1223 {

    public int dieSimulator(int n, int[] rollMax) {
        final int KMaxRolls = 15;
        final int divisor = (int)Math.pow(10, 9) + 7;

        //dp[i][j][k] = # of sequences ends with k consecutive j after i rolls
        int[][][] dp = new int[n + 1][6][KMaxRolls + 1];

        //initialize
        for (int j = 0; j < 6; ++j) {
            dp[1][j][1] = 1; // 1 step , 1 dice , 1 way
        }

        for (int i = 2; i <= n ; ++i) {
            for (int j = 0; j < 6; ++j) {
                for (int p = 0; p < 6; ++p) {   //现在这次是p
                    for (int k = 1; k <= rollMax[p]; ++k) {
                        if (p != j) {
                            dp[i][j][1] = (dp[i][j][1] + dp[i - 1][p][k]) % divisor;
                        } else if (k < rollMax[j]) {
                            dp[i][j][k + 1] = dp[i - 1][j][k];
                        }
                    }
                }
            }
        }

        int ret = 0;
        for (int j = 0; j < 6; ++j) {
            for (int k = 1; k <= rollMax[j]; ++k) {
                ret = (ret + dp[n][j][k]) % divisor;
            }
        }

        return ret;
    }

}
