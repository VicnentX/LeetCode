package leetcode.backtracking;

/*
Additive number is a string whose digits can form additive sequence.

A valid additive sequence should contain at least three numbers. Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.

Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.

Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.

Example 1:

Input: "112358"
Output: true
Explanation: The digits can form an additive sequence: 1, 1, 2, 3, 5, 8.
             1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
Example 2:

Input: "199100199"
Output: true
Explanation: The additive sequence is: 1, 99, 100, 199.
             1 + 99 = 100, 99 + 100 = 199
Follow up:
How would you handle overflow for very large input integers?
 */

public class AdditiveNumber306 {
    public boolean isAdditiveNumber(String s) {
        return dfs (-1 , -1 , -1 , 0 , s , 0);
    }

    private boolean dfs (long pre1 , long pre2 , long cur , int start , String s , int level) {
        if (level <= 1) {
            if (start == s.length()) return false;
            if (s.charAt(start) == '0') {
                return dfs(pre2 , 0 , 0 + pre2 , start + 1 , s , level + 1);
            } else {
                for (int len = 1 ; len <= s.length() - start; ++len) {
                    long tem = Long.valueOf(s.substring(start , start + len));
                    if (dfs(pre2 , tem , pre2 + tem , start + len , s , level + 1)) {
                        return true;
                    }
                }
                return false;
            }
        }
        if (level >= 3 && start == s.length()) return true;

        if (s.startsWith(String.valueOf(cur) , start)) {
            return dfs (pre2 , cur , pre2 + cur , start + String.valueOf(cur).length() , s , level + 1);
        } else {
            return false;
        }
    }

    public static void main (String[] args) {
        AdditiveNumber306 an = new AdditiveNumber306();
        System.out.println(an.isAdditiveNumber("112358"));
    }
}
