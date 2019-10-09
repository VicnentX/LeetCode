package Amazon.full_time2020.final_round_加油少年_coding;

/*
Implement a trie with insert, search, and startsWith methods.

Example:

Trie trie = new Trie();

trie.insert("apple");
trie.search("apple");   // returns true
trie.search("app");     // returns false
trie.startsWith("app"); // returns true
trie.insert("app");
trie.search("app");     // returns true
Note:

You may assume that all inputs are consist of lowercase letters a-z.
All inputs are guaranteed to be non-empty strings.
 */

class TrieNode {
    public char val;
    public boolean isWord;
    public TrieNode[] children = new TrieNode[26];
    public TrieNode() {

    }
    TrieNode(char c) {
        TrieNode node = new TrieNode();
        node.val = c;
    }
}


public class ImplementTriePrefixTree208 {

    private TrieNode root;

    /** Initialize your data structure here. */
    public ImplementTriePrefixTree208() {
        root = new TrieNode();
        root.val = ' ';
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode curState = root;
        for (char c: word.toCharArray()) {
            if (curState.children[c - 'a'] == null) {
                curState.children[c - 'a'] = new TrieNode(c);
            }
            curState = curState.children[c - 'a'];
        }
        curState.isWord = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode curState = root;
        for (char c: word.toCharArray()) {
            if (curState.children[c - 'a'] == null) return false;
            curState = curState.children[c - 'a'];
        }
        return curState.isWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode curState = root;
        for (char c: prefix.toCharArray()) {
            if (curState.children[c - 'a'] == null) return false;
            curState = curState.children[c - 'a'];
        }
        return true;
    }

}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
