package leetcode.dfs_bfs;

/*
There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.



Example 1:

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (4, 4)

Output: true

Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.

Example 2:

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (3, 2)

Output: false

Explanation: There is no way for the ball to stop at the destination.



Note:

There is only one ball and one destination in the maze.
Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
Seen this question in a real interview before?

 */

import java.util.*;

public class TheMaze490 {
    //其实这里可以直接用int[] 作为queue的元素
    public class point {
        int x , y;
        point(int xx , int yy) {
            x = xx;
            y = yy;
        }
    }
    public boolean hasPath(int[][] maze, int[] start, int[] end) {
        int[][] dirs = {{1 , 0} , {-1 , 0} , {0 , 1} , {0 , -1}};
        if (start[0] == end[0] && start[1] == end[1]) return true;
        int m = maze.length;
        int n = maze[0].length;
        Set<Integer> set = new HashSet<>();
        Queue<point> queue = new LinkedList<>();
        queue.add(new point(start[0] , start[1]));
        //这里set的key不能够是int[] 或者 point
        set.add(start[0] * n + start[1]);
        while (!queue.isEmpty()) {
            point cur = queue.poll();
            for (int[] dir : dirs) {
                int x = cur.x;
                int y = cur.y;
                while (x >= 0 && x < m && y >= 0 && y < n && maze[x][y] == 0) {
                    x += dir[0];
                    y += dir[1];
                }
                x -= dir[0];
                y -= dir[1];
                if (set.contains(x * n + y)) continue;
                if (x == end[0] && y == end[1]) return true;
                set.add(x * n + y);
                queue.add(new point(x , y));
            }
        }
        return false;
    }
}
