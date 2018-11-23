package leetcode.dfs;

import java.util.*;

//class TreeNode {
//      int val;
//      TreeNode left;
//      TreeNode right;
//      TreeNode(int x) { val = x; }
//}
/*
Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

Note:

Given target value is a floating point.
You may assume k is always valid, that is: k ≤ total nodes.
You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
Example:

Input: root = [4,2,5,1,3], target = 3.714286, and k = 2

    4
   / \
  2   5
 / \
1   3

Output: [4,3]
Follow up:
Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?
 */

public class ClosestBinarySearchTreeValueII272 {


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
////方法 scan the tree and then delete from head or tail till q.size() is k.
//    public List<Integer> closestKValues(TreeNode root, double target, int k) {
//
//        Deque<Integer> q = new LinkedList<>();
//        dfs(root , q);
//        while(q.size() > k){
//            if(Math.abs(q.peekLast() - target) > Math.abs(q.peekFirst() - target)){
//                q.pollLast();
//            }else{
//                q.pollFirst();
//            }
//        }
//        return new ArrayList<>(q);
//    }
//    private void dfs(TreeNode root , Deque<Integer> q){
//        if(root == null){
//            return ;
//        }
//        dfs(root.left , q);
//        q.add(root.val);
//        dfs(root.right , q);
//    }

    //方法二 先initiate两个stack 用掉一个数字 就为那个stack加一串数字
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        Stack<TreeNode> pre = new Stack<>();
        Stack<TreeNode> succ = new Stack<>();
        initiateStack(root , target , pre , succ);
        List<Integer> ret = new ArrayList<>();
        while(k > 0){
            --k;
            //System.out.println(succ.empty());
            //System.out.println(pre.empty());
            if(pre.empty() || ( !succ.empty() && Math.abs(pre.peek().val - target) > Math.abs(succ.peek().val - target))){
                ret.add(succ.peek().val);
                getSucc(succ);
            }else{
                ret.add(pre.peek().val);
                getPre(pre);
            }
        }
        return ret;
    }
    private void initiateStack(TreeNode root , double target , Stack<TreeNode> pre , Stack<TreeNode> succ){
        while(root != null){
            if(root.val > target){
                succ.push(root);
                root = root.left;
            }else{
                pre.push(root);
                root = root.right;
            }
        }
    }
    private void getSucc(Stack<TreeNode> succ){
        TreeNode node = succ.pop();
        node = node.right;
        while(node != null){
            succ.push(node);
            node = node.left;
        }
    }
    private void getPre(Stack<TreeNode> pre){
        TreeNode node = pre.pop();
        node = node.left;
        while(node != null){
            pre.push(node);
            node = node.right;
        }
    }
}
