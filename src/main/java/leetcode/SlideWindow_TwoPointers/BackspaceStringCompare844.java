package leetcode.SlideWindow_TwoPointers;

/*
Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.

Example 1:

Input: S = "ab#c", T = "ad#c"
Output: true
Explanation: Both S and T become "ac".
Example 2:

Input: S = "ab##", T = "c#d#"
Output: true
Explanation: Both S and T become "".
Example 3:

Input: S = "a##c", T = "#a#c"
Output: true
Explanation: Both S and T become "c".
Example 4:

Input: S = "a#c", T = "b"
Output: false
Explanation: S becomes "c" while T becomes "b".
Note:

1 <= S.length <= 200
1 <= T.length <= 200
S and T only contain lowercase letters and '#' characters.
Follow up:

Can you solve it in O(N) time and O(1) space?
 */

/**
 * scan from the end to begin
 * find the numbers which are not deleted by # in each string one by one
 * compare them
 *
 */

public class BackspaceStringCompare844 {
    public boolean backspaceCompare(String s, String t) {
        for (int i = s.length() - 1, j = t.length() - 1; ; --i , --j) {
            for (int b = 0 ; i >= 0 && (b > 0 || s.charAt(i) == '#'); --i) {
                b += s.charAt(i) == '#' ? 1 : -1;
            }
            for (int b = 0 ; j >= 0 && (b > 0 || t.charAt(j) == '#'); --j) {
                b += t.charAt(j) == '#' ? 1 : -1;
            }
            if (i < 0 || j < 0 || s.charAt(i) != t.charAt(j)) {
                return i == -1 && j == -1;
            }
        }
    }

    public static void main (String[] args) {
        BackspaceStringCompare844 bs = new BackspaceStringCompare844();
        System.out.println(bs.backspaceCompare("w##" , "ryt###"));
        System.out.println(bs.backspaceCompare("w##" , "ryt##"));
    }
}
