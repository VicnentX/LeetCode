package twitter.twitter_2020;

/*
得分：

输入：一个数组A，大小为N，代表N题，每一题1为正确，0为错误（A[i]=0或1）

输出：int K

问题：你至少做多少题得分比你朋友高(K可以为0)？

规则：你做前K题(index: 0 to K-1)，你的朋友做剩下的题(index: K to N-1)，题为正确(A[i]=1)得分加1，错误(A[i]=0)得分减1
 */

import java.util.Arrays;

public class MinimumStepsToWin {
    public int MinimumStep(int[] array) {
        final int ARRAY_LENGTH = array.length;
        for (int i = 0 ; i < ARRAY_LENGTH ; ++i) {
            if (array[i] == 0) {
                array[i] = -1;
            }
        }
        int sum = Arrays.stream(array).sum();
        int triggerPoint = sum / 2 + 1;
        int ret = 0;
        for (int i = 0 ; i < array.length ; ++i) {
            if (ret >= triggerPoint) return i;
            ret += array[i];
        }
        return ARRAY_LENGTH;
    }

    public static void main(String[] args) {
        MinimumStepsToWin minimumStepsToWin = new MinimumStepsToWin();
        System.out.println(minimumStepsToWin.MinimumStep(new int[] {1,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1}));
    }
}
