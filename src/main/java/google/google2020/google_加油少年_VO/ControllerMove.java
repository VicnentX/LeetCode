package google.google2020.google_加油少年_VO;

/*
有个控制杆，有上下左右四个方向 或者理解为 A, B, C, D四个键。但是操作的时候不能穿越中心。
对于每个时刻 t， 有一个键或几个键可以得分。
比如 t = 2时， 按 A, B可得分， t=3 时，按 A, C可得分。
问： 给一个得分键的list, 能获得的最大分数是多少。
楼主直接用dfs 做，但觉得 更好的方法是 dp。

感觉可以，dp[t][j] 代表走到t时候最后一步是j的最大得分, j = A, B, C, D，
不知道楼主说的不能穿越中心是什么意思？是说我上一步往上走了，
下一步就只能走左或者右不能走下了是嘛。如果是这样理解的话，
那dp[A] 就是max  (dp[t-1][C], dp[t-1][D]) + score if A get scored at time t

 */

/**
 *  input is the score of every position in i round
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ControllerMove {

    public int getMaxScores(List<List<Integer>> inputs) {
        //因为是四个方向 我只用一维dp 因为我可以把这一轮的跟新存下来 然后在替换
        int[] dp = new int[4];
        for (List<Integer> input: inputs) {
            int[] tempDP = new int[4];
            for (int i = 0; i < 4; ++i) {
                tempDP[i] = Math.max(dp[(i - 1 + 4) % 4], dp[(i + 1) % 4]) + input.get(i);
            }
            dp = tempDP;
        }

        //get result
        int maxScore = 0;
        for (int ret: dp) {
            maxScore = Math.max(maxScore, ret);
        }
        return maxScore;
    }

    public static void main(String[] args) {
        ControllerMove controllerMove = new ControllerMove();
        List<List<Integer>> moves = new ArrayList<>();
        moves.add(new ArrayList<>(Arrays.asList(0,0,0,1)));
        moves.add(new ArrayList<>(Arrays.asList(0,10,0,0)));
        System.out.println(controllerMove.getMaxScores(moves));
    }

}
