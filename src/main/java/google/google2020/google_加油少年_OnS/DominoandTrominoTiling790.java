package google.google2020.google_加油少年_OnS;

/*
when N==0, we need return 0, but in dp ,
 we need make dp[0]=1 for easy to construct formula
image
sorry my handwriting is ugly

dp[n]=dp[n-1]+dp[n-2]+ 2*(dp[n-3]+...+d[0])
=dp[n-1]+dp[n-2]+dp[n-3]+dp[n-3]+2*(dp[n-4]+...+d[0])
=dp[n-1]+dp[n-3]+(dp[n-2]+dp[n-3]+2*(dp[n-4]+...+d[0]))
=dp[n-1]+dp[n-3]+dp[n-1]
=2*dp[n-1]+dp[n-3]
 */


public class DominoandTrominoTiling790 {
    public int numTilings(int N) {
        int md = 1000000007;
        if (N <= 0) return 1;
        int[] dp = new int[N + 1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 5;
        if (N <= 3) return dp[N];
        for (int i = 4; i <= N; ++i) {
            dp[i] = (2 * dp[i - 1] % md + dp[i - 3]) % md;
        }
        return dp[N];
    }
}
