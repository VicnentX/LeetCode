package NextJump;

/*
two strings are anagrams if they are permutations of each other. for example,
"aaagmnrs" is an anagram of "anagrams" / given an array of strings,
remove each string that is an anagram of an earlier string, then return the remaining
array in alphabetically sorted order
 */

import java.util.*;

public class Anagrams {
    public List<String> funWithAnagrams(String[] words) {
        Set<String> keySet = new HashSet<>();
        List<String> anagramsList = new ArrayList<>();

        for (String word: words) {
            String key = buildKey(word);
            if (keySet.add(key)) {
                anagramsList.add(word);
            }
        }

        Collections.sort(anagramsList);
        return anagramsList;
    }

    private String buildKey(String s) {
        int[] charCnt = new int[26];
        for (char c: s.toCharArray()) {
            charCnt[c - 'a']++;
        }
        StringBuilder key = new StringBuilder();
        for (int i: charCnt) {
            key.append(i);
        }
        return key.toString();
    }

    public static void main(String[] args) {
        Anagrams anagrams = new Anagrams();
        List<String> ret = anagrams.funWithAnagrams(new String[] {
                "code", "doce", "ecod", "framer", "frame"});
        for (String s: ret) {
            System.out.println(s);
        }
    }
}
