package leetcode.KnapsackProblem;

/*
0-1背包问题的通常定义是：一共有N件物品，第i件物品的重量为w[i]，价值为v[i]。在总重量不超过背包承载上限W的情况下，能够获得的最大价值是多少？每件物品可以使用0次或者1次。

例子：

重量 w = [1, 1, 2, 2]

价值 v = [1, 3, 4, 5]

背包承重 W = 4

最大价值为9，可以选第1,2,4件物品，也可以选第3，4件物品；总重量为4，总价值为9。

动态规划的状态转移方程为：

1
dp[i][j] = max(dp[i-1][j], dp[i][j-w[i]] + v[i])
 */


/**
 * 我们看到的求最优解的背包问题题目中，事实上有两种不太相同的问法。有的题目要求“恰好装满背包”时的最优解，有的题目则并没有要求必须把背包装满。一种区别这两种问法的实现方法是在初始化的时候有所不同。
 *
 * 如果是第一种问法，要求恰好装满背包，那么在初始化时除了f[0]为0其它f[1..V]均设为-∞，这样就可以保证最终得到的f[N]是一种恰好装满背包的最优解。
 *
 * 如果并没有要求必须把背包装满，而是只希望价格尽量大，初始化时应该将f[0..V]全部设为0。
 *
 * 为什么呢？可以这样理解：初始化的f数组事实上就是在没有任何物品可以放入背包时的合法状态。如果要求背包恰好装满，那么此时只有容量为0的背包可能被价值为0的nothing“恰好装满”，其它容量的背包均没有合法的解，属于未定义的状态，它们的值就都应该是-∞了。如果背包并非必须被装满，那么任何容量的背包都有一个合法解“什么都不装”，这个解的价值为0，所以初始时状态的值也就全部为0了。
 *
 * 这个小技巧完全可以推广到其它类型的背包问题，后面也就不再对进行状态转移之前的初始化进行讲解。
 */
public class SackZeroOne {
    public int solve(int[] weights, int[] values, int maxWeight) {
        /**
         * 如果并没有要求必须把背包装满，而是只希望价格尽量大，
         * 初始化时应该将f[0..V]全部设为0。
         */
        int n = weights.length;
        int[][] dp = new int[n + 1][maxWeight + 1];
        for (int i = 0 ; i < n; ++i) {
            for (int j = 0; j <= maxWeight; ++j) {
                dp[i + 1][j] = Math.max(dp[i][j], j - weights[i] >= 0 ? dp[i][j - weights[i]] + values[i] : 0);
            }
        }
        return dp[n][maxWeight];
    }


    public int solveSpaceReduce(int[] weights, int[] values, int maxWeight) {
        /**
         * 如果并没有要求必须把背包装满，而是只希望价格尽量大，
         * 初始化时应该将f[0..V]全部设为0。
         */
        int n = weights.length;
        int[] dp = new int[maxWeight + 1];
        for (int i = 0 ; i < n; ++i) {
            for (int j = maxWeight; j >= weights[i]; --j) {
                dp[j] = Math.max(dp[j], dp[j - weights[i]] + values[i]);
            }
        }
        return dp[maxWeight];
    }


    public int solveMustFillingOut(int[] weights, int[] values, int maxWeight) {
        /**
         * 如果是第一种问法，要求恰好装满背包，那么在初始化时除了f[0]为0
         * 其它f[1..V]均设为-∞，这样就可以保证最终得到的f[N]是一种恰好装满背包的最优解。
         */
        int n = weights.length;
        int[][] dp = new int[n + 1][maxWeight + 1];
        for(int i = 1; i <= maxWeight; ++i) {
            dp[0][i] = Integer.MIN_VALUE;
        }
        for (int i = 0 ; i < n; ++i) {
            for (int j = 0; j <= maxWeight; ++j) {
                dp[i + 1][j] = Math.max(dp[i][j], j - weights[i] >= 0 ? dp[i][j - weights[i]] + values[i] : 0);
            }
        }
        return Math.max(0, dp[n][maxWeight]);
    }


    public static void main(String[] args) {
        SackZeroOne sackSeroOne = new SackZeroOne();
        //不用塞满 13
        System.out.println(sackSeroOne.solve(new int[] {1, 1, 2, 2}, new int[] {1, 3, 4, 5}, 7));
        //不用塞满 space reduce 13
        System.out.println(sackSeroOne.solveSpaceReduce(new int[] {1, 1, 2, 2}, new int[] {1, 3, 4, 5}, 7));
        //塞满 （因为不可能塞满这个例子 就只能是0）
        System.out.println(sackSeroOne.solveMustFillingOut(new int[] {1, 1, 2, 2}, new int[] {1, 3, 4, 5}, 7));
        //塞满  = 12
        System.out.println(sackSeroOne.solveMustFillingOut(new int[] {1, 1, 2, 2}, new int[] {1, 3, 4, 5}, 5));
    }
}
