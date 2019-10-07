package Mathworks;

import javafx.util.Pair;

import java.util.*;

/**
 * Given a grid with w as width, h as height. A company wants to develop an office park in the grid where each cell represents a potential building lot. The goal is for the furthest of all lots to be as near as possible to an office building. Given an input n, which is the number of buildings to be placed in the lot, determine the building placement to minimize the distance the most distant empty lot is from the building. Distance is measured in horizontal and vertical directions, i.e. diagonal distance measurement is not considered.
 * <p>
 * inputs
 * w: an integer, the width of the grid
 * h: an integer, the height of the grid
 * n: an integer, the number of buildings to be placed
 * <p>
 * For example, w=4, h=4 and n=3. An optimal grid placement sets any lot within two unit distance of the building. The answer for this case is 2.
 * <p>
 * "0" indicates optimal building placement and in this case the maximal value of all shortest distances to the closest building for each cell is "2".
 * <p>
 * 1 0 1 2
 * 2 1 2 1
 * 1 0 1 0
 * 2 1 2 1
 * The above represents one optimal solution, there could be more like the above array rotated as an example. The above is an optimal solution because out of the 3 buildings (n=3), one building was placed at index (0,1), second was placed at (2,1) and third was placed at (2,3). The surrounding horizontal and vertical distance is shown as 1 and 2 by adding 1 each time we move horizontally and/or vertically. Note again that diagonal movement is not allowed:
 * <p>
 * 1 ← 0 → 1 → 2
 * ↓
 * 2 ← 1 → 2 ← 1
 * ↑. . ↑
 * 1 ← 0 → 1 ← 0
 * ↓ . . ↓
 * 2 ← 1 → 2 ← 1
 * Other examples:
 * <p>
 * Example 1)
 * <p>
 * w=3, h=3, n=2
 * Two buildings (zeros) have to be optimally placed. One of the optimal plan for this case is:
 * <p>
 * 01
 * 11
 * 10
 * <p>
 * 0 → 1
 * ↓
 * 1 . . .1
 * . . . . ↑
 * 1 ← 0
 * <p>
 * Answer: 1
 */

public class BuildOffices {
        // W for width, H for height, N for no of offices to build
        final int W, H, N;

        // dx and dy value together gives (x,y)
        // which helps to move in 4 adjacent cells
        // Right (1,0)
        // Left (-1,0)
        // Down (0,1)
        // Up (0,-1)
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        Map<String, Integer> dp = new HashMap<>();

        int[][] grid;

        // Constructor will set the values and clear the hashmap.
        public BuildOffices(int w, int h, int n) {
            W = w;
            H = h;
            N = n;
            dp.clear();
            grid = new int[W][H];

            for (int[] r : grid) {
                Arrays.fill(r, 0);
            }
        }

        // We convert the 2D array of W*H into 1D array in Row Order (if square matrix or Width is less),
        // or Column Wise (if Height is less)
        // BitMask holds the bits 0 empty spots, and 1 for where offices are present
        // Left means how many offices are still left to be built
        public int solve(int[][] grid, int left) {

            // If no more offices are left to be built, get the maximum distance for this scenario
            if (left == 0) {
                return bfs(grid);
            }

            StringBuilder k = new StringBuilder();

            for (int i = 0; i < W; i++) {
                for (int j = 0; j < H; j++) {
                    if (grid[i][j] == 1) {
                        k.append(i + ":" + j + "::");
                    }
                }
            }

            k.append("#" + left);
            // if the current scenario along with offices left are already processed, return the result
            String key = k.toString();
            if (dp.containsKey(key)) {
                return dp.get(key);
            }

            int[][] gridtemp = new int[W][H];
            for (int i = 0; i < W; i++) {
                for (int j = 0; j < H; j++) {
                    gridtemp[i][j] = grid[i][j];
                }
            }

            //  We are trying every possible scenario to build offices in the given grid
            int minDist = Integer.MAX_VALUE;
            for (int i = 0; i < W; i++) {
                for (int j = 0; j < H; j++) {
                    // If no office present in (i,j)th location, put one office there and check the minimum distance for that scenario
                    if (gridtemp[i][j] == 0) {
                        gridtemp[i][j] = 1;
                        int val = solve(gridtemp, left - 1);
                        minDist = Math.min(minDist, val);
                        gridtemp[i][j] = 0;
                    }
                }
            }

            // Store the min distance possible for the current scenario
            dp.put(key, minDist);
            return minDist;
        }

        // This function gives the maximum distance from all the empty spots to the offices for a given case of scenario
        private int bfs(int[][] grid) {
            // get a distance matrix with initial values as -1
            int[][] dist = new int[W][H];
            for (int[] row : dist)
                Arrays.fill(row, -1);

            int maxDist = 0;
            // Queue for processing the cells in Bredth-First-Search order.
            Queue<Pair<Integer, Integer>> Q = new LinkedList<>();

            // if office is present at (i,j)th location, the distance is 0, and put the (i,j) pair in Queue
            for (int i = 0; i < W; i++) {
                for (int j = 0; j < H; j++) {
                    if (grid[i][j] == 1) {
                        dist[i][j] = 0;
                        Q.add(new Pair<>(i, j));
                    }
                }
            }


            while (!Q.isEmpty()) {
                Pair<Integer, Integer> kv = Q.poll();
                int x = kv.getKey();
                int y = kv.getValue();

                // Get maximum distance for (i,j)th location
                maxDist = Math.max(maxDist, dist[x][y]);

                // Process all adjacent cells
                for (int d = 0; d < dx.length; d++) {
                    int xNew = x + dx[d];
                    int yNew = y + dy[d];

                    // if the adjacent cell is within grid boundary, and is not yet processed,
                    // set the max dist of he adjacent cell 1 more than the (i,j)th cell
                    // add the adjacent cell to queue
                    if (xNew >= 0 && xNew < W && yNew >= 0 && yNew < H && dist[xNew][yNew] == -1) {
                        dist[xNew][yNew] = dist[x][y] + 1;
                        Q.add(new Pair<>(xNew, yNew));
                    }
                }
            }

            return maxDist;
        }

        public static void main(String[] args) {
            BuildOffices ofc = new BuildOffices(6, 4, 3);
            int res = ofc.solve(ofc.grid, ofc.N);
            System.out.println(res);
        }
}
