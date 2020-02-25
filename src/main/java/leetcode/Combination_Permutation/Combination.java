package leetcode.Combination_Permutation;

public class Combination {
    //这里我就不写push pull的版本，写一种填充数组的版本，这种benchmark绝对速度更快一些
    public void solve(int[] nums) {
        int n = nums.length;
        for(int i = 0; i <= n; ++i) {
            dfs(i, 0,0, new int[i], n, nums);
        }
    }

    private void dfs(int size, int curSize, int start, int[] array, int n, int[] nums) {
        if (curSize == size) {
            printArray(array);
            return;
        }

        for(int i = start; i < n; ++i) {
            array[curSize] = nums[i];
            dfs(size, curSize + 1, i + 1, array, n, nums);
            //下面这步写不写都可以，都会被覆盖的
            //array[i] = 0;
        }

    }

    private void printArray(int[] nums) {
        for(int num: nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Combination combination = new Combination();
        combination.solve(new int[] {1,2,3,4});
    }
}
