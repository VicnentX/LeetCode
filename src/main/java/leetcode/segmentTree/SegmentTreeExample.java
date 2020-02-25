package leetcode.segmentTree;

public class SegmentTreeExample {

    private int[] nums;

    public class SegmentTreeNode {
        int start, end, sum;
        SegmentTreeNode left, right;

        public SegmentTreeNode(int start, int end, int sum) {
            this.start = start;
            this.end = end;
            this.sum = sum;
        }

        public SegmentTreeNode(int start, int end, int sum, SegmentTreeNode left, SegmentTreeNode right) {
            this.start = start;
            this.end = end;
            this.sum = sum;
            this.left = left;
            this.right = right;
        }
    }

    public SegmentTreeNode buildTree(int start, int end, int[] nums) {
         if (start == end) {
             return new SegmentTreeNode(start, end, nums[start]);
         }

         int mid = start + (end - start) / 2;
         SegmentTreeNode left = buildTree(start, mid, nums);
         SegmentTreeNode right = buildTree(mid + 1, end, nums);
         return new SegmentTreeNode(start, end, left.sum + right.sum, left, right);
    }

    public void updateTree(SegmentTreeNode root, int index, int newValue) {
        if (root.start == root.end && root.start == index) {
            root.sum = newValue;
            return;
        }
        int mid = root.start + (root.end - root.start) / 2;
        if (index <= mid) {
            updateTree(root.left, index, newValue);
        } else {
            updateTree(root.right, index, newValue);
        }
        root.sum = root.left.sum + root.right.sum;
    }

    public int querySum(SegmentTreeNode root, int i, int j) {
        if (root.start == i && root.end == j) {
            return root.sum;
        }
        int mid = root.start + (root.end - root.start) / 2;
        if (j <= mid) {
            return querySum(root.left, i, j);
        } else if (i > mid) {
            return querySum(root.right, i, j);
        } else {
            return querySum(root.left, i, mid) + querySum(root.right, mid + 1, j);
        }
    }
}
