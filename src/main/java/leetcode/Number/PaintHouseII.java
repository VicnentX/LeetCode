package leetcode.Number;



public class PaintHouseII {
    public int minCostII(int[][] costs) {
        int n = costs.length;
        int m = costs[0].length;
        for(int i = 1 ; i < n ; ++i){
            for(int j = 0 ; j < m ; ++j){
                int min = Integer.MAX_VALUE;
                for(int k = 0 ; k < m ; ++k){
                    if(k == j) continue;
                    min = Math.min(min , costs[i-1][k] + costs[i][j]);
                }
                costs[i][j] = min;
            }
        }
        int ret = Integer.MAX_VALUE;
        for(int i = 0 ; i < m ; ++i){
            ret = Math.min(ret , costs[n - 1][i]);
        }
        return ret;
    }

    public static void main(String[] args){
        PaintHouseII ph = new PaintHouseII();
        System.out.println(ph.minCostII(new int[][]{{1,5,3},{2,9,4}}));
    }
}
