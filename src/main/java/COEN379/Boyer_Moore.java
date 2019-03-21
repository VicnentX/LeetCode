package COEN379;

//这个算法也是string match用的！！！！

public class Boyer_Moore {
    public int[] compute_r (String s) {
        int[] r = new int[256];
        for (int i = 0 ; i < s.length() ; ++i) {
            r[s.charAt(i)] = i;
        }
        return r;
    }

    public void bm_bc(String text , String pattern) {
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
                s += Math.max(1 , m - 1 - len - r[text.charAt(s - len)]);
            }
        }
    }

    public static void main (String[] args) {
        Boyer_Moore bm = new Boyer_Moore();
        bm.bm_bc("abcabcbbacb" , "abc");
    }
}
