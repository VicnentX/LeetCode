package leetcode.SlideWindow_TwoPointers;

//这里如果map1 = map2 这样写的话  就等于两个map联动了 所以要写成 HashMap<Character , Integer> tem = new HashMap<>(map);
//但这种方法比较慢

/*
Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first string's permutations is the substring of the second string.
Example 1:
Input:s1 = "ab" s2 = "eidbaooo"
Output:True
Explanation: s2 contains one permutation of s1 ("ba").
Example 2:
Input:s1= "ab" s2 = "eidboaoo"
Output: False
Note:
The input strings only contain lower case letters.
The length of both given strings is in range [1, 10,000].
 */

/**
 * How do we know string p is a permutation of string s?
 * Easy, each character in p is in s too.
 * So we can abstract all permutation strings of s to a map (Character -> Count).
 * i.e. abba -> {a:2, b:2}. Since there are only 26 lower case letters in this problem,
 * we can just use an array to represent the map.
 * How do we know string s2 contains a permutation of s1?
 * We just need to create a sliding window with length of s1,
 * move from beginning to the end of s2.
 * When a character moves in from right of the window,
 * we subtract 1 to that character count from the map.
 * When a character moves out from left of the window,
 * we add 1 to that character count.
 * So once we see all zeros in the map,
 * meaning equal numbers of every characters between s1 and the substring in the sliding window,
 * we know the answer is true.
 */

import java.util.*;
public class PermutationInString567 {
    public boolean checkInclusion(String s1, String s2) {
        //方法二 每次修改一位
        int m = s1.length();
        int n = s2.length();

        if(s1.length() > s2.length()) return false;

        int[] array = new int[26];
        for(char ch : s1.toCharArray()){
            ++array[ch - 'a'];
        }
        for(int i = 0 ; i < m ; ++i){
            --array[s2.charAt(i) - 'a'];
        }

        for(int i = 0 ; i < n - m ; ++i){
            if(allZero(array))  return true;
            ++array[s2.charAt(i) - 'a'];
            --array[s2.charAt(i + m) - 'a'];
        }
        return allZero(array)? true : false ;
    }

    private boolean allZero(int[] a){
        for(int i = 0 ; i < a.length ; ++i){
            if(a[i] != 0) return false;
        }
        return true;
    }

    public boolean checkInclusionTWOPOINTER(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        if (m > n) return false;
        Map<Character , Integer> map = new HashMap<>();
        for (char c : s1.toCharArray()) {
            map.put(c , map.getOrDefault(c , 0) + 1);
        }
        int i = 0 , j = 0;
        Map<Character , Integer> count = new HashMap<>(map);
        while (j < n) {
            char c = s2.charAt(j);
            if (count.containsKey(c)) {
                int value = count.get(c) - 1;
                count.put(c , value);
                if (value >= 0) {
                    if (j - i + 1 == m) return true;
                } else {
                    while (count.get(c) < 0) {
                        count.put(s2.charAt(i) , count.get(s2.charAt(i)) + 1);
                        ++i;
                    }
                }
                ++j;
            } else {
                ++j;
                i = j;
                count = new HashMap<>(map);
            }
        }
        return false;
    }



    public static void main(String[] args){
        PermutationInString567 pi = new PermutationInString567();
        System.out.println(pi.checkInclusion("ab" , "eidbaooo"));
    }
}
