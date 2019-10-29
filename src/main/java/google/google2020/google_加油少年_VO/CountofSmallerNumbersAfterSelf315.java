package google.google2020.google_加油少年_VO;


/*
You are given an integer array nums and you have to return a new counts array.
The counts array has the property
where counts[i] is the number of smaller elements to the right of nums[i].

Example:

Input: [5,2,6,1]
Output: [2,1,1,0]
Explanation:
To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
 */

/*
Every node will maintain a val sum recording the total of number on it's left bottom side,
dup counts the duplication. For example, [3, 2, 2, 6, 1], from back to beginning,
we would have:

                1(0, 1)
                     \
                     6(3, 1)
                     /
                   2(0, 2)
                       \
                        3(0, 1)
When we try to insert a number,
the total number of smaller number would be adding dup
and sum of the nodes where we turn right.
for example, if we insert 5, it should be inserted on the way down to the right of 3,
the nodes where we turn right is 1(0,1), 2,(0,2), 3(0,1),
so the answer should be (0 + 1)+(0 + 2)+ (0 + 1) = 4

if we insert 7, the right-turning nodes are 1(0,1), 6(3,1),
so answer should be (0 + 1) + (3 + 1) = 5
 */

/**
 *  这题有点难度 建立binary search tree的想法很好 值得细看
 */

import java.util.Arrays;
import java.util.List;

public class CountofSmallerNumbersAfterSelf315 {
    class Node {
        Node left, right;
        int val, sum, dup = 1;
        public Node(int v, int s) {
            val = v;
            sum = s;
        }
    }
    public List<Integer> countSmaller(int[] nums) {
        Integer[] ans = new Integer[nums.length];
        Node root = null;
        for (int i = nums.length - 1; i >= 0; i--) {
            root = insert(nums[i], root, ans, i, 0);
        }
        return Arrays.asList(ans);
    }
    private Node insert(int num, Node node, Integer[] ans, int i, int preSum) {
        if (node == null) {
            node = new Node(num, 0);
            ans[i] = preSum;
        } else if (node.val == num) {
            node.dup++;
            ans[i] = preSum + node.sum;
        } else if (node.val > num) {
            node.sum++;
            node.left = insert(num, node.left, ans, i, preSum);
        } else {
            node.right = insert(num, node.right, ans, i, preSum + node.dup + node.sum);
        }
        return node;
    }
}
