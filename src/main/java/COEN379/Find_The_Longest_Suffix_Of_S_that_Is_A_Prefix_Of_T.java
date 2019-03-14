package COEN379;

public class Find_The_Longest_Suffix_Of_S_that_Is_A_Prefix_Of_T {
    public int FindLongestSuffix(String s , String t) {
        int n = s.length();
        int m = t.length();
        int[] k = compute_k(s + '#' + t);
        int longest = 0;
        for (int i = m + 1 ; i < n + m + 1 ; ++i) {
            longest = Math.max(longest , k[i]);
        }
        return longest;
    }

    private int[] compute_k (String s) {
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

    public static void main (String[] args) {
        Find_The_Longest_Suffix_Of_S_that_Is_A_Prefix_Of_T fst
                = new Find_The_Longest_Suffix_Of_S_that_Is_A_Prefix_Of_T();
        System.out.println(fst.FindLongestSuffix("abcde" , "abc"));
    }
}
