package google.google2020.google_加油少年_OnS;

/*
说模拟Google Map中一个一维路段的车流，
最后设计并表示出所有车流的平均速度，
比如有[0,14,90mph]表示有一个车流从位置0到14，
速度为90mph，然后有[3,15,80mph]，
那相当于分成了3个车流，
[0,3,90mph]，[3,14,85mph]，[14,15,80mph]，
考虑到有overlap要算平均值，
所有这个车流的class设计的时候得加个count表示由多少车流汇合。
这题我第一反应是sort起始车流位置然后PQ存process过的车流，
时间复杂度是N^2，小哥说可以，写代码吧。
写到一半想到segment tree应该能优化，
和面试官说需不需要换，
他说不用你先用这方法做完然后我们讨论优化。
 */

/**
 * 我的思想就是和那个room schedule是差不多的
 * */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FollowTrraffic {
    public List<double[]> getSegamentTrafficInfo(int[][] traffics) {
        final int N = traffics.length;
        //时间点 + 开始或者结束 + 车速
        int[][] timePoints = new int[2 * N][3];
        int index = 0;
        for (int[] traffic: traffics) {
            timePoints[index][0] = traffic[0];
            timePoints[index][1] = 1;
            timePoints[index++][2] = traffic[2];
            timePoints[index][0] = traffic[1];
            timePoints[index][1] = -1;
            timePoints[index++][2] = traffic[2];
        }
        //现在这个pair是 时间点，开始或者结束，速度
        Arrays.sort(timePoints, (a,b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        //get result
        List<double[]> ret = new ArrayList<>();
        int cnt = 0;
        int sum = 0;
        int pre = -1;
        for (int[] timePoint: timePoints) {
            if (timePoint[1] == 1) {//is '['
                if (cnt == 0) {
                    pre = timePoint[0];
                    sum += timePoint[2];
                    cnt++;
                } else if (pre == timePoint[0]) {
                    sum+= timePoint[2];
                    cnt++;
                } else {
                    ret.add(new double[] {pre, timePoint[0], sum * 1.0 / cnt});
                    pre = timePoint[0];
                    sum += timePoint[2];
                    cnt++;
                }
            } else {    //is ']'
                if (pre == timePoint[0]) {
                    sum -= timePoint[2];
                    cnt--;
                } else {
                    ret.add(new double[] {pre, timePoint[0], sum * 1.0 / cnt});
                    pre = timePoint[0];
                    sum -= timePoint[2];
                    cnt--;
                }
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        FollowTrraffic followTrraffic = new FollowTrraffic();
        int[][] input = new int[][] {{0,14,90},{3,15,80}};
        List<double[]> output = followTrraffic.getSegamentTrafficInfo(input);
        for (double[] pair: output) {
            for (double i: pair) {
                System.out.print(i + "__");
            }
            System.out.println();
        }
        System.out.println("-------------------------------");

        //
        input = new int[][] {{0,3,90},{3,14,80}};
        output = followTrraffic.getSegamentTrafficInfo(input);
        for (double[] pair: output) {
            for (double i: pair) {
                System.out.print(i + "__");
            }
            System.out.println();
        }
        System.out.println("-------------------------------");

        //
        input = new int[][] {{1,3,90},{1,5,80}};
        output = followTrraffic.getSegamentTrafficInfo(input);
        for (double[] pair: output) {
            for (double i: pair) {
                System.out.print(i + "__");
            }
            System.out.println();
        }
        System.out.println("-------------------------------");
    }
}
