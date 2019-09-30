package NextJump;

/**
 * 这题就是s每次从第一个t或者最后一个t， 将其拿去，
 * 看做多可以拿几次
 *
 * 我的方法就是先用z algorithm 算出来每个index的longest prefix （是On的算法）
 * 然后就看longest prefix = t。length 就可能是
 * 因为这题拿掉就不能重复用了，
 * 所以当=t.length的时候就将index往后移动t的长度 否则就移动1。。。。
 * 看我总共可以得到几次 答案就是这数字了
 *
 */

public class DeletingSubstring {
    public int maxMoves(String s, String t) {
        final int TARGET_LENGTH = t.length();
        final int N = (t + "#" + s).length();
        int[] k = compute_k(t + "#" + s);
        int i = 0, cnt = 0;

        while (i < N) {
            if (k[i] == TARGET_LENGTH) {
                cnt++;
                i += TARGET_LENGTH;
            } else {
                i++;
            }
        }

        return cnt;
    }

    public int[] compute_k (String s) {
        int[] k = new int[s.length()];
        int n = s.length();
        int[] z = getZarr(s);
        k[0] = 0;
        for (int i = n - 1 ; i > 0 ; --i) {
            k[i + z[i] - 1] = z[i];
        }
        return k;
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

    public static void main(String[] args) {
        DeletingSubstring deletingSubstring = new DeletingSubstring();
        System.out.println(deletingSubstring.maxMoves("bcbbc", "b"));
    }
}
