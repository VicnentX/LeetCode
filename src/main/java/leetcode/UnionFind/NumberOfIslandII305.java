package leetcode.UnionFind;
/*
A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example:

Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
Output: [1,1,2,3]
Explanation:

Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

0 0 0
0 0 0
0 0 0
Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

1 0 0
0 0 0   Number of islands = 1
0 0 0
Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

1 1 0
0 0 0   Number of islands = 1
0 0 0
Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

1 1 0
0 0 1   Number of islands = 2
0 0 0
Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

1 1 0
0 0 1   Number of islands = 3
0 1 0
Follow up:

Can you do it in time complexity O(k log mn), where k is the length of the positions?
 */

/**
 * 这题是一道union find的题目 难在建立unionfidn 这里每个点[i,j]是roots里面的一个元素
 * 通过index = n * i + j to realize this conversion
 * initialize them with -1
 * when we check the surrounding point , if they are -1 means they have not been set so skip them
 * else
 * find their root and compare with the point we are setting then change the cnt if necessary
 *
 */
import java.util.*;

public class NumberOfIslandII305 {

    private int[][] dirs = {{-1 , 0} , {1 , 0} , {0 , 1} , {0 , -1}};

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> ret = new ArrayList<>();
        if (m == 0 || n == 0) return ret;
        int[] roots = new int[m * n];
        Arrays.fill(roots , -1);
        int cnt = 0;
        for (int[] p : positions) {
            int pos = p[0] * n + p[1];
            roots[pos] = pos;
            ++cnt;
            for (int[] dir : dirs) {
                int x = p[0] + dir[0];
                int y = p[1] + dir[1];
                int neighbor = x * n + y;

                if (x >= 0 && x < m && y >= 0 && y < n && roots[neighbor] != -1) {
                    int neighborRoot = find(roots , neighbor);
                    if (pos != neighborRoot) {
                        roots[pos] = neighborRoot;
                        pos = neighborRoot;
                        --cnt;
                    }
                }
            }
            ret.add(cnt);
        }
        return ret;
    }

    public List<Integer> numIslands2Optimaize(int m, int n, int[][] positions) {
        List<Integer> ret = new ArrayList<>();
        if (m == 0 || n == 0) return ret;
        int[] roots = new int[m * n];
        Arrays.fill(roots , -1);
        int[] size = new int[m * n];
        Arrays.fill(size , 1);
        int cnt = 0;
        for (int[] p : positions) {
            int pos = p[0] * n + p[1];
            roots[pos] = pos;
            ++cnt;
            for (int[] dir : dirs) {
                int x = p[0] + dir[0];
                int y = p[1] + dir[1];
                int neighbor = x * n + y;

                if (x >= 0 && x < m && y >= 0 && y < n && roots[neighbor] != -1) {
                    int neighborRoot = find(roots , neighbor);
                    if (pos != neighborRoot) {
                        if (size[pos] < size[neighborRoot]) {
                            roots[pos] = neighborRoot;
                            pos = neighborRoot;
                            size[neighborRoot] += size[pos];
                        } else {
                            roots[neighborRoot] = pos;
                            size[pos] += size[neighborRoot];
                        }
                        --cnt;
                    }
                }
            }
            ret.add(cnt);
        }
        return ret;
    }


    private int find(int[] roots , int id) {
        while (id != roots[id]) {
            roots[id] = roots[roots[id]];
            id = roots[id];
        }
        return id;
    }
    //Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
    //Output: [1,1,2,3]
    public static void main (String[] args) {
        NumberOfIslandII305 ni = new NumberOfIslandII305();
        System.out.println(ni.numIslands2(3 , 3 , new int[][] {{0,0} , {0,1} , {1,2} , {2,1}}));
        System.out.println(ni.numIslands2Optimaize(3 , 3 , new int[][] {{0,0} , {0,1} , {1,2} , {2,1}}));

    }
}
