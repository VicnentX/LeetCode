package leetcode.dfs;

/*
Given an encoded string, return it's decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

Examples:

s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 */
public class DecodeString394 {
    private int i = 0;
    public String decodeString(String s) {
        if (s.length() == 0) return "";
        String ret = "";
        int cnt = 0;
        while (i < s.length()) {
            if (Character.isDigit(s.charAt(i))) {
                cnt = cnt * 10 + s.charAt(i++) - '0';
            }
            else if (Character.isLetter(s.charAt(i))) {
                ret += s.charAt(i++);
            } else if (s.charAt(i) == '[') {
                ++i;
                String up = decodeString(s);
                for (int j = 0 ; j < cnt ; ++j) {
                    ret += up;
                }
                cnt = 0;
            } else {
                ++i;
                return ret;
            }
        }
        return ret;
    }

    public static void main (String[] args) {
        DecodeString394 ds = new DecodeString394();
        System.out.println(ds.decodeString("2[sw3[rf]]"));
    }
}
