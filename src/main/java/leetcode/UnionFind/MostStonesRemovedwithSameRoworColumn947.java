package leetcode.UnionFind;

/*
On a 2D plane, we place stones at some integer coordinate points.  Each coordinate point may have at most one stone.

Now, a move consists of removing a stone that shares a column or row with another stone on the grid.

What is the largest possible number of moves we can make?



Example 1:

Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
Output: 5
Example 2:

Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
Output: 3
Example 3:

Input: stones = [[0,0]]
Output: 0


Note:

1 <= stones.length <= 1000
0 <= stones[i][j] < 10000
 */

/**
 * 这题的精髓在于找到有几个group 每个group总有办法从边缘往中心删除点 最后每一个group总有一个留下
 * 所以用unionfind找出一共有有几个group， 记作cnt
 * 然后总的点数n － cnt就是答案
 *
 */

public class MostStonesRemovedwithSameRoworColumn947 {
    public int removeStones(int[][] stones) {
        if (stones.length == 0 || stones[0].length == 0) return 0;
        int n = stones.length;
        int[] roots = new int[n];
        for (int i = 0 ; i < n ; ++i) {
            roots[i] = i;
        }
        int cnt = n;
        for (int i = 0 ; i < n ; ++i) {
            for (int j = i + 1 ; j < n ; ++j) {
                if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
                    int root1 = findRoot(i , roots);
                    int root2 = findRoot(j , roots);
                    if (root1 != root2) {
                        roots[root1] = root2;
                        --cnt;
                    }
                }
            }
        }
        return n - cnt;
    }
    private int findRoot(int id , int[] roots) {
        while (roots[id] != id) {
            roots[id] = roots[roots[id]];
            id = roots[id];
        }
        return id;
    }

    public static void main (String[] args) {
        MostStonesRemovedwithSameRoworColumn947 ms = new MostStonesRemovedwithSameRoworColumn947();
        //Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
        //Output: 3
        System.out.println(ms.removeStones(new int[][]{{0,0},{0,2},{1,1},{2,0},{2,2}}));
    }
}
