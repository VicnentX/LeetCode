package google.google2020.google_加油少年_ResidentVO;

/*
第二题是问当前被强调的word是不是dictionary中的单词，
可以使用已存在的boolean isDictionaryWord(String word){} 这个function
e.g.
dictionary中包含{“hello”,“abc”, “aabbccd”}
“helllllllo” -> True
“helo” -> False
同样是时间来不及，没有写code，跟面试官交流了思路之后就结束了。
 */

import java.util.HashSet;
import java.util.Set;

public class StrongWordInDict {

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

    private static TrieNode root;

    public StrongWordInDict () {
        root = new TrieNode(' ');
    }

    public void insert (String word) {
        TrieNode node = root;
        for (int i = 0 ; i < word.length() ; ++i) {
            char c = word.charAt(i);
            if (node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new TrieNode(c);
            }
            node = node.children[c - 'a'];
        }
        node.isWord = true;
    }



    public boolean isDictionaryWord(String word, Set<String> dict){
        //make trie
        for(String s: dict) {
            insert(s);
        }
        TrieNode node = root;
        boolean ret = false;
        for (int i = 0; i < word.length(); ++i) {

            boolean pre = ret;

            char c = word.charAt(i);
            int index = c - 'a';
            if (node.children[index] == null) {
                if (i == 0 || word.charAt(i) != word.charAt(i - 1)) {
                    return false;
                }
            } else {
                node = node.children[index];
            }
            ret = node.isWord || (i > 0 && word.charAt(i) == word.charAt(i - 1) && pre);
        }

        return ret;
    }

    public static void main(String[] args) {
        StrongWordInDict strongWordInDict = new StrongWordInDict();
        Set<String> set = new HashSet<>();
        set.add("worrdd");
        set.add("love");

        System.out.println(strongWordInDict.isDictionaryWord("woorrddddddd", set));
    }
}
