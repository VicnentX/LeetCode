package Amazon.cv;

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



public class ImplementTrie_PrefixTree208 {

    //这种自定义类的array 若是什么都没用放的话，，初始值是null！！！！！

    private class TrieNode {
        char val;
        boolean isWord;
        TrieNode[] children = new TrieNode[26];
        TrieNode () {

        }
        TrieNode (char c) {
            TrieNode node = new TrieNode();
            val = c;
        }
    }

    private TrieNode root;

    public ImplementTrie_PrefixTree208 () {
        root = new TrieNode(' ');
    }

    public void insert (String word) {
        TrieNode node = root;
        for (int i = 0 ; i < word.length() ; ++i) {
            char c = word.charAt(i);
            if (node.children[c-'a'] == null) {
                node.children[c - 'a'] = new TrieNode(c);
            }
            node = node.children[c - 'a'];
        }
        node.isWord = true;
    }

    public boolean search (String word) {
        TrieNode node = root;
        for (int i = 0 ; i < word.length() ; ++i) {
            char c = word.charAt(i);
            if (node.children[c - 'a'] == null) return false;
            node = node.children[c - 'a'];
        }
        return node.isWord;
    }

    public boolean startWith (String prefix) {
        TrieNode node = root;
        for (int i = 0 ; i < prefix.length() ; ++i) {
            char c = prefix.charAt(i);
            if (node.children[c - 'a'] == null) return false;
            node = node.children[c - 'a'];
        }
        return true;
    }


    public static void main (String[] args) {
        ImplementTrie_PrefixTree208 it = new ImplementTrie_PrefixTree208();
        it.insert("apple");
        System.out.println(it.search("apple"));
        System.out.println(it.search("app"));
        System.out.println( it.startWith("app"));
        it.insert("app");
        System.out.println(it.search("app"));
    }
}
