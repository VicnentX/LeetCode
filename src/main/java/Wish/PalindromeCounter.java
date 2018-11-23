package Wish;

public class PalindromeCounter {
    public int counterPal(String s){
        if(s == null) return 0;
        int ret = 0;
        for(int i = 0 ; i < s.length() ; ++i){
            ret += counts(i , i , s);
            ret += counts(i, i + 1, s);
        }
        return ret;
    }
    private int counts(int i , int j , String s){
        int cnt = 0;
        while(i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)){
            ++cnt;
            --i;
            ++j;
        }
        return cnt;
    }

    public static void main(String[] args){
        PalindromeCounter pc = new PalindromeCounter();
        System.out.println(pc.counterPal("aaa"));
        System.out.println(pc.counterPal("abccba"));
    }
}
