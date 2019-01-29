package leetcode.string;

/*
Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.

If possible, output any possible result.  If not possible, return the empty string.

Example 1:

Input: S = "aab"
Output: "aba"
Example 2:

Input: S = "aaab"
Output: ""
Note:

S will consist of lowercase letters and have length in range [1, 500].
 */

public class ReorganizeString767 {
    public String reorganizeString(String s) {
        if (s.length() == 0) return "";
        int n = s.length();
        int[] ch = new int[26];
        for (char c : s.toCharArray()) {
            ++ch[c - 'a'];
        }
        int index = 0;
        int max = 0;
        for (int i = 0 ; i < 26 ; ++i) {
            if (ch[i] > max) {
                index = i;
                max = ch[i];
            }
        }
        char most = (char)(index + 'a');

        if (max > Math.ceil(n / 2.0) ) return "";


        System.out.println("there must be a solution");

        StringBuilder ret = new StringBuilder();
        int residue = (n - max) - max + 1;

        OUT:
        while (residue > 0) {
            for (int i = 0 ; i < 26 ; ++i) {
                if (i != index && ch[i] > 0) {
                    ret.append((char)(i + 'a'));
                    --ch[i];
                    --residue;
                    if (residue == 0) break OUT;
                }
            }
        }

        int j = 0;
        ret.append(most);
        while (j < 26) {
            if (j != index && ch[j] > 0) {
                ret.append((char)(j + 'a')).append(most);
                --ch[j];
            } else {
                ++j;
            }
        }

        return ret.toString();
    }

    public static void main (String[] args) {
        ReorganizeString767 rs = new ReorganizeString767();
        System.out.println(rs.reorganizeString("abaaaaabbd"));
        //
        System.out.println(rs.reorganizeString("abaaaaabbdeds"));
    }
}
