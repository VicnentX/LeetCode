package VMware;
/*
改一个字母 使得 字典排序最小 并且 不是palindrome
 */


public class liujie {
    public String changeChar(String s) {
        if (s == null || s.length() == 0) return "IMPOSSIBLE";
        char[] charArray = s.toCharArray();
        int n = s.length();
        for (int i = 0 ; i < n / 2 ; ++i) {

            if (charArray[i] == 'a') continue;
            else {
                charArray[i] = 'a';
                return new String(charArray);
            }

        }
        return "IMPOSSIBLE";
    }

    public static void main(String[] args) {
        liujie lj = new liujie();
        System.out.println(lj.changeChar("bab"));
        System.out.println(lj.changeChar("qwerrewq"));
        System.out.println(lj.changeChar("abcdcba"));
        System.out.println(lj.changeChar("aaaa"));
        System.out.println(lj.changeChar("aaabbaaa"));
        System.out.println(lj.changeChar("aaabaaa"));
    }
}
