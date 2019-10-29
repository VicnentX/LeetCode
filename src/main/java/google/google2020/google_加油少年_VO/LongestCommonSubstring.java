package google.google2020.google_加油少年_VO;

/*
LZY20170708 发表于 2019-10-9 23:55
请问最长连续子字符串是longest common substring吗

类似吧， 举个例子，有个字符串‘abcdaaaacaaddddddd',
给一个字母’a', 那么最长的字符串就是‘aaaa'.
 */

public class LongestCommonSubstring {

    public String getSubstring(String p, String t) {
        String ret = "";
        String s = p + "#" + t;
        //O(1)
        int[] z = getZarr(s);
        int n = t.length();
        int m = p.length();

        //就是=pattern长度的时候才看后面能不能连起来 不能的就结束了，
        // 找后面一位，可以的话中间就都跳过了，所以每个点最多被access一次
        for (int i = m + 1  ; i <= n + 1 ; ++i) {
            if (z[i] < m) {
                if (z[i] > ret.length()) {
                    ret = s.substring(i, i + z[i]);
                }
            } else {
                int start = i;
                while (i <= n + 1 && z[i] == m) {
                    i = i + m;
                }
                if (i - start > ret.length()) {
                    ret = s.substring(start, i);
                    i--;
                }
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        LongestCommonSubstring longestCommonSubstring = new LongestCommonSubstring();
        //7
        System.out.println(longestCommonSubstring.getSubstring("d","abcdaaaacaaddddddd"));
        //9
        System.out.println(longestCommonSubstring.getSubstring("asd","asdfdasdjkjkasdasdasdjk"));
    }


    // Fills Z array for given string str[]
    private int[] getZarr(String str) {

        int[] Z = new int[str.length()];
        int n = str.length();

        // [L,R] make a window which matches with
        // prefix of s
        int L = 0, R = 0;

        for (int i = 1; i < n; ++i) {

            // if i>R nothing matches so we will calculate.
            // Z[i] using naive way.
            if (i > R) {

                L = R = i;

                // R-L = 0 in starting, so it will start
                // checking from 0'th index. For example,
                // for "ababab" and i = 1, the value of R
                // remains 0 and Z[i] becomes 0. For string
                // "aaaaaa" and i = 1, Z[i] and R become 5

                while (R < n && str.charAt(R - L) == str.charAt(R))
                    R++;

                Z[i] = R - L;
                R--;

            } else {

                // k = i-L so k corresponds to number which
                // matches in [L,R] interval.
                int k = i - L;

                // if Z[k] is less than remaining interval
                // then Z[i] will be equal to Z[k].
                // For example, str = "ababab", i = 3, R = 5
                // and L = 2
                if (Z[k] < R - i + 1)
                    Z[i] = Z[k];

                    // For example str = "aaaaaa" and i = 2, R is 5,
                    // L is 0
                else {


                    // else start from R and check manually
                    L = i;
                    while (R < n && str.charAt(R - L) == str.charAt(R))
                        R++;

                    Z[i] = R - L;
                    R--;
                }
            }
        }
        return Z;
    }

}
