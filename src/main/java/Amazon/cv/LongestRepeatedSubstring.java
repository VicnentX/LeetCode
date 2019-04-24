package Amazon.cv;

public class LongestRepeatedSubstring {
    private int index = -1;
    private int maxLen = -1;
    public String findLongestSub (String s) {
        //get the suffix of input s
        //On2
        int n = s.length();
        String[] patterns = new String[n];
        for (int i = n - 1 ; i >= 0 ; --i) {
            patterns[i] = s.substring(i);
        }

        //calculate the z-value according to every pattern
        // and at some time find out the longest substring beginning index
        //On2
        for (int i = 0 ; i < n ; ++i) {
            int[] z = getZarr(patterns[i]);
            //find temp longest substring index
            for (int j = 0 ; j < z.length ; ++j) {
                if (z[j] > maxLen) {
                    index = j + i;
                    maxLen = z[j];
                }
            }
        }

        return s.substring(index , index + maxLen);
    }

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
        LongestRepeatedSubstring lrs = new LongestRepeatedSubstring();
        System.out.println(lrs.findLongestSub("I like apple, I like banana."));
    }
}
