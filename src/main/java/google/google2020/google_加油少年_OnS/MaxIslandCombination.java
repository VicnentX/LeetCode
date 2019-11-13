package google.google2020.google_加油少年_OnS;

/*
number of island变体
但是是输出这些所有island里最大的size
这是第一问 第二问是自己选择改变一个0->1
使能输出新的最大的island size
也就是也许可以把两个island连起来之类的
这个题还是很有意思的
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 假设1是岛屿 0是海洋
 * 我用一个map记录root-size的对应
 * 起初的时候把所有的 岛屿的key，x * n + n -- value is 1
 * 每次unionfind的时候 找到需要链接的岛屿
 * 把一个岛屿的value保留 加到另一个岛屿的value里面 然后把这个岛屿的key从map删掉
 *
 * 最后每个0的点试图加上一个1 尝试
 */

public class MaxIslandCombination {
    public int solve(int[][] grid) {
        Map<Integer, Integer> map = new HashMap<>();
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        final int M = grid.length;
        final int N = grid[0].length;
        //initialize roots
        int[] roots = new int[M * N];
        for (int i = 0; i < M * N; ++i) {
            roots[i] = i;
        }
        //initialize map
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (grid[i][j] == 1) {
                    map.put(i * N + j, 1);
                }
            }
        }
        //try to find the origin size of islands
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (grid[i][j] == 1) {
                    for (int[] dir : dirs) {
                        int x = i + dir[0];
                        int y = j + dir[1];
                        if (x >= 0 && x < M && y >= 0 && y < N && grid[x][y] == 1) {
                            int root1 = findRoot(i * N + j, roots);
                            int root2 = findRoot(x * N + y, roots);
                            if (root1 != root2) {
                                int add = map.get(root2);
                                map.remove(root2);
                                roots[root2] = root1;
                                map.put(root1, map.get(root1) + add);
                            }
                        }
                    }
                }
            }
        }

        //add one single island to get max island
        int ret = 0;
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (grid[i][j] == 0) {
                    int tempSize = 1;
                    Set<Integer> visited = new HashSet<>();
                    for (int[] dir: dirs) {
                        int x = i + dir[0];
                        int y = j + dir[1];
                        if (x >= 0 && x < M && y >= 0 && y < N && grid[x][y] == 1) {
                            int root = findRoot(x * N + y, roots);
                            if (visited.add(root)) {
                                tempSize += map.get(root);
                            }
                        }
                    }
                    ret = Math.max(ret, tempSize);
                }
            }
        }

        return ret;
    }

    private int findRoot(int id, int[] roots) {
        if (roots[id] == id) return id;
        roots[id] = findRoot(roots[id], roots);
        return roots[id];
    }

    public static void main(String[] args) {
        MaxIslandCombination maxIslandCombination = new MaxIslandCombination();
        //9
        System.out.println(maxIslandCombination.solve(new int[][] {{1,1,0,0},{1,0,0,1},{1,1,0,1},{0,0,1,0}}));
    }
}
