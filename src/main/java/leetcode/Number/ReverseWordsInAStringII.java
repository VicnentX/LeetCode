package leetcode.Number;

public class ReverseWordsInAStringII {

    public char[] reverseWords(char[] str) {
        // write your code here

        char tem;
        for (int i = 0; i < str.length - i - 1; i++) {
            tem = str[i];
            str[i] = str[str.length - i - 1];
            str[str.length - i - 1] = tem;
        }

        int s = -1;
        int e = 0;

        for (int i = 0; i < str.length; i++) {
            if (str[i] != ' ' && s == -1) {
                s = i;
            } else if (str[i] == ' ' && s != -1) {
                e = i;
                while(s++<e--) {
                    tem = str[s];
                    str[s] = str[e-1];
                    str[e-1] = tem;
                }
                s = -1;
            }
        }

        if (s != -1) {
            e = str.length;
            while(s++<e--) {
                tem = str[s];
                str[s] = str[e - 1];
                str[e - 1] = tem;
            }
        }

        return str;
    }

    public static void main(String[] args){
        ReverseWordsInAStringII rs=new ReverseWordsInAStringII();
        char[] ch={'a','s','d',' ','4','5',' ','t','y'};
        System.out.println(rs.reverseWords(ch));
    }
}
