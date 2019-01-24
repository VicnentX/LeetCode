package leetcode.backtracking_TrieNode;

/*
Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

Example:

Input: "aab"
Output:
[
  ["aa","b"],
  ["a","a","b"]
]
 */

import java.util.*;
public class PalindromePartitioning131 {
    public List<List<String>> partition(String s) {
        List<List<String>> ret = new ArrayList<>();
        if (s.length() == 0) return ret;
        dfs (new ArrayList<>() , s , ret);
        return ret;
    }

    private void dfs (List<String> path , String s , List<List<String>> ret) {
        if (s.isEmpty()) {
            ret.add(new ArrayList<>(path));
            return ;
        }

        for (int i = 0; i < s.length() ; ++i) {
            if (isPalindrome(s , i)) {
                path.add(s.substring(0 , i + 1));
                dfs (path , s.substring(i + 1) , ret);
                path.remove(path.size() - 1);
            }
        }
    }

    private boolean isPalindrome (String s , int end) {
        int start = 0;
        while (end > start) {
            if (s.charAt(start) != s.charAt(end)) return false;
            --end;
            ++start;
        }
        return true;
    }
}
