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


/**
 * Explanation
 * dfs help find the the minimum difficulty
 * if start work at ith job with d days left.
 *
 * If d = 1, only one day left, we have to do all jobs,
 * return the maximum difficulty of jobs.
 *
 *
 * Complexity
 * Time O(nnd)
 * Space O(nd)
 */

import java.util.Arrays;

public class MinimumDifficultyofaJobSchedule1335 {
    public int minDifficultyDFSMEM(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if (n < d) return -1;
        int[][] dp = new int[n][d + 1];
        for (int[] rows: dp) {
            Arrays.fill(rows, -1);
        }
        return dfs(0, d, jobDifficulty, jobDifficulty.length, dp);
    }

    //dfs返回的是从第i个任务开始，并且只剩余d天的时候，最小的diffculty是多少
    private int dfs(int i, int d, int[] jobDifficulty, int n, int[][] dp) {

        if (dp[i][d] != -1) return dp[i][d];

        if (d == 1) {
            return dp[i][d] = Arrays.stream(jobDifficulty, i, n).max().getAsInt();
        }

        int ret = Integer.MAX_VALUE;
        int maxd = 0;


        //这里的maxd就相当于把i 到 index 当作一天，然后和后面的dfs加起来
        for (int index = i; index < n - d + 1; ++index) {
            maxd = Math.max(maxd, jobDifficulty[index]);
            ret = Math.min(ret, maxd + dfs(index + 1, d - 1, jobDifficulty, n, dp));
        }

        return dp[i][d] = ret;
    }

    public int minDifficultyDP(int[] jobDifficulty, int d) {

        int n=jobDifficulty.length;
        int sum=0;
        if(d>n)
            return -1;

        if(d==n)
        {
            for(int i=0;i<n;i++)
                sum+=jobDifficulty[i];

            return sum;

        }

        int dp[][]=new int[d][n];
        dp[0][0]=jobDifficulty[0];

        for(int j=1;j<n;j++)
        {
            dp[0][j]=Math.max(jobDifficulty[j],dp[0][j-1]);
        }

        for(int i=1;i<d;i++)
        {
            for(int j=i;j<n;j++)
            {
                int localMx=jobDifficulty[j];
                dp[i][j]=Integer.MAX_VALUE;
                for(int r=j;r>=i;r--)
                {
                    localMx=Math.max(localMx,jobDifficulty[r]);
                    dp[i][j]=Math.min(dp[i][j],dp[i-1][r-1]+localMx);

                }
            }
        }
        return dp[d-1][n-1];
    }
}
