package google.google2020.google_加油少年_OnS;

/*
merge interval的简单版本。
两个输入，A是一堆没有overlap的interval list，
B也是一堆没有overlap的interval list。
返回一个合并了A和B的interval list。
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntesectionAB {
    public List<int[]> solve(int[][] aa, int[][] bb) {
        List<int[]> ret = new ArrayList<>();
        final int M = aa.length;
        final int N = bb.length;
        // [时间点，左/右， from a / from b] ======【时间点， 1/-1 ， -1/1】
        int[][] timePoints = new int[2 * (M + N)][3];
        int index = 0;
        //convert and add a into timePoints
        for (int[] interval: aa) {
            timePoints[index++] = new int[] {interval[0], 1, -1};
            timePoints[index++] = new int[] {interval[1], -1, -1};
        }
        //convert and add b into timePoints
        for (int[] interval: bb) {
            timePoints[index++] = new int[] {interval[0], 1, 1};
            timePoints[index++] = new int[] {interval[1], -1, 1};
        }

        //sort 先按照时间 时间一样就看是左还是右 是左的话a先 是右括号的话就b先
        Arrays.sort(timePoints, (a,b) -> a[0] == b[0] ? a[1] == b[1] ? a[1] == 1 ? a[2] - b[2] : b[2] - a[2] : a[1] - b[1] : a[0] - b[0]);
        int cnt = 0;
        for (int i = 1 ; i < 2 * (M + N); ++i) {
            cnt++;
            if (timePoints[i][1] == -1 && timePoints[i - 1][1] == 1 && cnt > 1) {
                ret.add(new int[] {timePoints[i - 1][0], timePoints[i][0]});
                cnt -= 2;
            } else {
                if (timePoints[i][1] == -1) {
                    cnt -= 2;
                }
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        IntesectionAB intesectionAB = new IntesectionAB();
        for (int[] pair: intesectionAB.solve(new int[][] {{1,5},{6,9}}, new int[][] {{2,3},{4,8}})) {
            System.out.println(pair[0] + " " + pair[1]);
        }
        System.out.println("---------------------");


        for (int[] pair: intesectionAB.solve(new int[][] {{1,4},{6,9}}, new int[][] {{1,4},{6,8}})) {
            System.out.println(pair[0] + " " + pair[1]);
        }
        System.out.println("---------------------");
    }
}
