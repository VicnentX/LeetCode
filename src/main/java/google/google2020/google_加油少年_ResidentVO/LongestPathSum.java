package google.google2020.google_加油少年_ResidentVO;

/*
一个蜀，每个node可以有0-n个子node，然后每个子node 和父母直接都有edge，edge上有weight，求maxmimum。
比如说 Root      Root-A weight是10，RB是5，RC是3 那么最大的是RA。 （要自己定义Tree的结构以及EDGE什么的）
 */

import java.util.List;

public class LongestPathSum {
    public class Node {
       List<Object[]> children;

       //[Node, cost]
       Node(List<Object[]> children) {
           this.children = children;
       }
    }

    private int maxCost = 0;

    public int solve(Node root) {
        dfs(root, 0);
        return maxCost;
    }

    private void dfs(Node root, int curCost) {
        if (root.children == null) {
            maxCost = Math.max(maxCost, curCost);
            return;
        }
        for (Object[] pair: root.children) {
            dfs((Node)pair[0], (int)pair[1] + curCost);
        }
    }
}
