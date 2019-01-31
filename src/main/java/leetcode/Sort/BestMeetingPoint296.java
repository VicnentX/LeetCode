package leetcode.Sort;

/*
A group of two or more people wants to meet and minimize the total travel distance. You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

Example:

Input:

1 - 0 - 0 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0

Output: 6

Explanation: Given three people living at (0,0), (0,4), and (2,2):
             The point (0,2) is an ideal meeting point, as the total travel distance
             of 2+2+2=6 is minimal. So return 6.
Seen this question in a real interview before?

 */

import java.util.*;

public class BestMeetingPoint296 {
    private int ret = 0;
    public int minTotalDistance(int[][] grid) {

        if (grid.length == 0 || grid[0].length == 0) return ret;
        int m = grid.length;
        int n = grid[0].length;
        List<Integer> x = new ArrayList<>();
        List<Integer> y = new ArrayList<>();
        for (int i = 0 ; i < m ; ++i) {
            for (int j = 0 ; j < n ; ++j) {
                if (grid[i][j] == 1) {
                    x.add(i);
                    y.add(j);
                }
            }
        }
        Collections.sort(x);
        Collections.sort(y);
        countX(x , 0 , x.size() - 1);
        countY(y , 0 , y.size() - 1);
        return ret;
    }
    private void countX(List<Integer> x , int i , int j) {
        while (i <= j) {
            ret += (x.get(j--) - x.get(i++));
        }
    }
    private void countY(List<Integer> y , int i , int j) {
        while (i <= j) {
            ret += (y.get(j--) - y.get(i++));
        }
    }
}
