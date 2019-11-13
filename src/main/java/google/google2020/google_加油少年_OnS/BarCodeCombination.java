package google.google2020.google_加油少年_OnS;

/*
第三题我的思路： 建立两个int[] white 和 black 长度都为50-google 1point3acres white[i] 和 black[i] 分别表示 第 i 位 涂成 white or black 的方法有多少种 white[0] = 1;black[0] = 0; white1 = 1;black1 = 1;. visit 1point3acres for more. white2 = 2;black2 = 2;.1point3acres网

white[i] = black[i-1]+black[i-2]+black[i-3]; black[i] = white[i-1]+white[i-2]+white[i-3]; 最后返回white[49]; 因为一个块（i）如果涂成白色的话， 从左向右数， 它要么是第一个白色，(i-1)是黑色 要么是第二个白色，（i-2）是黑色， （i-1）是白色. Waral 博客有更多文章, 要么是第三个白色，（i-3）是黑色，（i-2）(i-1)都是是白色

dp[j] 长度为j且两侧颜色相同。= dp[j - 2] + 2 * dp[j - 3] + dp[j - 4] + 2 * dp[j - 5] + dp[j - 6], dp1 = dp2 = 1, dp3 = 2

i表示当前位置，j表示颜色，0是白色，1是黑色 初始化dp[0][0] = 0，dp[1][0] = 1, dp[2][0] = 1, dp0 = 0, dp1 = 0, dp2 = 1 dp[i][0] = dpi - 1 + dpi - 2 + dpi - 3 dpi = dp[i - 1][0] + dp[i - 2][0] + dp[i - 3][0] 最后返回dp[n][0]

关于第三题，维护一个50×6的dp。50是barcode的长度，6只一共只有六种结尾的状态，即：1白， 2连白， 3连白， 1黑， 2连黑， 3连黑。. 1point 3acres 论坛 .本文原创自1point3acres论坛 int Solution::google(void) { vector<vector> dp (6, vector (50, 0)); dp[0][0] = 1;

int mod = 1e9 + 7;

for (size_t i = 1; i < dp[0].size(); ++i) {
    // end with 1 while.1point3acres网
    dp[0][i] = (dp[3][i - 1] + dp[4][i - 1] + dp[5][i - 1]) % mod;. 1point 3acres 论坛
    // end with 2 while
    dp[1][i] = (dp[0][i - 1]) % mod;. more info on 1point3acres
    // end with 3 while
    dp[2][i] = (dp[1][i - 1]) % mod;

    // end with 1 black
    dp[3][i] = (dp[0][i - 1] + dp[1][i - 1] + dp[2][i - 1]) % mod;
    // end with 2 black
    dp[4][i] = (dp[3][i - 1]) % mod;
    // end with 3 black
    dp[5][i] = (dp[4][i - 1]) % mod;
}
.1point3acres网 return dp[0].back() + dp1.back() + dp2.back();. 1point3acres } . 一亩-三分-地，独家发布 手动test了下。一下1代表黑，0代表白。 长度为1 所有可能：0 满足条件：0， 共计1种 长度为2 所有可能：00， 01 满足条件：00， 共计1种. Waral 博客有更多文章, 长度为3 所有可能：000，001，010，011 满足条件：000，010，共计2种.本文原创自1point3acres论坛 长度为4. 留学申请论坛-一亩三分地 所有可能：0000，0001，0010，0011，0100，0101，0110，0111 来源一亩.三分地论坛. 满足条件：0010，0100，0110，共计3种

请大家看看思路是否正确。谢谢。

第三题我觉得是DP没问题， DP[n] = DP[N - 2] + 2DP[N - 3] + 3DP[N-4] + 2DP[N - 5] + DP[N - 6]. 然后把1-6的情况都求出来就好了。。我也不知道求得对不对 1->1, 2->1,3->2,4->3,5->7,6->12然后开始往后面DP就好了 应该是个O[N]. 大体逻辑是你两边必须是两个白的开始，如果你两边只有两个白的，就是两边两个黑的的48个格子，如果两边有三个白的，就有两种情况每种都是两边都是黑的的47个格子依次类推。两边都是白的和两边都是黑的其实是一样的，所以每次直接递归就可以了。
 */

public class BarCodeCombination {
    /**
     *
     * @param n is n digits
     * @return
     */
    public int solve(int n) {
        //dp 分别存储 以一个白色 两个白色 三个白色 一个黑色 两个黑色 三个黑色 结尾的情况
        int[] dp = new int[6];
        dp[0] = dp[3] = 1;
        for (int i = 1; i < n; ++i) {
            int[] temp = new int[6];
            temp[0] = dp[3] + dp[4] + dp[5];
            temp[1] = dp[0];
            temp[2] = dp[1];
            temp[3] = dp[0] + dp[1] + dp[2];
            temp[4] = dp[3];
            temp[5] = dp[4];
            dp = temp;
        }

        //get result
        int ret = 0;
        for (int i: dp) {
            ret += i;
        }
        return ret;
    }

    public static void main(String[] args) {
        BarCodeCombination barCodeCombination = new BarCodeCombination();
        //2
        System.out.println(barCodeCombination.solve(1));
        //4
        System.out.println(barCodeCombination.solve(2));
        //14
        System.out.println(barCodeCombination.solve(4));
    }
}
