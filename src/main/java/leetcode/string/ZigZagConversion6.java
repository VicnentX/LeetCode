package leetcode.string;

/*
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"

Write the code that will take a string and make this conversion given a number of rows:

string convert(string s, int numRows);
Example 1:

Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"
Example 2:

Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:

P     I    N
A   L S  I G
Y A   H R
P     I
 */

public class ZigZagConversion6 {
    public String convert(String s, int numRows) {
        if (s == null || s.length() == 0 || numRows <= 0) return "";
        if (numRows == 1) return s;
        int i = 0;
        StringBuilder[] sb = new StringBuilder[numRows];
        for (int j = 0 ; j < numRows ; ++j) sb[j] = new StringBuilder();
        int index = 0;

        while (i < s.length()) {
            while (i < s.length() && index < numRows - 1) {
                sb[index++].append(s.charAt(i++));
            }
            if (i == s.length()) break;
            while (i < s.length() && index >= 1) {
                sb[index--].append(s.charAt(i++));
            }
        }

        StringBuilder ret = new StringBuilder();
        for (StringBuilder k : sb) {
            ret.append(k);
        }
        return ret.toString();
    }
}
