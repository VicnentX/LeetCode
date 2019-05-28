package COEN379;

public class IfSIsConcatenationOfSomeT {
    public boolean IsConcatenation(String s) {
        int[] z = getZarr(s);
        int n = z.length;
        int[] concate = new int[n];
        for (int i = 0 ; i < n ; ++i) {
            concate[i] = i;
        }
        for (int i = 1 ; i < n ; ++i) {
            if (z[i] < concate[i]) {
                concate[i] = 0;
            } else { //z[i] >= concate[i]
                if (concate[i] + i == n) {
                    return true;
                }
                concate[i + concate[i]] = concate[i];
            }
        }
        return false;
    }

    public boolean IsIsConcatenation2(String s) {
        int[] z = getZarr(s);
        int n = z.length;
        for (int i = 1 ; i < n ; ++i) {
            if (z[i] + i == n && z[i] % i == 0) {
                return true;
            }
        }
        return false;
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
        IfSIsConcatenationOfSomeT concate = new IfSIsConcatenationOfSomeT();
        System.out.println("isCon result: ");
        System.out.println(concate.IsConcatenation("abaabaabaabaaba"));
        System.out.println(concate.IsConcatenation("aaa"));
        System.out.println(concate.IsConcatenation("aaaa"));

        System.out.println("isisCon2 result: ");
        System.out.println(concate.IsIsConcatenation2("abaabaabaabaaba"));
        System.out.println(concate.IsIsConcatenation2("aaa"));
        System.out.println(concate.IsIsConcatenation2("aaaa"));

        System.out.println("___________________________");
        System.out.println("Z value of 'abaabaabaabc' as below : ");
        for (int k : concate.getZarr("abaabaabaabc")) {
            System.out.print(k + " ");
        }
        System.out.println();
        System.out.println(concate.IsConcatenation("abaabaabaabc"));
    }
}
