package google.google2020.google_加油少年_VO;

/*
牛牛有两个字符串（可能包含空格）,牛牛想找出其中最长的公共连续子串,希望你能帮助他,并输出其长度。
输入描述:
输入为两行字符串（可能包含空格），长度均小于等于50
输出描述:
输出为一个整数，表示最长公共连续子串的长度

输入例子:
abcde

abgde

输出例子:
2

解题思路：

这题其实是动态规划的变形经典题型，应用动态规划的思想，创建一个二维数组dp[n][n]，
其中dp[i][j]，表示取到s1[i]和取到s2[j]时的最大连续子串长度。如果s1[i]等于s2[j]，则dp[i-1][j-1]等于取到s1[i-1]和取到s2[j-1]时的最大连续子串长度加1，即

dp[i][j]=dp[i-1][j-1]+1。
————————————————
版权声明：本文为CSDN博主「倾城一笑stu」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/zls986992484/article/details/69863710
 */

public class LongestCommonSubstring2 {

    public int getLongest(String s, String t) {
        int max = 0;
        final int M = s.length();
        final int N = t.length();
        int[][] dp = new int[M][N];
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (s.charAt(i) == t.charAt(j)) {
                    if (i > 0 && j > 0) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = 1;
                    }
                    if (dp[i][j] > max) {
                        max = dp[i][j];
                    }
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        LongestCommonSubstring2 longestCommonSubstring2 = new LongestCommonSubstring2();
        System.out.println(longestCommonSubstring2.getLongest("asddf", "asbdf"));
    }

}
