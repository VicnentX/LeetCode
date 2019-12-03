package google.google2020.google_加油少年_OnS;

/*
Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

Example:

Given the sorted linked list: [-10,-3,0,5,9],

One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

      0
     / \
   -3   9
   /   /
 -10  5
Seen this question in a real interview before?

 */

public class ConvertSortedListtoBinarySearchTree109 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        return dfs(head, null);
    }

    //tail 是取不到的值

    //nlogn

    private TreeNode dfs(ListNode head, ListNode tail) {
        ListNode slow = head;
        ListNode fast = head;
        if (head == tail) return null;

        while (fast != tail && fast.next != tail) {
            slow = slow.next;
            fast = fast.next.next;
        }
        TreeNode node = new TreeNode(slow.val);
        node.left = dfs(head, slow);
        node.right = dfs(slow.next, tail);
        return node;
    }

}
