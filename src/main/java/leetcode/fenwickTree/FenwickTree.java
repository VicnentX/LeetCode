package leetcode.fenwickTree;

public class FenwickTree {
    int[] nums;

    public FenwickTree(int n) {
        //there is n node in the tree
        nums = new int[n + 1];
    }

    public void update(int i, int delta) {
        //update ith node with delta
        //第0个元素应该始终没有被使用
        while (i < nums.length) {
            nums[i] += delta;
            //这里i每次都加上他的最低位为1的值 比如5 = 101 就加上1 得到6 = 110； 如果是 6 = 110就加上10得到1000就是8
            i += (i & -i);
        }
    }

    public int query(int i) {
        int sum = 0;
        while(i > 0) {
            sum += nums[i];
            i -= (i & -i);
        }
        return sum;
    }

    public static void main(String[] args) {
        FenwickTree fenwickTree = new FenwickTree(8);
        for(int i = 1; i <= 8; ++i) {
            fenwickTree.update(i, i);
        }
        //比如我要得到第1到第3个的值 = 6
        System.out.println(fenwickTree.query(3) - fenwickTree.query(0));
    }
}
