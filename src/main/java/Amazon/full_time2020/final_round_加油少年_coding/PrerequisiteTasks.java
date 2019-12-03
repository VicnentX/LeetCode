package Amazon.full_time2020.final_round_加油少年_coding;

/*
There are a total of n tasks you have to pick, labeled from 0 to n-1. Some tasks may have prerequisites tasks, for example to pick task 0 you have to first finish tasks 1, which is expressed as a pair: [0, 1]

Given the total number of tasks and a list of prerequisite pairs, return the ordering of tasks you should pick to finish all tasks.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all tasks, return an empty array.
 */

import java.util.*;

public class PrerequisiteTasks {
    public List<Integer> solve(int n, int[][] pairs) {
        Map<Integer, Integer> indegreeMap = new HashMap<>();
        Map<Integer, List<Integer>> relations = new HashMap<>();
        List<Integer> ret = new ArrayList<>();

        for (int i = 0; i < n; ++i) {
            indegreeMap.put(i, 0);
        }
        for (int[] pair: pairs) {
            if (!relations.containsKey(pair[1])) {
                relations.put(pair[1], new ArrayList<>());
            }
            relations.get(pair[1]).add(pair[0]);
            indegreeMap.put(pair[0], indegreeMap.get(pair[0]) + 1);
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int key: indegreeMap.keySet()) {
            if (indegreeMap.get(key) == 0) {
                queue.add(key);
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.remove();
            ret.add(cur);
            if (relations.containsKey(cur)) {
                for (int next: relations.get(cur)) {
                    indegreeMap.put(next, indegreeMap.get(next) - 1);
                    if (indegreeMap.get(next) == 0) {
                        queue.add(next);
                    }
                }
            }
        }

        if (ret.size() != n) {
            System.out.println("impossible");
            return new ArrayList<>();
        }
        return ret;
    }

    public static void main(String[] args) {
        PrerequisiteTasks prerequisiteTasks = new PrerequisiteTasks();
        for (int i: prerequisiteTasks.solve(4, new int[][] {{1,0},{2,0},{3,1},{3,2}})) {
            System.out.println(i);
        }
    }
}
