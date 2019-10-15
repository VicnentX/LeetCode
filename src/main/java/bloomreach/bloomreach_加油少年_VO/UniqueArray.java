package bloomreach.bloomreach_加油少年_VO;

/*
Given an array of unique integers in increasing order,
return the integer with its value equal to its index in the array
 */

/**
 *  这里应该是严格递增 否则就只能扫一遍
 */

public class UniqueArray {

    //binary search
    public int findIndexEqualsValue(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == mid) return mid;
            if (nums[mid] > mid) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (nums[start] == start) {
            return start;
        }
        if (nums[end] == end) {
            return end;
        }
        return -1;
    }

    public static void main(String[] args) {
        UniqueArray uniqueArray = new UniqueArray();
        //return -1
        System.out.println(uniqueArray.findIndexEqualsValue(new int[] {1,4,5,7,9}));
        //return 7
        System.out.println(uniqueArray.findIndexEqualsValue(new int[] {-1,0,1,2,3,4,5,7,9}));

    }

}
