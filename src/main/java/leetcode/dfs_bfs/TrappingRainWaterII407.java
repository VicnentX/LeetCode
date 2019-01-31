package leetcode.dfs_bfs;

/*
Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map, compute the volume of water it is able to trap after raining.



Note:

Both m and n are less than 110. The height of each unit cell is greater than 0 and is less than 20,000.



Example:

Given the following 3x6 height map:
[
  [1,4,3,1,3,2],
  [3,2,1,3,2,4],
  [2,3,3,2,3,1]
]

Return 4.


The above image represents the elevation map [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]] before the rain.





After the rain, water is trapped between the blocks. The total volume of water trapped is 4.
 */

import  java.util.*;

public class TrappingRainWaterII407 {
    public int trapRainWater(int[][] h) {
        int[][] dirs = {{-1 , 0} , {1 , 0} , {0 , -1} , {0 , 1}};
        PriorityQueue<int[]> heap = new PriorityQueue<>((a , b) -> a[2] - b[2]);
        int ret = 0;
        if (h.length == 0 || h[0].length == 0) return ret;
        int m = h.length;
        int n = h[0].length;
        int tem = -1;
        //fill the heap
        for (int i = 0 ; i < n ; ++i) {
            tem = h[0][i];
            heap.add(new int[] {0 , i , tem});
            h[0][i] = tem == 0 ? Integer.MIN_VALUE : -tem;
            tem = h[m - 1][i];
            heap.add(new int[] {m - 1 , i , tem});
            h[m - 1][i] = tem == 0 ? Integer.MIN_VALUE : -tem;
        }
        for (int i = 1 ; i < m - 1 ; ++i) {
            tem = h[i][0];
            heap.add(new int[] {i , 0 , tem});
            h[i][0] = tem == 0 ? Integer.MIN_VALUE : -tem;
            tem = h[i][n - 1];
            heap.add(new int[] {i , n - 1 , tem});
            h[i][n - 1] = tem == 0 ? Integer.MIN_VALUE : -tem;
        }
        //calculate the ret;
        int max = Integer.MIN_VALUE;
        while(!heap.isEmpty()) {
            int[] cur = heap.poll();
            max = Math.max(max , cur[2]);
            for (int dir[] : dirs) {
                int i = cur[0] + dir[0];
                int j = cur[1] + dir[1];
                if (i >= 0 && i < m && j >= 0 && j < n && h[i][j] >= 0) {
                    tem = h[i][j];
                    if (tem < max) {
                        ret += (max - tem);
                    }
                    heap.add(new int[] {i , j , tem});
                    h[i][j] = tem == 0 ? Integer.MIN_VALUE : -tem;
                }
            }
        }
        return ret;
    }

    public static void main (String[] args) {
        TrappingRainWaterII407 tr = new TrappingRainWaterII407();
        System.out.println(tr.trapRainWater(new int[][] {
                {1,4,3,1,3,2},{3,2,1,3,2,4},{2,3,3,2,3,1}
        }));
    }
}
