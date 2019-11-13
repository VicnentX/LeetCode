package google.google2020.google_加油少年_OnS;

//https://blog.csdn.net/sinat_35261315/article/details/78678961

public class ShortestDistanceBetweenTwoWords {

    public int solve(String s, String t) {
        final int M = s.length();
        final int N = t.length();
        int[][] dp = new int[M + 1][N + 1];
        for (int i = 0; i <= M; ++i) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= N; ++i) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= M; ++i) {
            for (int j = 1; j <= N; ++j) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }

        return dp[M][N];
    }

    public static void main(String[] args) {
        ShortestDistanceBetweenTwoWords shortestDistanceBetweenTwoWords = new ShortestDistanceBetweenTwoWords();
        //2
        System.out.println(shortestDistanceBetweenTwoWords.solve("ert","egd"));
    }



}
