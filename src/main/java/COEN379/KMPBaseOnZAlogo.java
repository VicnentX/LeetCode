package COEN379;


public class KMPBaseOnZAlogo {
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



    public void kmp (String t , String p) {
        int[] k = compute_k(p); //preprocess
        int n = t.length();
        int m = p.length();
        int s = 0;
        int i = 0;

        while (s < n) {
            //compere
            while (i < m && p.charAt(i) == t.charAt(s)) {
                ++i;
                ++s;
            }
            //either complete match or found a mismatch
            if (i == 0) { //immediate mismatch
                ++s;
            } else {
                if ( i == m ) {
                    //complete match
                    System.out.print(s - i + " ");
                }
                i = k[i - 1]; //shift y the longest suffix , 这个会越来越小 每次覆盖之前的
            }
        }
    }

    public static void main (String[] args) {
        KMPBaseOnZAlogo kmp = new KMPBaseOnZAlogo();
        System.out.println("z-value is as below : ");
        for (int k : kmp.getZarr("MISSISSIPPI")) {
            System.out.print(k);
        }
        System.out.println("\n_________________________");
        System.out.println("k-value is as below : ");
        for (int k : kmp.getZarr("MISSISSIPPI")) {
            System.out.print(k);
        }
    }
}
