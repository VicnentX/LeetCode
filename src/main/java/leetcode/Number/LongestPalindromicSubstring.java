package leetcode.Number;

public class LongestPalindromicSubstring {

    private int low;
    private int maxLen=0;

    public String longestPalindrome(String s) {
        if(s.length()<2){
            return s;
        }

        for(int i=0;i<s.length();i++){
            extendPalindrome(s,i,i);
            extendPalindrome(s,i,i+1);
        }
        return s.substring(low,low+maxLen);
    }

    private void extendPalindrome(String s,int j, int k){
        while(j>=0 && k<s.length() && s.charAt(j)==s.charAt(k)){
            j--;
            k++;
        }
        if(maxLen<k-j-1){
            low=j+1;
            maxLen=k-j-1;
        }
    }

    public static void main(String[] args){
        LongestPalindromicSubstring lps=new LongestPalindromicSubstring();
        System.out.println(lps.longestPalindrome("asdfggfdlo"));
    }

}
