package TripleByte;

/*
coding题第一道是括号匹配，给一个全是左右括号的字符串，最后返回要加多少个左/右括号才能完全匹配，比如")("就返回2，
 */

public class ParenthesisFill {

    public int fillParen(String s) {
        int fillCnt = 0;
        int cnt = 0;
        for (char c: s.toCharArray()) {
            if (c == '(') {
                if (cnt < 0) {
                    fillCnt += Math.abs(cnt);
                    cnt = 1;
                } else {
                    cnt++;
                }
            } else {
                cnt--;
            }
        }
        fillCnt += Math.abs(cnt);

        return fillCnt;
    }

    public static void main(String[] args) {
        ParenthesisFill parenthesisFill = new ParenthesisFill();
        //2
        System.out.println(parenthesisFill.fillParen(")("));
        //1
        System.out.println(parenthesisFill.fillParen(")()"));
    }

}
