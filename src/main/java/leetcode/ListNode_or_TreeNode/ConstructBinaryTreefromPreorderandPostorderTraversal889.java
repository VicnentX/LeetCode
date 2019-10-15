package leetcode.ListNode_or_TreeNode;

/*
Return any binary tree that matches the given preorder and postorder traversals.

Values in the traversals pre and post are distinct positive integers.



Example 1:

Input: pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
Output: [1,2,3,4,5,6,7]


Note:

1 <= pre.length == post.length <= 30
pre[] and post[] are both permutations of 1, 2, ..., pre.length.
It is guaranteed an answer exists. If there exists multiple answers, you can return any of them.
Seen this question in a real interview before?

 */

/**
 * https://www.youtube.com/watch?time_continue=799&v=53aOi0Drp9I
 *
 *
 * i 是这一个dfs pre的起始位置
 * j 是这一个dfs post的起始位置
 * n  是这一个dfs 从起始位置算起的pre和postarray的长度
 */

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreefromPreorderandPostorderTraversal889 {

    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        Map<Integer, Integer> map = new HashMap<>();
        final int N = pre.length;
        for (int i = 0 ; i < post.length; ++i) {
            map.put(post[i], i);
        }
        return dfs(0,0,N,pre,post,map);
    }

    private TreeNode dfs(int i, int j, int n, final int[] pre, final int[] post, final Map<Integer, Integer> map) {
        if (n <= 0) {
            return null;
        }
        TreeNode root = new TreeNode(pre[i]);
        if (n == 1) {
            return root;
        }
        int k = map.get(pre[i + 1]);
        int len = k - j + 1;
        root.left = dfs(i + 1, j, len, pre, post, map);
        root.right = dfs(i + len + 1, k + 1, n - len - 1, pre, post, map);
        return root;
    }

}
