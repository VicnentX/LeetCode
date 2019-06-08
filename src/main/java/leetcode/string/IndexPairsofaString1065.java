package leetcode.string;

/*
Given a text string and words (a list of strings), return all index pairs [i, j] so that the substring text[i]...text[j] is in the list of words.



Example 1:

Input: text = "thestoryofleetcodeandme", words = ["story","fleet","leetcode"]
Output: [[3,7],[9,13],[10,17]]
Example 2:

Input: text = "ababa", words = ["aba","ab"]
Output: [[0,1],[0,2],[2,3],[2,4]]
Explanation:
Notice that matches can overlap, see "aba" is found in [0,2] and [2,4].


Note:

All strings contains only lowercase English letters.
It's guaranteed that all strings in words are different.
1 <= text.length <= 100
1 <= words.length <= 20
1 <= words[i].length <= 50
Return the pairs [i,j] in sorted order (i.e. sort them by their first coordinate in case of ties sort them by their second coordinate).
 */

import java.util.*;

public class IndexPairsofaString1065 {
    public int[][] indexPairs(String text, String[] words) {
        List<int[]> ret = new ArrayList<>();
        Arrays.sort(words , (a,b) -> a.length() - b.length());
        int n = text.length();
        for (int i = 0 ; i < text.length() ; ++i) {
            OUT:
            for (String word : words) {
                int len_word = word.length();
                if (len_word <= n - i) {
                    for (int j = 0 ; j < len_word ; ++j) {
                        if (word.charAt(j) != text.charAt(j + i)) {
                            continue OUT;
                        }
                    }
                    ret.add(new int[] {i, i + len_word - 1});
                }
            }
        }
        int[][] result = new int[ret.size()][2];
        int index = 0;
        for (int[] pair : ret) {
            result[index][0] = pair[0];
            result[index][1] = pair[1];
            index++;
        }
        return result;
    }

    public int[][] indexPairs_with_z_algorithm(String text, String[] words) {

        List<int[]> ret = new ArrayList<>();

        for (String word : words) {
            String s = word + "#" + text;
            int[] z = getZarr(s);
            int n = text.length();
            int m = word.length();
            for (int i = m + 1  ; i <= n + 1 ; ++i) {
                if (z[i] == m) {
                    ret.add(new int[] {i - m - 1, i - m - 1 + m - 1});
                    //System.out.println("index could be " + (i - m - 1) + " ");
                }
            }
        }

        Collections.sort(ret, (a,b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int[][] result = new int[ret.size()][2];
        int index = 0;
        for (int[] pair : ret) {
            result[index][0] = pair[0];
            result[index][1] = pair[1];
            index++;
        }
        return result;
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
