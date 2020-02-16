package leetcode.UnionFind;

/*
Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs pairs, determine if two sentences are similar.

For example, words1 = ["great", "acting", "skills"] and words2 = ["fine", "drama", "talent"] are similar, if the similar word pairs are pairs = [["great", "good"], ["fine", "good"], ["acting","drama"], ["skills","talent"]].

Note that the similarity relation is transitive. For example, if "great" and "good" are similar, and "fine" and "good" are similar, then "great" and "fine" are similar.

Similarity is also symmetric. For example, "great" and "fine" being similar is the same as "fine" and "great" being similar.

Also, a word is always similar with itself. For example, the sentences words1 = ["great"], words2 = ["great"], pairs = [] are similar, even though there are no specified similar word pairs.

Finally, sentences can only be similar if they have the same number of words. So a sentence like words1 = ["great"] can never be similar to words2 = ["doubleplus","good"].

Note:

The length of words1 and words2 will not exceed 1000.
The length of pairs will not exceed 2000.
The length of each pairs[i] will be 2.
The length of each words[i] and pairs[i][j] will be in the range [1, 20].
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SentenceSimilarityII737 {
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, List<List<String>> pairs) {
        if (words1.length != words2.length) return false;
        int n = pairs.size() * 2;
        int[] roots = new int[n];
        for (int i = 0; i < n; ++i) {
            roots[i] = i;
        }

        //assign index
        Map<String, Integer> map = new HashMap<>();
        int index = 0;
        for (List<String> pair: pairs) {
            for (String word: pair) {
                if (!map.containsKey(word)) {
                    map.put(word, index++);
                }
            }
            int root1 = find(map.get(pair.get(0)), roots);
            int root2 = find(map.get(pair.get(1)), roots);
            if (root1 != root2) {
                roots[root2] = root1;
            }
        }

        //check
        for (int i = 0; i < words1.length; ++i) {
            if (words1[i].equals(words2[i])) continue;
            if (!map.containsKey(words1[i]) || !map.containsKey(words2[i])) return false;
            int root1 = find(map.get(words1[i]), roots);
            int root2 = find(map.get(words2[i]), roots);
            if (root1 != root2) return false;
        }

        return true;

    }

    private int find(int id, int[] roots) {
        if (id == roots[id]) return id;
        roots[id] = find(roots[id], roots);
        return roots[id];
    }
}
