package google.google2020.google_加油少年_VO;

/*
You are allowed three operations
(each of these is considered a 'hop'), these are:
- Divide by 3
- Divide by 2
- Subtract 1

Given a positive integer as input,
compute the minimum number of hops
to reduce the number down to 1.
 */

import java.util.Arrays;

public class MinHopCnt {
    public int solve(int num) {
        int[] dp = new int[num + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[num] = 0;
        for (int i = num; i >= 2; --i) {
            if (dp[i] != Integer.MAX_VALUE) {
                if (i % 3 == 0) {
                    dp[i / 3] = Math.min(dp[i / 3], dp[i] + 1);
                }
                if (i % 2 == 0) {
                    dp[i / 2] = Math.min(dp[i / 2], dp[i] + 1);
                }
                dp[i - 1] = Math.min(dp[i - 1], dp[i] + 1);
            }
        }
        return dp[1];
    }

    public static void main(String[] args) {
        MinHopCnt minHopCnt = new MinHopCnt();
        //3
        System.out.println(minHopCnt.solve(7));
        //3
        System.out.println(minHopCnt.solve(10));
        //2
        System.out.println(minHopCnt.solve(9));
        //
        System.out.println(minHopCnt.solve(103));
    }
}
