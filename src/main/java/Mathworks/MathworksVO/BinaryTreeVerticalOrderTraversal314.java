package Mathworks.MathworksVO;

/*
Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.

Examples 1:

Input: [3,9,20,null,null,15,7]

   3
  /\
 /  \
 9  20
    /\
   /  \
  15   7

Output:

[
  [9],
  [3,15],
  [20],
  [7]
]
Examples 2:

Input: [3,9,8,4,0,1,7]

     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7

Output:

[
  [4],
  [9],
  [3,0,1],
  [8],
  [7]
]
Examples 3:

Input: [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5)

     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
    /\
   /  \
   5   2

Output:

[
  [4],
  [9,5],
  [3,0,1],
  [8,2],
  [7]
]
 */

import java.util.*;

public class BinaryTreeVerticalOrderTraversal314 {

    public class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode (int x) {
            val = x;
        }
    }

    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) return results;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        Map<Integer, List<Integer>> map = new HashMap<>();
        LinkedList<Position> queue = new LinkedList<>();
        queue.add(new Position(root, 0));
        while (!queue.isEmpty()) {
            Position position = queue.remove();
            min = Math.min(min, position.col);
            max = Math.max(max, position.col);
            List<Integer> list = map.get(position.col);
            if (list == null) {
                list = new ArrayList<>();
                map.put(position.col, list);
            }
            list.add(position.node.val);
            if (position.node.left != null) queue.add(new Position(position.node.left, position.col-1));
            if (position.node.right != null) queue.add(new Position(position.node.right, position.col+1));
        }
        for(int i=min; i<=max; i++) {
            List<Integer> list = map.get(i);
            if (list != null) results.add(list);
        }
        return results;
    }

    private class Position {
        TreeNode node;
        int col;
        Position (TreeNode node, int col) {
            this.node = node;
            this.col = col;
        }
    }

}
