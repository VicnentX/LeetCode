package bloomreach.bloomreach_加油少年_VO;

/*
2d array N X N, filled with int from 1 to N^2

 3 7 8
 4 5 1
 9 2 6
 */

import java.util.HashMap;
import java.util.Map;

/**
 * any point, you can go up, down, left ,right, consec seq: 5,4,3 and 7,8 (8, 7)
 *  return the length of longest consec seq in the matrix
 *
 *  m points - time complexity m2
 *
 *  improve this algorithm
 *  dfs + memoization - Map<entryPoint, LenFromEntryPoint>
 *
 */

public class LongestConsecutiveSequence {
    private int maxLen = 0;

    public int solve(int[][] grid) {
        Map<Integer, Integer> map = new HashMap<>();
        final int M = grid.length;
        final int N = grid[0].length;
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                int tempLen = dfs(i,j,M,N,map,grid);
                maxLen = Math.max(maxLen, tempLen);
            }
        }
        return maxLen;
    }

    private int dfs (int i, int j, final int M, final int N, Map<Integer, Integer> map, int[][] grid) {
        if (map.containsKey(grid[i][j])) {
            return map.get(grid[i][j]);
        }

        int len = 1;

        int[][] dirs = new int[][] {{0,1},{0,-1},{1,0},{-1,0}};

        for (int[] dir: dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x >= 0 && x < M && y >= 0 && y < N && grid[x][y] == grid[i][j] + 1) {
                len = 1 + dfs(x, y, M, N, map, grid);
                map.put(grid[i][j], len);
                return len;
            }
        }

        map.put(grid[i][j], len);
        return len;
    }

    /*
    this method is use a map to store
        key is the number
        value is a int[] representing the position of this point
    then use a for loop (1 to n-1) to compare i with i + 1
        if i and i + 1 are neighbor , curLen + 1;
        else curLen = 1;
        compare maxLen with curLen and keep maxLen is maximum
     */

    public int solveWithOutRecursion(int[][] grid) {
        //fill the map
        Map<Integer, int[]> nodePositionMap = new HashMap<>();
        final int M = grid.length;
        final int N = grid[0].length;
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                nodePositionMap.put(grid[i][j], new int[] {i, j});
            }
        }
        //search number one by one
        int max = 1;
        int curLen = 1;
        //check from number 1 - M * N
        for (int i = 1; i < M * N; ++i) {
            if (isNeighbor(i, i + 1, nodePositionMap)) {
                curLen++;
                max = Math.max(max, curLen);
            } else {
                max = Math.max(max, curLen);
                curLen = 1;
            }
        }

        return max;
    }

    private boolean isNeighbor(int i, int j, Map<Integer, int[]> nodePositionMap) {
        int[] node1 = nodePositionMap.get(i);
        int[] node2 = nodePositionMap.get(i + 1);
        int deltaXAbs = Math.abs(node1[0] - node2[0]);
        int deltaYAbs = Math.abs(node1[1] - node2[1]);
        return deltaXAbs * deltaYAbs == 0 && deltaXAbs + deltaYAbs == 1;
    }

    public static void main(String[] args) {
        LongestConsecutiveSequence longestConsecutiveSequence = new LongestConsecutiveSequence();
        //3
        System.out.println(longestConsecutiveSequence.
                solve(new int[][] {{3,7,8},{4,5,1},{9,2,6}}));

        //3
        System.out.println(longestConsecutiveSequence.
                solveWithOutRecursion(new int[][] {{3,7,8},{4,5,1},{9,2,6}}));
    }
}
