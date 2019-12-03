package google.google2020.google_加油少年_OnS;

/*
大意就是给你一些画，每个画都有一个min price和一个quality point，
问需要多少钱才能把所有画买下来，条件是1）每幅画的成交价不能低于min price，2）
所有画的成交价必须和他们的quality point成正比。e.g. {20, 3}, {10, 1}, {15, 2}，
成交价就是30, 10, 20, 总价60。这一问比较简单，
可以O(N) time O(1) space完成。
接下来follow up问最少需要多少钱才能买下n幅画。
当时只有20分钟左右了我就说简单一点可以brute force，
把所有画里取n个的组合找出来，然后call第一问的函数算价格，
最后取global min。小姐姐说不错，你就写brute force吧，
我就写了。面完以后走回原来楼的路上我就问这题是不是有更简单的解法，
感觉有点像DP，小姐姐说是的可以DP解。

后来回家想了想可以用背包问题解
 */

import javafx.util.Pair;

import java.util.*;

public class BuyPainting {
    //buy all paintings
    public double buyAll(int[][] paintings) {
        double maxThrift = 0;
        for (int[] painting: paintings) {
            maxThrift = Math.max(maxThrift, painting[0] * 1.0 / painting[1]);
        }
        double ret = 0;
        for (int[] painting: paintings) {
            ret += painting[1] * maxThrift;
        }
        return ret;
    }

    //buy n paintings
    //这题我觉得蛮有意思的 我的想法就是按照thrift排序，(cost per quality)
    // 然后从尾巴算起取n的quality最小的和是多少，然后不用存下来，直接算一下local的最优解
    public double buyN(int[][] paintings, int n) {
        final int N = paintings.length;
        //pair store cost per quality and quality
        double[][] pairs = new double[N][2];
        for (int i = 0; i < N; ++i) {
            int[] painting = paintings[i];
            pairs[i][0] = painting[0] * 1.0 / painting[1];
            pairs[i][1] = painting[1];
        }
        Arrays.sort(pairs, (a,b) -> a[0] == b[0] ? Double.compare(b[1], a[1]) : Double.compare(b[0], a[0]));
        //这个排序是从小到大 还是上面的lamda比较直观 Arrays.sort(pairs, Comparator.<double[]>comparingDouble(a -> a[0]).thenComparingDouble(a -> a[1]));
        double ret = Integer.MAX_VALUE;
        double cnt = 0;
        //存放 quality
        PriorityQueue<Double> pq = new PriorityQueue<>((a,b) -> Double.compare(b,a));
        for(int i = pairs.length - 1; i >= 0; --i) {
            double[] pair = pairs[i];
            pq.add(pair[1]);
            cnt += pair[1];
            if (pq.size() < n) continue;
            if (pq.size() == n) {
                ret = Math.min(ret, pair[0] *  cnt);
            } else {
                double remove = pq.poll();
                cnt -= remove;
                ret = Math.min(ret, pair[0] *  cnt);
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        BuyPainting buyPainting = new BuyPainting();
        //60
        System.out.println(buyPainting.buyAll(new int[][] {{20, 3}, {10, 1}, {15, 2}}));
        //20
        System.out.println(buyPainting.buyN(new int[][] {{4, 1}, {20, 3}, {10, 1}, {15, 2}}, 2));
        //30
        System.out.println(buyPainting.buyN(new int[][] {{400, 100}, {20, 3}, {10, 1}, {15, 2}}, 2));
    }
}
