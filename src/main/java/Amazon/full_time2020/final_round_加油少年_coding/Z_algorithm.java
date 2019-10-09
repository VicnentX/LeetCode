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


/**
 *  这是z-algorithm的解释
 *
 *  The algorithm relies on a single, crucial invariant.
 *  Iterate over the letters in the string (index  from  to ) and maintain an interval
 *  which is the interval with maximum  such that
 *  and  is a prefix-substring (if no such interval exists, just let ).
 *  For , simply compute  and  by comparing  to .  is obtained during this process.
 *
 * Now, suppose the correct interval  for  and all of the  values up to .
 * Compute  and the new  by the following steps:
 *
 * If , then there does not exist a prefix-substring of  that starts before
 * and ends at or after . If such a substring existed,
 * would have been the interval for that substring rather than its current value.
 * Thus "reset" and compute a new  by comparing  to  and get  at the same time ().
 *
 * Otherwise, , so the current  extends at least to . Let .
 * It is known that  because  matches  for at least  characters
 * (they are in the  interval which is known to be a prefix-substring).
 *
 * If , then there is no longer prefix-substring starting at
 * (or else  would be larger), meaning  and  stays the same.
 * The latter is true because  only changes
 * if there is a prefix-substring starting at  that extends beyond ,
 * which is not the case here.
 *
 * If , then it is possible for  to match  for more than  characters (i.e. past position ).
 * Thus, there's a need to update  by setting  and matching from  forward to obtain the new .
 * Again,  is obtained during this process.
 *
 * The process computes all of the  values in a single pass over the string,
 * so we're done. Correctness is inherent in the algorithm and is pretty intuitively clear.
 *
 * Time Complexity
 *
 * The algorithm runs in  time.
 * Characters are never compared at positions less than 2 * n ,
 * and every time a match is found,  is increased by one, so there are at most 2 * n comparisons.
 */


/**
 * 这个是简单一些的解释 就是z-box【L，R】
 *
 * The idea is to maintain an interval [L, R] which is the interval with max R
 * such that [L,R] is prefix substring (substring which is also prefix).
 *
 * Steps for maintaining this interval are as follows –
 *
 * 1) If i > R then there is no prefix substring that starts before i and
 *    ends after i, so we reset L and R and compute new [L,R] by comparing
 *    str[0..] to str[i..] and get Z[i] (= R-L+1).
 *
 * 2) If i <= R then let K = i-L,  now Z[i] >= min(Z[K], R-i+1)  because
 *    str[i..] matches with str[K..] for atleast R-i+1 characters (they are in
 *    [L,R] interval which we know is a prefix substring).
 *    Now two sub cases arise –
 *       a) If Z[K] < R-i+1  then there is no prefix substring starting at
 *          str[i] (otherwise Z[K] would be larger)  so  Z[i] = Z[K]  and
 *          interval [L,R] remains same.
 *       b) If Z[K] >= R-i+1 then it is possible to extend the [L,R] interval
 *          thus we will set L as i and start matching from str[R]  onwards  and
 *          get new R then we will update interval [L,R] and calculate Z[i] (=R-L+1).
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
