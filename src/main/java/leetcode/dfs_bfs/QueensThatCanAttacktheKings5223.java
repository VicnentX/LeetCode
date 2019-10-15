package leetcode.dfs_bfs;

/*
On an 8x8 chessboard, there can be multiple Black Queens and one White King.

Given an array of integer coordinates queens that represents the positions of the Black Queens, and a pair of coordinates king that represent the position of the White King, return the coordinates of all the queens (in any order) that can attack the King.



Example 1:



Input: queens = [[0,1],[1,0],[4,0],[0,4],[3,3],[2,4]], king = [0,0]
Output: [[0,1],[1,0],[3,3]]
Explanation:
The queen at [0,1] can attack the king cause they're in the same row.
The queen at [1,0] can attack the king cause they're in the same column.
The queen at [3,3] can attack the king cause they're in the same diagnal.
The queen at [0,4] can't attack the king cause it's blocked by the queen at [0,1].
The queen at [4,0] can't attack the king cause it's blocked by the queen at [1,0].
The queen at [2,4] can't attack the king cause it's not in the same row/column/diagnal as the king.
Example 2:



Input: queens = [[0,0],[1,1],[2,2],[3,4],[3,5],[4,4],[4,5]], king = [3,3]
Output: [[2,2],[3,4],[4,4]]
Example 3:



Input: queens = [[5,6],[7,7],[2,1],[0,7],[1,6],[5,1],[3,7],[0,3],[4,0],[1,2],[6,3],[5,0],[0,4],[2,2],[1,1],[6,4],[5,4],[0,0],[2,6],[4,5],[5,2],[1,4],[7,5],[2,3],[0,5],[4,2],[1,0],[2,7],[0,1],[4,6],[6,1],[0,6],[4,3],[1,7]], king = [3,4]
Output: [[2,3],[1,4],[1,6],[3,7],[4,3],[5,4],[4,5]]


Constraints:

1 <= queens.length <= 63
queens[0].length == 2
0 <= queens[i][j] < 8
king.length == 2
0 <= king[0], king[1] < 8
At most one piece is allowed in a cell.
 */

import java.util.*;

public class QueensThatCanAttacktheKings5223 {
    private int[][] visited;
    private List<List<Integer>> ret;
    private Set<List<Integer>> cor;

    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        final int M = 8;
        final int N = 8;
        ret = new ArrayList<>();
        visited = new int[M][N];
        cor = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {king[0], king[1]});
        visited[king[0]][king[1]] = 1;

        //make queen set
        Set<List<Integer>> queenSet = new HashSet<>();
        for (int[] queen: queens) {
            queenSet.add(new ArrayList<>(Arrays.asList(queen[0], queen[1])));
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                int[] cur = queue.remove();
                int x = cur[0];
                int y = cur[1];
                int deltax = cur[0] - king[0];
                int deltay = cur[1] - king[1];

                if (queenSet.contains(new ArrayList<>(Arrays.asList(x,y)))) {
                    if (deltax != 0 && deltay == 0) {
                        if (cor.add(new ArrayList<>(Arrays.asList((int)(deltax / Math.abs(deltax)), 0)))) {
                            ret.add(new ArrayList<>(Arrays.asList(x, y)));
                        }
                    } else if (deltax == 0 && deltay != 0) {
                        if (cor.add(new ArrayList<>(Arrays.asList(0, (int)(deltay / Math.abs(deltay)))))) {
                            ret.add(new ArrayList<>(Arrays.asList(x, y)));
                        }
                    } else if (Math.abs(deltax) == Math.abs(deltay) && deltax != 0) {
                        if (cor.add(new ArrayList<>(Arrays.asList((int)(deltax / Math.abs(deltax)), (int)(deltay / Math.abs(deltay)))))) {
                            ret.add(new ArrayList<>(Arrays.asList(x, y)));
                        }
                    }
                }
                addNeigbor(x, y, M, N, visited, queue);
            }
        }
        return ret;
    }

    private void addNeigbor(int x, int y, final int M, final int N, int[][] visited, Queue<int[]> queue) {
        int[][] dirs = new int[][]{{0,1}, {0,-1},{1,0},{-1,0}};
        for (int[] dir: dirs) {
            int xx = x + dir[0];
            int yy = y + dir[1];
            if (xx >= 0 && xx < M && yy >= 0 && yy < N && visited[xx][yy] == 0) {
                visited[xx][yy] = 1;
                queue.add(new int[] {xx, yy});
            }
        }
    }
}
