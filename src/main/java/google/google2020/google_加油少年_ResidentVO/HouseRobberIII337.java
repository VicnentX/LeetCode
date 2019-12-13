package google.google2020.google_加油少年_ResidentVO;

/*
dfs all the nodes of the tree, each node return two number,
int[] num, num[0] is the max value while rob this node,
num[1] is max value while not rob this value.
Current node return value only depend on its children's value.
Transform function should be very easy to understand.
 */

public class HouseRobberIII337 {

    public class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) {
            val = x;
        }
    }

    public int HouseRobberIII337(TreeNode root) {
        int[] nums = dfs(root);
        return Math.max(nums[0], nums[1]);
    }
    //return 的两个值 第一个是占用当前点可以拿到的最大值， 第二个是不占用当前点的最大值
    private int[] dfs(TreeNode node) {
        if (node == null) return new int[2];
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);
        int num0 = node.val + left[1] + right[1];
        int num1 = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return new int[] {num0, num1};
    }
}
