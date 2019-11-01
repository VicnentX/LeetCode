package google.google2020.google_加油少年_OnS;

/*
Given a set of N people (numbered 1, 2, ..., N), we would like to split everyone into two groups of any size.

Each person may dislike some other people, and they should not go into the same group.

Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same group.

Return true if and only if it is possible to split everyone into two groups in this way.



Example 1:

Input: N = 4, dislikes = [[1,2],[1,3],[2,4]]
Output: true
Explanation: group1 [1,4], group2 [2,3]
Example 2:

Input: N = 3, dislikes = [[1,2],[1,3],[2,3]]
Output: false
Example 3:

Input: N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
Output: false


Note:

1 <= N <= 2000
0 <= dislikes.length <= 10000
1 <= dislikes[i][j] <= N
dislikes[i][0] < dislikes[i][1]
There does not exist i != j for which dislikes[i] == dislikes[j].
 */

import java.util.*;

public class PossibleBipartition886 {
    public boolean possibleBipartitionDFS(int N, int[][] dislikes) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] pair: dislikes) {
            if (!map.containsKey(pair[0])) {
                map.put(pair[0], new ArrayList<>());
            }
            map.get(pair[0]).add(pair[1]);
            if (!map.containsKey(pair[1])) {
                map.put(pair[1], new ArrayList<>());
            }
            map.get(pair[1]).add(pair[0]);
        }

        //每个点的厨师情况是没有颜色，设为0， 蓝色红色分别是1 ， -1
        int[] color = new int[N + 1];

        //每个点作为入口
        for (int i = 1; i <= N; ++i) {
            if (color[i] == 0 && !dfs(i, 1, color, map)) {
                return false;
            }
        }

        return true;
    }

    private boolean dfs(int i, int curColor, int[] color, Map<Integer, List<Integer>> map) {
        color[i] = curColor;
        if (map.containsKey(i)) {
            for (int next: map.get(i)) {
                if (color[next] == curColor) return false;
                if (color[next] == 0 && !dfs(next, -curColor, color, map)) return false;
            }
        }

        return true;
    }

    public boolean possibleBipartitionBFS(int N, int[][] dislikes) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] pair: dislikes) {
            if (!map.containsKey(pair[0])) {
                map.put(pair[0], new ArrayList<>());
            }
            map.get(pair[0]).add(pair[1]);
            if (!map.containsKey(pair[1])) {
                map.put(pair[1], new ArrayList<>());
            }
            map.get(pair[1]).add(pair[0]);
        }

        //每个点的厨师情况是没有颜色，设为0， 蓝色红色分别是1 ， -1
        int[] color = new int[N + 1];

        //
        Queue<Integer> queue = new LinkedList<>();

        //每个点作为入口
        for (int i = 1; i <= N; ++i) {
            if (color[i] == 0) {
                color[i] = 1;
                queue.add(i);

                while (!queue.isEmpty()) {
                    int cur = queue.remove();
                    if (map.containsKey(cur)) {
                        for (int next: map.get(cur)) {
                            if (color[next] == color[cur]) return false;
                            if (color[next] == 0) {
                                color[next] = -color[cur];
                                queue.add(next);
                            }
                        }
                    }
                }
            }
        }

        return true;
    }
}
