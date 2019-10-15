package leetcode.dfs_bfs;

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

import java.util.*;

/**
 * 第 158 场比赛题解：
 *
 * 1221. 【统计】字符串贪心与统计 O(n)
 * 1222. 【枚举】国王攻击王后
 * 1223. 【动态规划】有限制的掷骰子（详解）
 * 1224. 【统计】最大相等频率，详解，O(N)
 * 题意：每次骰子可以随机的得到1~6这些数字。
 * 现在需要掷n次骰子，但是每个数字连续出现的次数有个最大限制。
 * 问此时，可能出现的数字序列有多少个。
 *
 * 思路：典型的动态规划题。
 *
 * 假设当前是起始状态，此时前面是没有依赖，我们可以随机n次。
 * 掷一次骰子后，可能出现6个数字其中一个，而下个状态就有依赖了，即前面的数字出现的次数需要保持下来。
 *
 * 这里的状态定义为f(k, index, n)，代表数字index在前面出现k次时，再掷n次骰子会出现多少序列。
 * 那么答案就是：
 *
 * ans += f(1, 1, n-1)
 * ans += f(1, 2, n-1)
 * ans += f(1, 3, n-1)
 * ans += f(1, 4, n-1)
 * ans += f(1, 5, n-1)
 * ans += f(1, 6, n-1)
 * 那对于有依赖的状态，大概分四种情况。
 *
 * 情况一：n等于0了，此时不能掷骰子了，确定一个序列，找到一个答案。
 * 情况二：随机出的数字newIndex与index无关，那么得到新的状态f(1, newIndex, n-1)。
 * 情况三：随机出当前index，但是还没到达限制，那么得到新状态f(k+1, index, n-1)
 * 情况四：随机出当前index，但是到达限制，忽略这个状态。
 *
 * 汇总一下就是下面的状态转移方程：
 *
 * f(k, index, n) = 五个 f(1, newindex, n) + 合法的f(k+1, index, n-1)
 * 代码：
 */

public class DiceRollSimulation5224 {

    private int ret = 0;
    Map<List<Integer>, Long> map = new HashMap<>();
    private long mod = 1000000000 + 7;

    public int dieSimulator(int n, int[] rollMax) {
        return (int)dfs(0, 0, 0, rollMax, n);
    }

    private long dfs(int cnt, int previousNum, int remain, int[] rollMax, final int n) {
        if (cnt == n) {
            return 1;
        }

        List<Integer> key = new ArrayList<>(Arrays.asList(cnt, previousNum, remain));
        if (map.containsKey(key)) {
            return map.get(key);
        }

        long ans = 0;

        for (int i = 1 ; i <= 6; ++i) {
            if (i != previousNum) {
                ans += dfs(cnt + 1, i, rollMax[i - 1] - 1, rollMax, n);
            } else {
                if (remain > 0) {
                ans += dfs(cnt + 1, previousNum, remain - 1, rollMax, n);
                }
            }
        }

        map.put(key, ans % mod);

        return ans % mod;
    }

}
