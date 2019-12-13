package google.google2020.google_加油少年_MonResidency;


/*
这题的意思就是给一个String， 然后假设我有一个字典，
比如：cat 就表示我有一个a 一个c 一个t可以使用 （给我cat和act其实是一样的）
要我给出所有的可以的用一个a 一个c 一个t 组成的在字典里面的单词
 */

/**
 * 这题我假设字典是trie
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LetterCombination {

    Trie root;

    public class Trie {
        char val;
        boolean isWord;
        Trie[] children;
        Trie() {

        }
        Trie(char x) {
            val = x;
            Trie[] children = new Trie[26];
        }
    }

    public List<String> solve(String s) {
        //convert s to int[]
        int[] input = new int[26];
        for(char c: s.toCharArray()) {
            input[c - 'a']++;
        }
        //create a dictionary and fill the dictionary(assume it is done in the generateTrie method)
        root = new Trie(' ');
        generateTrie(root);
        //dfs to get all result
        List<String> ret = new ArrayList<>();
        dfs(input, "", root, ret);
        return ret;
    }

    private void dfs(int[] input, String curWord, Trie root, List<String> ret) {
        if (root.isWord) {
            ret.add(curWord);
        }

        for (int i = 0; i < 26; ++i) {
            if (input[i] > 0) {
                if (root.children[i] != null) {
                    input[i]--;
                    dfs(input, curWord + (i + 'a'), root.children[i], ret);
                    input[i]++;
                }
            }
        }

    }

    private void generateTrie(Trie root) {

    }

    private List<String> solve1(String s, List<String> dic) {
        Set<String> res = new HashSet<>(); // avoid duplicate
        Set<String> dicSet = new HashSet<>(dic);
        int[] input = new int[26];
        for (char c : s.toCharArray()) input[c - 'a']++;
        // dfs
        dfs(input, res, dicSet, new StringBuilder());
        List<String> resList = new ArrayList<>();
        return resList;
    }

    private void dfs(int[] input, Set<String> res, Set<String> dicSet, StringBuilder word) {
        if (word.length() > 0 && dicSet.contains(word.toString()))
            res.add(word.toString());

        for (int i = 0; i < 26; i++) {
            if (input[i] > 0) {
                word.append((char) (i + 'a'));
                input[i]--;
                dfs(input, res, dicSet, word);
                input[i]++;
                word.setLength(word.length() - 1);
            }
        }
    }
 }
