package leetcode.dfs_bfs;

/*
There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

Example 1:

Input: 2, [[1,0]]
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished
             course 0. So the correct course order is [0,1] .
Example 2:

Input: 4, [[1,0],[2,0],[3,1],[3,2]]
Output: [0,1,2,3] or [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both
             courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
             So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
Note:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.
 */


import java.util.*;
public class CourseSchedule210 {
    public int[] findOrder(int n, int[][] pre) {
        int[] nums = new int[n];
        List<Integer> ret = new ArrayList<>();
        Map<Integer , Set<Integer>> map = new HashMap<>();
        for (int[] pair : pre) {
            ++nums[pair[0]];
            if (!map.containsKey(pair[1])) map.put(pair[1] , new HashSet<>());
            map.get(pair[1]).add(pair[0]);
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0 ; i < n ; ++i) {
            if (nums[i] == 0) queue.add(i);
        }
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            ret.add(cur);
            if (map.containsKey(cur)) {
                for (int i : map.get(cur)) {
                    --nums[i];
                    if (nums[i] == 0) {
                        queue.add(i);
                    }
                }
            }
        }
        return ret.size() == n ? ret.stream().mapToInt(i -> i).toArray() : new int[] {};
    }

    public static void main (String[] args) {
        CourseSchedule210 cs = new CourseSchedule210();
        for (int k : cs.findOrder(4, new int[][] {{1,0},{2,0},{3,1},{3,2}})) {
            System.out.print(k + " ");
        }
    }
}
