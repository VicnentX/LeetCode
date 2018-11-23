package leetcode.dp;

/*
There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

Example:

Input: [[1,5,3],[2,9,4]]
Output: 5
Explanation: Paint house 0 into color 0, paint house 1 into color 2. Minimum cost: 1 + 4 = 5;
             Or paint house 0 into color 2, paint house 1 into color 0. Minimum cost: 3 + 2 = 5.
Follow up:
Could you solve it in O(nk) runtime?
 */

public class PaintHouseII265 {
    public int minCostII(int[][] costs) {
        if(costs == null || costs.length == 0 || costs[0].length == 0) return 0;
        if(costs[0].length == 1 && costs.length > 1) return 0;
        int n = costs.length;
        int k = costs[0].length;

        int preMin = 0;
        int preMinInd = -1;
        int preSecMin = 0;
        for(int i = 0 ; i < n ; ++i){
            int min = Integer.MAX_VALUE;
            int minInd = -1;
            int secMin = Integer.MAX_VALUE;
            for(int j = 0 ; j < k ; ++j){
                int val = costs[i][j] + (j == preMinInd ? preSecMin : preMin);
                if(val < min){
                    secMin = min;
                    min = val;
                    minInd = j;
                }else if(val < secMin){
                    secMin = val;
                }
            }
            preMin = min;
            preMinInd = minInd;
            preSecMin = secMin;
        }
        return preMin;
    }
}
