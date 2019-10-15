package IBM;

/*
这题就是只能相邻换 要把1011001 变成 0001111要最少多少次换
 */

/**
 * 我的理解就是先换最前面的0 然后换后面的 这样就剩下重复换的事情
 *
 * 我的start就是开始要换的 是index = 0
 * 每换了一次 start + 1
 *
 * 每次换的成本是 index of 0 - start
 */

public class SwapNeighbor {

    public int getMinMove(String s) {
        return Math.min(countCostToSwap(s), countCostToSwap(new StringBuilder(s).reverse().toString()));
    }

    private int countCostToSwap(String s) {
        int cnt = 0;
        int start = 0;

        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '0') {
                cnt += (i - start);
                start++;
            }
        }

        return cnt;
    }

    public static void main(String[] args) {
        SwapNeighbor swapNeighbor = new SwapNeighbor();
        //4
        System.out.println(swapNeighbor.getMinMove("10000111"));
        //0
        System.out.println(swapNeighbor.getMinMove("11110000"));
    }
}
