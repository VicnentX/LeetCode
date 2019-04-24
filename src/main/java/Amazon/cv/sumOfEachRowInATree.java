package Amazon.cv;

/*
calcualte each sum of every row in a tree.
 */

import java.util.*;

public class sumOfEachRowInATree {

    public class TreeNode{
        int val;
        TreeNode left , right;
        TreeNode (int x) {
            val = x;
        }
    }
    public List<Integer> sumOfRow (TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if (root == null) return ret;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            int sum = 0;
            for (int i = 0 ; i < n ; ++i) {
                TreeNode cur = queue.poll();
                sum += cur.val;
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
            }
            ret.add(sum);
        }
        return ret;
    }
}
