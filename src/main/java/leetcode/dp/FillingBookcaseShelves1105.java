package leetcode.dp;

/*
We have a sequence of books: the i-th book has thickness books[i][0] and height books[i][1].

We want to place these books in order onto bookcase shelves that have total width shelf_width.

We choose some of the books to place on this shelf (such that the sum of their thickness is <= shelf_width), then build another level of shelf of the bookcase so that the total height of the bookcase has increased by the maximum height of the books we just put down.  We repeat this process until there are no more books to place.

Note again that at each step of the above process, the order of the books we place is the same order as the given sequence of books.  For example, if we have an ordered list of 5 books, we might place the first and second book onto the first shelf, the third book on the second shelf, and the fourth and fifth book on the last shelf.

Return the minimum possible height that the total bookshelf can be after placing shelves in this manner.



Example 1:


Input: books = [[1,1],[2,3],[2,3],[1,1],[1,1],[1,1],[1,2]], shelf_width = 4
Output: 6
Explanation:
The sum of the heights of the 3 shelves are 1 + 3 + 2 = 6.
Notice that book number 2 does not have to be on the first shelf.


Constraints:

1 <= books.length <= 1000
1 <= books[i][0] <= shelf_width <= 1000
1 <= books[i][1] <= 1000
 */


/*
题意： 给n本书，每本书有一个厚度和高度，摆在书架上。
书架每一层最大宽度是shelf_width，摆不下时可以摆在下一层。
每层的高度为当前层所有书的最大高度，问书的总高度最小时多少。

思路：经典的动态规划题。
定义dp[i]为前i本书能够到达的最小高度。
则对于第i+1本书，有若干选择。
如自己单独一层，则状态转移为dp[i+1] = dp[i] + h[i+1]
如果和前面的书放在一起，则状态转移方程式dp[i+1] = min(dp[j] + max[h[j+1] ~ h[i+1])), 其中需要满足sum(w[j+1] ~ w[i+1]) <= shelf_width，含义是前j本书组成若干层，第j+1到第i+1本书组成一层。
对于这些选择，取最小值。
 */
public class FillingBookcaseShelves1105 {
    public int minHeightShelves(int[][] books, int shelfWidth) {
        int n = books.length;
        int[] dp = new int[n + 1];
        for (int i = 1 ; i <= n ; ++i) {
            int curw = books[i - 1][0];
            int curh = books[i - 1][1];
            dp[i] = dp[i - 1] + curh;
            for (int j = i - 1 ; j > 0 ; j--) {
                curw += books[j - 1][0];
                curh = Math.max(curh, books[j - 1][1]);
                if (curw > shelfWidth) break;
                dp[i] = Math.min(dp[i], dp[j - 1] + curh);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        FillingBookcaseShelves1105 fb = new FillingBookcaseShelves1105();
        int ret = fb.minHeightShelves(new int[][]{{1,1},{2,3},{2,3},{1,1},{1,1},{1,1},{1,2}},4 );
        System.out.println(ret);
    }
}
