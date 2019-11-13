package google.google2020.google_加油少年_OnS;

/*
给你一个BST, 但是这个BST是有duplicate的，问你在这棵BST中出现频率最高的那个数字是什么，以及频率是多少。 follow up是， 不用extra space

利用BST中序遍历是单调递增的性质。维护一个curVal，
在中序遍历的时候，如果root.val == curVal，
更新curFreq。
因为BST保证了你只会在连续几个node的时候碰到相同的val.
不可能你刚开始遍历了几个4，然后遍历了几个5，然后又遍历到4.
 */

public class CountBST {

    class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode (int x) {
            val = x;
        }
    }

    //ret = num + frequency
    int[] ret = new int[] {-1, 0};
    int cnt = 0;
    int pre = -1;

    public int getMostFrequentNum(TreeNode root) {
        dfs(root);
        return ret[0];
    }

    //return the pair of number and its frequency
    private void dfs(TreeNode root) {
        if (root == null) return;

        dfs(root.left);

        if (root.val == pre) {
            cnt++;
        } else {
            cnt = 1;
            pre = root.val;
        }
        if (cnt > ret[1]) {
            ret = new int[] {root.val, cnt};
        }

        dfs(root.right);
    }
}
