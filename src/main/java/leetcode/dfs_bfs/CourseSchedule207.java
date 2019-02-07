package leetcode.dfs_bfs;

/*
There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

Example 1:

Input: 2, [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take.
             To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: 2, [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take.
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.
Note:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.
 */

import java.util.*;

public class CourseSchedule207 {
    public boolean canFinish(int n, int[][] pre) {
        int cnt = n;
        int[] indegree = new int[n];
        for (int[] pair : pre) {
            ++indegree[pair[0]];
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0 ; i < n ; ++i) {
            if (indegree[i] == 0) {
                queue.add(i);
                --cnt;
            }
        }

        while (!queue.isEmpty()) {
            int course = queue.poll();
            for (int[] pair : pre) {
                if (pair[1] == course) {
                    --indegree[pair[0]];
                    if (indegree[pair[0]] == 0) {
                        queue.add(pair[0]);
                        --cnt;
                    }
                }
            }
        }

        return cnt == 0;
    }

    public boolean canFinish2(int n, int[][] pres) {
        Map<Integer , Integer> degree = new HashMap<>();
        Map<Integer , List<Integer>> map = new HashMap<>();
        for (int[] pre : pres) {
            degree.put(pre[0] , degree.getOrDefault(pre[0] , 0) + 1);
            map.putIfAbsent(pre[1] , new ArrayList<>());
            map.get(pre[1]).add(pre[0]);
        }
        int ret = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0 ; i < n ; ++i) {
            if (!degree.containsKey(i)) {
                queue.add(i);
            }
        }

        while(!queue.isEmpty()) {
            int cur = queue.poll();
            ++ret;
            if (map.containsKey(cur)) {
                for (int k : map.get(cur)) {
                    degree.put(k , degree.get(k) - 1);
                    if (degree.get(k) == 0) {
                        queue.add(k);
                    }
                }
            }
        }

        return ret == n;
    }

    public static void main (String[] args) {
        CourseSchedule207 cs = new CourseSchedule207();
        System.out.println(cs.canFinish(2, new int[][]{{1,0},{0,1}}));
    }
}
