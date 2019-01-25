package leetcode.backtracking_TrieNode;

/*
Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form.

Example 1:

Input: "aabb"
Output: ["abba", "baab"]
Example 2:

Input: "abc"
Output: []
Seen this question in a real interview before?

 */

import java.util.*;

public class PalindromePermutationII267 {
    public List<String> generatePalindromes(String s) {
        List<String> ret = new ArrayList<>();
        Set<Character> set = new HashSet<>();
        StringBuilder input = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (!set.add(c)) {
                input.append(c);
                set.remove(c);
            }
        }
        System.out.println(input);
        if (set.size() > 1) return ret;
        Character mid = 'a';
        if (set.size() == 1) {
            for (char c : set) {
                mid = c;
            }
        }
        System.out.println(mid);
        System.out.println(set.size());
        Set<Character>[] visited = new HashSet[input.length()];
        for (int i = 0 ; i < input.length() ; ++i) {
            visited[i] = new HashSet<>();
        }
        dfs (new StringBuilder() , input , 0 , input.length() ,  visited , new int[input.length()] , set.size() , mid , ret);
        return ret;
    }

    private void dfs (StringBuilder path , StringBuilder input , int level , int n , Set[] visited , int[] indexVisited , int size , char mid ,  List<String> ret) {
        if (level == n) {
            StringBuilder tail = new StringBuilder(path);
            tail.reverse();
            if (size == 1) {
                path.append(mid).append(tail);
                ret.add(new String(path));
                path.setLength(path.length() - 1 - tail.length());
            } else {
                path.append(tail);
                ret.add(new String(path));
                path.setLength(path.length() - tail.length());
            }
            return ;
        }

        for (int i = 0 ; i < n ; ++i) {
            if (indexVisited[i] == 0 && !visited[level].contains(input.charAt(i))) {
                visited[level].add(input.charAt(i));
                indexVisited[i] = 1;
                path.append(input.charAt(i));
                dfs(path , input , level + 1 , n  , visited , indexVisited ,  size , mid , ret);
                if (level < n - 1) visited[level + 1].clear();
                indexVisited[i] = 0;
                path.deleteCharAt(path.length() - 1);
            }
        }
    }

    public static void main (String[] args) {
        PalindromePermutationII267 pp = new PalindromePermutationII267();
        System.out.println(pp.generatePalindromes("asdffds"));
    }
}
