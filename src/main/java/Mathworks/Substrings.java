package Mathworks;

import java.util.*;

/**
 * z这题是找出 alphabetically first element in this reduce list and alphabetically last element in this reduce list
 * find them from substring which start with a vowel and end with a consonant
 */
public class Substrings {
    public List<String> findFirstAndSecond(String s) {
        final int N = s.length();
        List<String> reduceList = new ArrayList<>();
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        for (int i = 0; i < N; ++i) {
            for (int j = i + 1; j < N; ++j) {
                if (vowels.contains(s.charAt(i)) && !vowels.contains(s.charAt(j))) {
                    reduceList.add(s.substring(i, j + 1));
                }
            }
        }
        Collections.sort(reduceList);
        return new ArrayList<>(Arrays.asList(reduceList.get(0), reduceList.get(reduceList.size() - 1)));
    }

    public static void main(String[] args) {
        Substrings substrings = new Substrings();
        for (String s: substrings.findFirstAndSecond("abaab")) {
            System.out.println(s);
        }
    }
}
