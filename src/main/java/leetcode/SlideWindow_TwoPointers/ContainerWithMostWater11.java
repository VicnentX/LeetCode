package leetcode.SlideWindow_TwoPointers;

/*
Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container and n is at least 2.





The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.



Example:

Input: [1,8,6,2,5,4,8,3,7]
Output: 49
 */
public class ContainerWithMostWater11 {
    public int maxArea(int[] height) {
        if (height == null || height.length == 0) return 0;
        int n = height.length;
        int ret = 0;
        int i = 0;
        int j = n - 1;
        while (i < j) {
            ret = Math.max(ret , Math.min(height[i] , height[j]) * (j - i));
            if (height[i] < height[j]) {
                ++i;
            } else {
                --j;
            }
        }
        return ret;
    }

    public static void main (String[] args) {
        ContainerWithMostWater11 cw = new ContainerWithMostWater11();
        //Input: [1,8,6,2,5,4,8,3,7]
        //Output: 49

        System.out.println(cw.maxArea(new int[] {1,8,6,2,5,4,8,3,7}));
    }
}
