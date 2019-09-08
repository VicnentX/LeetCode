package twitter.twitter_2020;

/**
 * String字符集变换
 *
 * 输入：string数组A，string数组B
 *
 * 输出：int数组，每项代表对应index A[i]转化为B[i]至少需要变化的字符数量，不能转化则为-1
 *
 * 规则：所有字符只为小写a到z；string m转化为string n只需要变化到对应字符个数相等，
 * 不论顺序，若m, n不等长则不能转化。例：”abaa”转化为”baab”只需一个’a’变为’b’，因此答案为1。
 */

public class StringEditor {
    public int TranformString(String A , String B) {
        final int A_LENGTH = A.length();
        final int B_LENGTH = B.length();
        int wordToChange = 0;

        if (A_LENGTH != B_LENGTH) return -1;

        for (int i = 0 ; i < A_LENGTH ; ++i) {
            if(A.charAt(i) != B.charAt(i)) wordToChange ++;
        }

        return wordToChange;
    }

    public static void main(String[] args) {
        StringEditor stringEditor = new StringEditor();
        System.out.println(stringEditor.TranformString("asdfg", "asdhj"));
    }
}
