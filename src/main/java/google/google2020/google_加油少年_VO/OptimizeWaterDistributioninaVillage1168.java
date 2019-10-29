package google.google2020.google_加油少年_VO;

/*
There are n houses in a village. We want to supply water for all the houses by building wells and laying pipes.

For each house i, we can either build a well inside it directly with cost wells[i], or pipe in water from another well to it. The costs to lay pipes between houses are given by the array pipes, where each pipes[i] = [house1, house2, cost] represents the cost to connect house1 and house2 together using a pipe. Connections are bidirectional.

Find the minimum total cost to supply water to all houses.


Example 1:


Input: n = 3, wells = [1,2,2], pipes = [[1,2,1],[2,3,1]]
Output: 3
Explanation:
The image shows the costs of connecting houses using pipes.
The best strategy is to build a well in the first house with cost 1 and connect the other houses to it with cost 2 so the total cost is 3.


Constraints:

1 <= n <= 10000
wells.length == n
0 <= wells[i] <= 10^5
1 <= pipes.length <= 10000
1 <= pipes[i][0], pipes[i][1] <= n
0 <= pipes[i][2] <= 10^5
pipes[i][0] != pipes[i][1]
 */


import java.util.Arrays;

/**
 * 这题pipe的管道点的index是从1开始 不是0 这个要当心
 * 这题是min spanning tree的问题
 * 把所以打井想成链接到一个super source的情况，
 * 这样
 * 从把边按照权重排序 + unionFind
 * 最后得到的结果就是答案
 */

public class OptimizeWaterDistributioninaVillage1168 {

    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        //多了一个超级点 所以是n+ 1个点
        int[] roots = new int[n + 1];
        for (int i = 0; i <= n; ++i) {
            roots[i] = i;
        }
        //简历边的arrays是一个二位int【】【】
        int[][] uf = new int[pipes.length + n][3];
        for (int i = 0; i < pipes.length; ++i) {
            uf[i] = pipes[i];
        }
        //管道的index是从1开始的 ，为超级源泉提供了0的位置
        for (int i = pipes.length; i < pipes.length + n; ++i) {
            uf[i] = new int[]{0, i - pipes.length + 1, wells[i - pipes.length]};
        }
        //sort
        Arrays.sort(uf, (a,b)->a[2] - b[2]);

        //get ret
        int ret = 0;
        for (int[] path: uf) {
            int root1 = findRoot(path[0], roots);
            int root2 = findRoot(path[1], roots);
            if (root1 != root2) {
                ret += path[2];
                roots[root2] = root1;
            }
        }
        return ret;
    }

    private int findRoot(int id, int[] roots) {
        return id == roots[id] ? id : (roots[id] = findRoot(roots[id], roots));
    }

}
