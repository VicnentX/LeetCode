package google.google2020.google_加油少年_OnS;

/*
利口起灵，比那个稍微难点，一次不是跳两级，
是跳k级，给一个k，一个n，n是总台阶数，
k是每次最多跳的个数，可以比它少。
我一上来说dp，给了个时间复杂度 O(kn)，空间O(n)的。
他说能不能简化一下空间。我说那我给了个时间kn, 空间k的。
最后讨论了一阵子我简化成了时间n，空间min(n,k)的。
就是不用每次都把那k个加一遍，保持一个sum就好了，
每次删除一个旧的再加一个新的。
 */

public class ClimbingStairs {
    public int climb(int n, int k) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        int sum = 0;
        for (int i = 1; i <= n; ++i) {
            sum += dp[i - 1];
            sum -= i - k - 1 < 0 ? 0 : dp[i - k - 1];
            dp[i] = sum;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        ClimbingStairs climbingStairs = new ClimbingStairs();
        //5
        System.out.println(climbingStairs.climb(5, 1));
        //89
        System.out.println(climbingStairs.climb(10, 2));
        //274
        System.out.println(climbingStairs.climb(10, 3));
    }
}
