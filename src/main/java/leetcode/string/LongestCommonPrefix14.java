package leetcode.string;

/*
Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

Example 1:

Input: ["flower","flow","flight"]
Output: "fl"
Example 2:

Input: ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
Note:

All given inputs are in lowercase letters a-z.
 */
public class LongestCommonPrefix14 {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String prefix = strs[0];
        int i = 1;
        while (i < strs.length) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0 , prefix.length() - 1);
            }
            ++i;
        }
        return prefix;
    }

    public static void main (String[] args) {
        LongestCommonPrefix14 lc = new LongestCommonPrefix14();
        System.out.println(lc.longestCommonPrefix(new String[] {"dog","racecar","car"}));
        System.out.println(lc.longestCommonPrefix(new String[] {"flower","flow","flight"}));
    }
}
