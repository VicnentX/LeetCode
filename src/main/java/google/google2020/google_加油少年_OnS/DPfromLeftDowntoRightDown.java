package google.google2020.google_加油少年_OnS;

/*
貌似是个高频题，之前地理看到过。
给了2d grid的宽度和高度， 找从左下到右下有几种方法，
规定只能往右，右上，右下三个方向走。 follow up能不能优化空间。
 */

public class DPfromLeftDowntoRightDown {
    public int solve(final int M, final int N) {

        int[] dp = new int[M + 2];
        dp[M] = 1;

        for (int i = 1; i < N; ++i) {
            int[] temp = new int[M + 2];
            for (int j = 1; j <= M; ++j) {
                temp[j] = dp[j -1] + dp[j] + dp[j + 1];
            }
            dp = temp;
        }

        return dp[M];
    }

    public static void main(String[] args) {
        DPfromLeftDowntoRightDown dPfromLeftDowntoRightDown = new DPfromLeftDowntoRightDown();
        System.out.println(dPfromLeftDowntoRightDown.solve(11,11));
    }
}
