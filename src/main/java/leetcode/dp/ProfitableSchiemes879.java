package leetcode.dp;

/*
There are G people in a gang, and a list of various crimes they could commit.

The i-th crime generates a profit[i] and requires group[i] gang members to participate.

If a gang member participates in one crime, that member can't participate in another crime.

Let's call a profitable scheme any subset of these crimes that generates at least P profit, and the total number of gang members participating in that subset of crimes is at most G.

How many schemes can be chosen?  Since the answer may be very large, return it modulo 10^9 + 7.



Example 1:

Input: G = 5, P = 3, group = [2,2], profit = [2,3]
Output: 2
Explanation:
To make a profit of at least 3, the gang could either commit crimes 0 and 1, or just crime 1.
In total, there are 2 schemes.
Example 2:

Input: G = 10, P = 5, group = [2,3,5], profit = [6,7,8]
Output: 7
Explanation:
To make a profit of at least 5, the gang could commit any crimes, as long as they commit one.
There are 7 possible schemes: (0), (1), (2), (0,1), (0,2), (1,2), and (0,1,2).


Note:

1 <= G <= 100
0 <= P <= 100
1 <= group[i] <= 100
0 <= profit[i] <= 100
1 <= group.length = profit.length <= 100
 */

public class ProfitableSchiemes879 {
    public int profitableSchemes(int G, int P, int[] group, int[] profit) {
        if(group == null || profit == null || group.length == 0 || profit.length == 0 || G == 0) return 0;
        //dp[k][i][j] k = task , i = profit , j = person
        //int g = group[k - 1]
        //int p = profit[k - 1];
        //dp[k][i][j] = dp[k - 1][i][j] + dp[k - 1][max(0 , i - p)][j - g];if j - g < 0 the later part dp[k - 1][max(0 , i - p)][j - g] = 0;
        int n = group.length;
        int[][][] dp = new int[n + 1][P + 1][G + 1];
        dp[0][0][0] = 1;
        for(int k = 1 ; k <= n ; ++k){
            int p = profit[k - 1];
            int g = group[k - 1];
            for(int i = 0 ; i < P + 1 ; ++i){
                for(int j = 0 ; j < G + 1 ; ++j){
                    dp[k][i][j] = (dp[k - 1][i][j] + (j < g ? 0 : dp[k - 1][Math.max(0 , i - p)][j - g])) % 1000000007;
                }
            }
        }
        int ret = 0;
        for(int i = 0 ; i < G + 1 ; ++i){
            ret = (ret + dp[n][P][i]) % 1000000007;
        }
        return ret ;
    }
}
