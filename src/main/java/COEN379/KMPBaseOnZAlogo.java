package COEN379;


public class KMPBaseOnZAlogo {
    public int[] compute_k (String s) {
        int[] k = new int[s.length()];
        int n = s.length();
        int[] z = computeZ(s);
        k[0] = 0;
        for (int i = n - 1 ; i > 0 ; --i) {
            k[i + z[i] - 1] = z[i];
        }
        return k;
    }

    public int[] computeZ (String s) {
        int n = s.length();
        int[] ret = new int[n];
        int l = 0;
        int r = 0;

        for (int i = 1 ; i < n ; ++i) {
            int j = 0;
            if (i > r) {//case 1
                for (j = 0 ; i + j < n && s.charAt(j) == s.charAt(i + j) ; ++j) {
                    //do nothing
                }
                //update l r
                r = i + j - 1;
                l = i;
                ret[i] = r - l + 1;
            } else {
                if (i + ret[i - l] - 1 < r) { //case 2
                    ret[i] = ret[i - l];
                } else { //case 3
                    for (j = i + ret[i - l] - 1 ; j < n && s.charAt(j) == s.charAt(j - i) ; ++j) {
                        //do nothing
                    }
                    //update l r
                    r = j - 1;
                    l = i;
                    ret[i] = r - l + 1;
                }
            }
        }

        return ret;
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
                    System.out.println(s - i);
                }
                i = k[i - 1]; //shift y the longest suffix , 这个会越来越小 每次覆盖之前的
            }
        }
    }
}
