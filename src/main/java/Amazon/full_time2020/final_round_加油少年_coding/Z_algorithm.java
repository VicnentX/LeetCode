package Amazon.full_time2020.final_round_加油少年_coding;

/*
一个log file， 若干敏感词，要求替换file中的敏感词。
 */

/**
 * given a stirng s,
 * Zi(s) = the length of the longest substring in s starting at the position i
 * that matches a prefix of s
 *
 * find the z values in time proportional to the length of s
 *
 *
 */

public class Z_algorithm {

    public void printAllMatchStartingIndex(String p, String t) {
        String s = p + "#" + t;
        int[] z = getZarr(s);
        final int N = t.length();
        final int M = p.length();

        for (int i = M + 1  ; i <= N + 1 ; ++i) {
            if (z[i] == M) {
                System.out.println("index could be " + (i - M - 1) + " ");
            }
        }

    }

    public int[] getZarr(String str) {
        int[] Z = new int[str.length()];
        int n = str.length();

        // [L,R] make a window which matches with
        // prefix of s
        int L = 0, R = 0;

        for (int i = 1; i < n; ++i) {

            if (i > R) {
                L = R = i;

                while (R < n && str.charAt(R - L) == str.charAt(R)) {
                    R++;
                }

                Z[i] = R - L;
                R--;
            } else {
                int k = i - L;

                if (Z[k] < R - i + 1) {
                    Z[i] = Z[k];
                } else {
                    L = i;
                    while (R < n && str.charAt(R - L) == str.charAt(R)) {
                        R++;
                    }
                    Z[i] = R - L;
                    R--;
                }
            }
        }
        return Z;
    }

    public static void main(String[] args) {
        Z_algorithm z_algorithm = new Z_algorithm();
        z_algorithm.printAllMatchStartingIndex("asd", "asdadsasd");
    }

}
