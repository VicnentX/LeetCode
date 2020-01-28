package leetcode.dfs_and_memo;

/*
You want to schedule a list of jobs in d days. Jobs are dependent (i.e To work on the i-th job, you have to finish all the jobs j where 0 <= j < i).

You have to finish at least one task every day. The difficulty of a job schedule is the sum of difficulties of each day of the d days. The difficulty of a day is the maximum difficulty of a job done in that day.

Given an array of integers jobDifficulty and an integer d. The difficulty of the i-th job is jobDifficulty[i].

Return the minimum difficulty of a job schedule. If you cannot find a schedule for the jobs return -1.



Example 1:


Input: jobDifficulty = [6,5,4,3,2,1], d = 2
Output: 7
Explanation: First day you can finish the first 5 jobs, total difficulty = 6.
Second day you can finish the last job, total difficulty = 1.
The difficulty of the schedule = 6 + 1 = 7
Example 2:

Input: jobDifficulty = [9,9,9], d = 4
Output: -1
Explanation: If you finish a job per day you will still have a free day. you cannot find a schedule for the given jobs.
Example 3:

Input: jobDifficulty = [1,1,1], d = 3
Output: 3
Explanation: The schedule is one job per day. total difficulty will be 3.
Example 4:

Input: jobDifficulty = [7,1,7,1,7,1], d = 3
Output: 15
Example 5:

Input: jobDifficulty = [11,111,22,222,33,333,44,444], d = 6
Output: 843


Constraints:

1 <= jobDifficulty.length <= 300
0 <= jobDifficulty[i] <= 1000
1 <= d <= 10
 */

import java.util.Arrays;

public class MinimumDifficultyofaJobSchedule1335 {
    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if (n < d) return -1;
        int arraySum = Arrays.stream(jobDifficulty).sum();
        //dp就是还剩多少工作要做，剩余多少天，
        int[][] dp = new int[n + 1][d + 1];
        for (int i = 0; i <= n; ++i) {
            for (int j = 0; j <= d; ++j) {
                if (i <= d) {
                    dp[i][j] = -1;
                } else {
                    dp[i][j] = arraySum;
                }
            }
        }
        return dfs(0, n, jobDifficulty, d, dp, jobDifficulty[0], 0);
    }

    private int dfs(int i, int n, int[] jobDifficulty, int d, int[][] dp, int tempMax, int sum) {

        if (i == n && d == 0) {
            dp[i][d] = 0;
            return dp[i][d];
        }

        if (dp[i][d] != -1) {
            return dp[i][d];
        }

        if (d == 0) {
            dp[i][d] = Integer.MAX_VALUE;
            return dp[i][d];
        }

        //一个是用新的一天，一个是继续用当天
        sum += Math.min(dfs(i + 1, n, jobDifficulty, d - 1, dp, i + 1 == n ? Integer.MAX_VALUE : jobDifficulty[i + 1], sum + tempMax)
                , dfs(i + 1, n, jobDifficulty, d, dp, Math.max(tempMax, jobDifficulty[i]), sum));

        dp[i][d] = sum;
        return dp[i][d];
    }






    private int globalMin = Integer.MAX_VALUE;

    private void dfsWithoutMem(int i, int n, int[] jobDifficulty, int remainDay, int sum, int tempMax) {

        if (i == n) {
            globalMin = Math.min(globalMin, sum);
            return;
        }


        if (n - i == remainDay) {
            dfsWithoutMem(i + 1, n, jobDifficulty, remainDay - 1, sum + jobDifficulty[i], 0);
        }
        dfsWithoutMem(i + 1, n, jobDifficulty, remainDay, sum, Math.max(tempMax, jobDifficulty[i]));
    }
}
