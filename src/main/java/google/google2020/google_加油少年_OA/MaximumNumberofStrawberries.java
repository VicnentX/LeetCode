package google.google2020.google_加油少年_OA;

/*
a robot wants to pick strawberries from a strawberry bush.
you are given an integer array arr of length n
and an integer num as input
where num is the number of strawberries
a robot will pick as maximum.
Each array element in arr represents
the number of strawberries present in each bush
and n is the number of bushes.
A robot cannot pick strawberries from two consecutive bushes
and it has to pick the strawberries in such a way
that it may not exceed the limit proposed
i.e., num. Calculate the maximum number of strawberries
a robot can pick and print the same as output.


output should be a single integer.

 */

/**
 * 首先这里的最大限制是对于水果的 不是对于袋子的
 * 就是给一个array，
 * 然后给一个limit，
 * 我从array里面挑选不限个数的元素，
 * 把元素的和加起来要最大，但是不能比我的limit大，。。。
 * 还有一个附加限制，这个最优结果里面，不能包含相邻元素。
 */

public class MaximumNumberofStrawberries {

    /**
     * 这里我先用一个dfs的方法
     * @param nums
     * @param MAX_SUM
     * @return
     */

    int max = 0;

    public int maximumStrawBerriesPicked(int[] nums, final int MAX_SUM) {
        dfs(0, false, 0, nums, MAX_SUM);
        return max;
    }

    private void dfs(int i, boolean prePicked, int sum, final int[] nums, final int MAX_SUM) {
        if (sum > MAX_SUM) return;
        max = Math.max(max, sum);
        if (i >= nums.length) return;
        dfs(i + 1, false, sum, nums, MAX_SUM);
        if (!prePicked) {
            dfs(i + 1, true, sum + nums[i], nums, MAX_SUM);
        }
    }


    /**
     *  这题的dp解法
     */
    public int maximumStrawBerriesPickedDP(int[] nums, final int MAX_SUM) {
        return 0;
    }



    /*

    PS：这题我就DIY出另一道题：
    别的条件都不变，就是limit的限制不是和的最大值，
    而是挑的元素的个数 《= limit。怎么做DP？
     */



    public static void main(String[] args) {
        MaximumNumberofStrawberries maximumNumberofStrawberries = new MaximumNumberofStrawberries();
        int maxSum = maximumNumberofStrawberries.maximumStrawBerriesPicked
                (new int[] {1,3,2,9,5,7,40,11}, 23);
        System.out.println(maxSum);
    }

}
