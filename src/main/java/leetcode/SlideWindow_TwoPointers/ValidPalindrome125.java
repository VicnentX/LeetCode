package leetcode.SlideWindow_TwoPointers;
/*
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

Note: For the purpose of this problem, we define empty string as valid palindrome.

Example 1:

Input: "A man, a plan, a canal: Panama"
Output: true
Example 2:

Input: "race a car"
Output: false
Seen this question in a real interview before?

 */

/**
 * alphanumeric
 */

public class ValidPalindrome125 {
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        if (s == null || s.length() == 0) return true;
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            while (i < j && !Character.isLetter(s.charAt(i)) && !Character.isDigit(s.charAt(i))) ++i;
            while (i < j && !Character.isLetter(s.charAt(j)) && !Character.isDigit(s.charAt(j))) --j;
            if (s.charAt(i) != s.charAt(j)) return false;
            ++i;
            --j;
        }
        return true;
    }
}
