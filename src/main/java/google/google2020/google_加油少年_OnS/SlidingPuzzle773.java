package google.google2020.google_加油少年_OnS;

import java.util.*;

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
 */
public class SlidingPuzzle773 {
    //这是一道bfs的题目
    public int slidingPuzzle(int[][] board) {
        //record the existing pattern
        Set<String> visited = new HashSet<>();
        String target = "123450";
        //position and next potential position
        Map<Integer, List<Integer>> map = new HashMap<>();
        map.put(0, Arrays.asList(1,3));
        map.put(1, Arrays.asList(0,2,4));
        map.put(2, Arrays.asList(1,5));
        map.put(3, Arrays.asList(0,4));
        map.put(4, Arrays.asList(1,3,5));
        map.put(5, Arrays.asList(2,4));

        String origin = "";
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 3; ++j) {
                origin += board[i][j];
            }
        }

        Queue<String> queue = new LinkedList<>();
        queue.add(origin);
        visited.add(origin);
        int level = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                String cur = queue.remove();
                if (cur.equals(target)) return level;
                int indexOf0 = cur.indexOf('0');
                for (int nextIndex: map.get(indexOf0)) {
                    StringBuilder sb = new StringBuilder(cur);
                    sb.setCharAt(indexOf0, sb.charAt(nextIndex));
                    sb.setCharAt(nextIndex, '0');
                    String str = sb.toString();
                    if (visited.add(str)) {
                        queue.add(str);
                    }
                }
            }
            level++;
        }

        return -1;
    }

    public static void main(String[] args) {
        SlidingPuzzle773 slidingPuzzle773 = new SlidingPuzzle773();
        //-1
        System.out.println(slidingPuzzle773.slidingPuzzle(new int[][] {{1,2,3},{5,4,0}}));
        //5 [4,1,2],[5,0,3]
        System.out.println(slidingPuzzle773.slidingPuzzle(new int[][] {{4,1,2},{5,0,3}}));
        //14 [3,2,4],[1,5,0]
        System.out.println(slidingPuzzle773.slidingPuzzle(new int[][] {{3,2,4},{1,5,0}}));
    }

}
