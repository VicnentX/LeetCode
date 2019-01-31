package leetcode.dfs_bfs;

/*
On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, and an empty square represented by 0.

A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.

The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].

Given a puzzle board, return the least number of moves required so that the state of the board is solved. If it is impossible for the state of the board to be solved, return -1.

Examples:

Input: board = [[1,2,3],[4,0,5]]
Output: 1
Explanation: Swap the 0 and the 5 in one move.
Input: board = [[1,2,3],[5,4,0]]
Output: -1
Explanation: No number of moves will make the board solved.
Input: board = [[4,1,2],[5,0,3]]
Output: 5
Explanation: 5 is the smallest number of moves that solves the board.
An example path:
After move 0: [[4,1,2],[5,0,3]]
After move 1: [[4,1,2],[0,5,3]]
After move 2: [[0,1,2],[4,5,3]]
After move 3: [[1,0,2],[4,5,3]]
After move 4: [[1,2,0],[4,5,3]]
After move 5: [[1,2,3],[4,5,0]]
Input: board = [[3,2,4],[1,5,0]]
Output: 14
Note:

board will be a 2 x 3 array as described above.
board[i][j] will be a permutation of [0, 1, 2, 3, 4, 5].
Seen this question in a real interview before?

 */

/*
Algorithm:

Consider each state in the board as a graph node,
we just need to find out the min distance between start node
and final target node "123450".
Since it's a single point to single point questions,
Dijkstra is not needed here. We can simply use BFS,
and also count the level we passed.
 Every time we swap 0 position in the String to find the next state.
  Use a hashTable to store the visited states.
 */

import java.util.*;
public class SlidingPuzzle773 {
    public int slidingPuzzle(int[][] b) {
        int level = 0;
        if (b.length == 0 || b[0].length == 0) return level;
        int m = b.length;
        int n = b[0].length;
        int[][] dirs = {{1 , 3} , {0 , 2 , 4} , {1 , 5} , {0 , 4} , {1 , 3 , 5} , {2 , 4}};
        String target = "123450";
        String s = "";
        for (int i = 0 ; i < m ; ++i) {
            for (int j = 0 ; j < n ; ++j) {
                s += (char)(b[i][j] + '0');
                //s += b[i][j];
            }
        }

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(s);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0 ; k < size ; ++k) {
                String cur = queue.poll();
                if (cur.equals(target)) return level;

                int i = cur.indexOf('0');
                for (int j : dirs[i]) {
                    String revisedString = swap(i , j , cur);
                    if (!visited.contains(revisedString)) {
                        queue.add(revisedString);
                        visited.add(revisedString);
                    }
                }
            }
            ++level;
        }
        return -1;
    }
    private String swap (int i , int j , String s) {
        StringBuilder sb = new StringBuilder(s);
        sb.setCharAt(i , s.charAt(j));
        sb.setCharAt(j , s.charAt(i));
        return sb.toString();
    }
}
