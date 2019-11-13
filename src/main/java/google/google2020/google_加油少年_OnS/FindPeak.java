package google.google2020.google_加油少年_OnS;

/*
第一题是一个数组，在某个位置前元素单调递增，
然后单调递减，就是那种元素大小像山一样形状的数组，
然后求最大最小值，用binary search做
 */

public class FindPeak {
    public int solve(int[] nums) {
        int start = 0;
        int end = nums.length - 1;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            int a = nums[mid - 1];
            int b = nums[mid];
            int c = nums[mid + 1];
            if (a < b && b > c) {
                return nums[mid];
            } else if (a < b && b < c) {
                start = mid;
            } else {
                end = mid;
            }
        }

        return Math.max(nums[start], nums[end]);
    }

    public static void main(String[] args) {
        FindPeak findPeak = new FindPeak();
        System.out.println(findPeak.solve(new int[] {1,2,3,5,6,7,9,7,6,5,3,1}));
    }
}
