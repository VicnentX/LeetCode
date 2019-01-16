package leetcode.SlideWindow_TwoPointers;
/*
Write a function that takes a string as input and reverse only the vowels of a string.

Example 1:

Input: "hello"
Output: "holle"
Example 2:

Input: "leetcode"
Output: "leotcede"
Note:
The vowels does not include the letter "y".
 */

public class ReverseVowelsofaString345 {
    public String reverseVowels(String s) {
        char[] ch = s.toCharArray();
        int i = 0;
        int j = ch.length - 1;
        String vo = "aeiouAEIOU";
        while (i < j) {
            while (i < j && !vo.contains(String.valueOf(ch[i]))) ++i;
            while (i < j && !vo.contains(String.valueOf(ch[j]))) --j;
            swap(i , j , ch);
            ++i;
            --j;
        }
        return String.valueOf(ch);
    }

    private void swap (int i , int j , char[] ch) {
        char tem = ch[i];
        ch[i] = ch[j];
        ch[j] = tem;
    }

    public static void main (String[] args) {
        ReverseVowelsofaString345 rv = new ReverseVowelsofaString345();
        System.out.println(rv.reverseVowels("Leetode"));
    }
}
