package leetcode.HARD;

/*
S and T are strings composed of lowercase letters. In S, no letter occurs more than once.

S was sorted in some custom order previously. We want to permute the characters of T so that they match the order that S was sorted. More specifically, if x occurs before y in S, then x should occur before y in the returned string.

Return any permutation of T (as a string) that satisfies this property.

Example :
Input:
S = "cba"
T = "abcd"
Output: "cbad"
Explanation:
"a", "b", "c" appear in S, so the order of "a", "b", "c" should be "c", "b", and "a".
Since "d" does not appear in S, it can be at any position in T. "dcba", "cdba", "cbda" are also valid outputs.


Note:

S has length at most 26, and no character is repeated in S.
T has length at most 200.
S and T consist of lowercase letters only.
 */


import java.util.*;
public class CustomSortString791 {
    public String customSortString(String S, String T) {

        Character[] arr = new Character[T.length()];
        for (int i = 0; i < T.length(); i++) {
            arr[i] = T.charAt(i);
        }
        Arrays.sort(arr, new Comparator<Character>() {
            // public int compare(Character a, Character b) {
            //     if (S.contains("" + a) && !S.contains("" + b)) {
            //         return -1;
            //     } else if (!S.contains("" + a) && S.contains("" + b)) {
            //         return 1;
            //     } else if (a == b) {
            //         return 0;
            //     } else if (S.contains("" + a) && S.contains("" + b)) {
            //         if (S.indexOf("" + a) > S.indexOf("" + b)) {
            //             return 1;
            //         } else {
            //             return -1;
            //         }
            //     }
            //     return 0;
            // }
            //_________________
            //@Override
            public int compare(Character c1, Character c2) {
                return S.indexOf(c1)<S.indexOf(c2)? -1:S.indexOf(c1) == S.indexOf(c2)? 0:1;
            }
        });
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            char ch = arr[i];
            sb.append(ch);
        }
        return sb.toString();
    }
}
