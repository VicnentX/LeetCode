package COEN379;

public class Boyer_Moore_Horspool {
    public int[] compute_r(String s) {
        int[] r = new int[256];
        for (int i = 0 ; i < s.length() - 1; ++i) {
            r[s.charAt(i)] = i;
        }
        return r;
    }
    public void FindOccurrenceHorspool(String text , String pattern) {
        int n = text.length();
        int m = pattern.length();
        int[] r = compute_r(pattern);
        int s = m - 1;
        int len = 0;

        while (s < n) {
            for (len = 0 ; len < m  && text.charAt(s - len) == pattern.charAt(m - 1 - len) ; ++len) {
                //nothing
            }
            if (len == m) {
                System.out.print(s - m + 1 + " ");
                ++s;
            } else {//there is a mismatch
                s += Math.max(1 , m - 1 - r[text.charAt(s)]);
                //这里相当于直接把pattern里面和s对应的最右边的那个charater移动到和s对齐。
            }
        }
    }

    public static void main (String[] args) {
        Boyer_Moore_Horspool bmh = new Boyer_Moore_Horspool();
        bmh.FindOccurrenceHorspool("abcabcbbcba" , "abc");
        System.out.println();
        bmh.FindOccurrenceHorspool("aaaaaa" , "a");
        System.out.println();
        bmh.FindOccurrenceHorspool("aaaa" , "aa");
    }
}
