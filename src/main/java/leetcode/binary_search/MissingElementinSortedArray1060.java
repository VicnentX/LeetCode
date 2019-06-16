package leetcode.binary_search;

public class MissingElementinSortedArray1060 {
    public int missingElement(int[] nums, int k) {
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            int numMiss = nums[mid] - nums[start] - (mid - start);
            if (k <= numMiss) {
                end = mid;
            } else {
                start = mid;
                k -= numMiss;
            }
        }
        // At this point we will reach a specific interval [nums[left], nums[right]]
        // it is very possible that the target we are looking for is beyond this interval
        // For example, [1,2] and we want k = 100
        int len = nums[end] - nums[start] - (end - start);
        if (k <= len) {
            return nums[start] + k;
        } else {
            return nums[end] + k - len;
        }
    }

}
