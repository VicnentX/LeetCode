package leetcode.dfs_bfs;

/*
A tree rooted at node 0 is given as follows:

The number of nodes is nodes;
The value of the i-th node is value[i];
The parent of the i-th node is parent[i].
Remove every subtree whose sum of values of nodes is zero.

After doing so, return the number of nodes remaining in the tree.



Example 1:



Input: nodes = 7, parent = [-1,0,0,1,2,2,2], value = [1,-2,4,0,-2,-1,-1]
Output: 2


Constraints:

1 <= nodes <= 10^4
-10^5 <= value[i] <= 10^5
parent.length == nodes
parent[0] == -1 which indicates that 0 is the root.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeleteTreeNodes1273 {
    public int deleteTreeNodes(int nodes, int[] parent, int[] value) {
        //recurrsion
        //把下面数据的和，以及几个点已经被忽略，以及下面点的个数总和
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 1; i < nodes; ++i) {
            int father = parent[i];
            if (!map.containsKey(father)) map.put(father, new ArrayList<>());
            map.get(father).add(i);
        }
        return nodes - dfs(0, value, map)[1];
    }

    private int[] dfs(int node, int[] value, Map<Integer, List<Integer>> map) {

        int sum = value[node];
        int dismissCnt = 0;
        int cnt = 1;

        if (map.containsKey(node)) {
            for (int nextNode: map.get(node)) {
                int[] ret = dfs(nextNode, value, map);
                sum += ret[0];
                dismissCnt += ret[1];
                cnt += ret[2];
            }
        }

        if (sum == 0) return new int[] {sum, cnt, cnt};
        return new int[] {sum, dismissCnt, cnt};
    }
}
