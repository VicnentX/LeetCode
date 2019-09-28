package intuit.Intuit2020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;




    public class MatrixProblem3 {
        private int minLength;
        private List<int[]> res;

        public List<int[]> treasure(int[][] board, int[] startPosition, int[] endPosition) {
            // Step 1: how many treasures
            int m = board.length;
            int n = board[0].length;
            int numTreasures = 0;

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == 1) {
                        numTreasures++;
                    }
                }
            }
            // Step 2: dfs to traversal, recursion + backtrack
            minLength = m * n;
            res = new ArrayList<>();
            boolean[][] visited = new boolean[m][n];
            dfs(board, visited, startPosition, endPosition, startPosition, new ArrayList<>(), 0, numTreasures);
            return res;
        }

        private void dfs(
                int[][] board,
                boolean[][] visited,
                int[] startPosition,
                int[] endPosition,
                int[] curPosition,
                List<int[]> path,
                int count,
                int numTreasures
        ) {
            // exit case
            if (!isValid(board, curPosition) || visited[curPosition[0]][curPosition[1]]) {
                return;
            } else {
                if (path.size() > minLength) { // prune for dfs
                    return;
                }
                visited[curPosition[0]][curPosition[1]] = true;
                path.add(curPosition);
                if (board[curPosition[0]][curPosition[1]] == 1) {
                    count++;
                }
                if (Arrays.equals(curPosition, endPosition)) {
                    if (count == numTreasures && path.size() < minLength) {
                        res = new ArrayList<>(path);
                        minLength = path.size();
                    }
                } else {
                    List<int[]> neighbors = findLegalMoves(board, curPosition);
                    for (int[] neighbor : neighbors) {
                        dfs(board, visited, startPosition, endPosition, neighbor, path, count, numTreasures);
                    }
                }
                visited[curPosition[0]][curPosition[1]] = false;
                path.remove(path.size() - 1);
            }
        }

        private boolean isValid(int[][] board, int[] curPosition){
            if(curPosition[0] < 0  || curPosition[0] >= board.length || curPosition[1] <0  || curPosition[1] >= board[0].length){
                return false;
            }
            return true;
        }

        private List<int[]> findLegalMoves(int[][] board, int[] curPosition) {
            List<int[]> result = new ArrayList<>();
            int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            for (int[] dir : dirs) {
                int x = curPosition[0] + dir[0];
                int y = curPosition[1] + dir[1];
                if (x < 0 || x >= board.length
                        || y < 0 || y >= board[0].length
                        || board[x][y] == -1)
                    continue;
                result.add(new int[]{x, y});
            }
            return result;
        }

        public static void main(String[] args) {
            int[][] board = {{0, -1, 0, 0}, {0, 1, 1,0}, {0, 0, 0, -1}};
            MatrixProblem3 test = new MatrixProblem3();
            int[] startPosition = {0,0};
            int[] endPosition = {1,3};
            List<int[]> result = test.treasure(board, startPosition, endPosition);
            for(int[] pair : result){
                System.out.println("(" + pair[0] + ")" + "(" + pair[1] + ")" );
            }
        }
    }



