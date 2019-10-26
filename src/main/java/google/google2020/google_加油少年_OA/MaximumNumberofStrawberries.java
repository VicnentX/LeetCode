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

    int max;

    public int maximumStrawBerriesPicked(int[] nums, final int MAX_SUM) {
        max = 0;
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

        //dp【i][j] 表示第i个可以拿可以不拿的情况下（能取到的就是0-i index之间的任何） 我包里 有的数量 《= j

        final int N = nums.length;

        int[][] dp = new int[N + 2][MAX_SUM + 1];

        for (int i = 0; i < N; ++i) {
            int value = nums[i];
            /**
             * 二位数组第二层循环 正过来倒过来是一样的 就只有节约空间的时候 正过来代表complete背包 反过来代表
             */
            for (int j = MAX_SUM; j >= 0; --j) {
                //不拿和拿两种情况
                //不拿的话就等于前一个可以取的最大值
                //拿的话前面那个就不能拿 所以= dp[i - 2][j - value] + value

                if (j >= value) {
                    dp[i + 2][j] = Math.max(dp[i + 1][j], dp[i][j - value] + value);
                }
                //如果当前的值比我包的体积大 就不能选这个 就只能用前一个的dp
                else {
                    dp[i + 2][j] = dp[i + 1][j];
                }
            }
        }
        //这里应该把0-maxSum都扫一遍得到最大的值
        return dp[N + 1][MAX_SUM];
    }

    /*

    PS：这题我就DIY出另一道题：
    别的条件都不变，就是limit的限制不是和的最大值，
    而是挑的元素的个数 《= limit。怎么做DP？
     */

    public int getMaxStrawberriesWithLimitBags(int[] nums, int limit) {
//        final int N = nums.length;
//
//        int[][] dp = new int[N + 2][limit + 1];
//
//        for (int i = 0; i < N; ++i) {
//            int value = nums[i];
//            for ()
//        }
        return 0;
    }



    public static void main(String[] args) {
        MaximumNumberofStrawberries maximumNumberofStrawberries = new MaximumNumberofStrawberries();
        int maxSum = maximumNumberofStrawberries.maximumStrawBerriesPicked
                (new int[] {1,3,2,9,5,7,40,11}, 23);
        System.out.println(maxSum);

        //21
        maxSum = maximumNumberofStrawberries.maximumStrawBerriesPicked
                (new int[] {1,45,45,9,45,56,40,11}, 23);
        System.out.println(maxSum);

        //21
        maxSum = maximumNumberofStrawberries.maximumStrawBerriesPickedDP
                (new int[] {1,45,45,9,45,56,40,11}, 23);
        System.out.println(maxSum);

        //6
        maxSum = maximumNumberofStrawberries.maximumStrawBerriesPickedDP
                (new int[] {2,0,0,2,0,2}, 6);
        System.out.println(maxSum);

        //3
        maxSum = maximumNumberofStrawberries.maximumStrawBerriesPickedDP
                (new int[] {0,0,2,3,0}, 6);
        System.out.println(maxSum);

    }

}
