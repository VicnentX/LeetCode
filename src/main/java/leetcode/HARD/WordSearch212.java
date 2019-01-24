package leetcode.HARD;

/*
Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

Example:

Input:
words = ["oath","pea","eat","rain"] and board =
[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]

Output: ["eat","oath"]
Note:
You may assume that all inputs are consist of lowercase letters a-z.
 */

import java.util.*;

public class WordSearch212 {
    public class TrieNode {
        TrieNode[] next = new TrieNode[26];
        String word;
    }
    public List<String> findWords(char[][] board, String[] words) {
        List<String> ret = new ArrayList<>();
        if (board.length == 0 || board[0].length == 0) return ret;
        int m = board.length;
        int n = board[0].length;
        TrieNode root = generateTree(words);
        for (int i = 0 ; i < m ; ++i) {
            for (int j = 0 ; j < n ; ++j) {
                dfs (i , j , m , n , root , board , ret);
            }
        }
        return ret;
    }

    private void dfs (int i , int j , int m , int n , TrieNode p , char[][] b , List<String> ret) {
        if (i < 0 || i >= m || j < 0 || j >= n || b[i][j] == '#' || p.next[b[i][j] - 'a'] == null) {
            return ;
        }
        char c = b[i][j];
        p = p.next[c - 'a'];
        if (p.word != null) {
            ret.add(p.word);
            p.word = null;
        }
        b[i][j] = '#';
        dfs (i + 1 , j , m , n , p , b , ret);
        dfs (i - 1 , j , m , n , p , b , ret);
        dfs (i , j + 1 , m , n , p , b , ret);
        dfs (i , j - 1 , m , n , p , b , ret);
        b[i][j] = c;

    }

    private TrieNode generateTree(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode p = root;
            for (char c : word.toCharArray()) {
                int i = c - 'a';
                if (p.next[i] == null) p.next[i] = new TrieNode();
                p = p.next[i];
            }
            p.word = word;
        }
        return root;
    }
}
