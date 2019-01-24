package leetcode.backtracking_TrieNode;

/*
Design a data structure that supports the following two operations:

void addWord(word)
bool search(word)
search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

Example:

addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true
Note:
You may assume that all words are consist of lowercase letters a-z.
 */

public class AddandSearchWordDatastructuredesign211 {
    public class TrieNode {
        TrieNode[] next = new TrieNode[26];
        boolean word;
    }

    TrieNode root;

    /** Initialize your data structure here. */
    public AddandSearchWordDatastructuredesign211() {
        root = new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode p = root;
        for (char c : word.toCharArray()) {
            int i = c - 'a';
            if (p.next[i] == null) p.next[i] = new TrieNode();
            p = p.next[i];
        }
        p.word = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return dfs (0 , word.length() , word , root);
    }
    private boolean dfs (int i  , int n , String s , TrieNode p) {
        if (i == n) {
            return p.word;
        }

        if (s.charAt(i) == '.') {
            for (int j = 0 ; j < 26 ; ++j) {
                if (p.next[j] != null) {
                    if (dfs (i + 1 , n , s , p.next[j])) {
                        return true;
                    }
                }
            }
        } else {
            if (p.next[s.charAt(i) - 'a'] != null) {
                if (dfs (i + 1 , n , s , p.next[s.charAt(i) - 'a'])) {
                    return true;
                }
            }
        }

        return false;
    }
}
