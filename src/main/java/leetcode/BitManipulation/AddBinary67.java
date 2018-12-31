package leetcode.BitManipulation;

/*
Given two binary strings, return their sum (also a binary string).

The input strings are both non-empty and contains only characters 1 or 0.

Example 1:

Input: a = "11", b = "1"
Output: "100"
Example 2:

Input: a = "1010", b = "1011"
Output: "10101"
 */

public class AddBinary67 {
    public String addBinary(String a, String b) {
        int m = a.length();
        int n = b.length();
        if (m < n) return addBinary(b , a);
        char[] aa = a.toCharArray();
        char[] bb = b.toCharArray();
        int carry = 0;
        for (int i = n - 1 ; i >= 0 ; --i) {
            int cur = aa[i + m - n] - '0' + bb[i] - '0' + carry;
            aa[i + m - n] = (char)(cur % 2 + '0');
            carry = cur / 2;
        }
        for (int i = m - n - 1 ; i >= 0 ; --i) {
            int cur = aa[i] - '0' + carry;
            aa[i] = (char)(cur % 2 + '0');
            carry = cur / 2;
        }
        return carry == 1 ? "1" + String.valueOf(aa) : String.valueOf(aa);
    }

    public static void main (String[] args) {
        AddBinary67 ab = new AddBinary67();
        System.out.println(ab.addBinary("1010" , "1011"));
    }
}
