package google.google2020.google_加油少年_VO;


/**
 * 这个nums是sorted过的
 */

public class FindNNearestElementsInSortedList {

    public int[] findNElements(int[] nums, int inserted, int n) {
        //这center是正好大于等于ta一点点的那个
        int center = findCenter(nums, inserted);
        int index = 0;
        int j = center;
        int i = center - 1;
        int[] ret = new int[n];
        while (index < n) {
            if (j == nums.length) {
                ret[index] = nums[i];
                --i;
            } else if (i == -1) {
                ret[index] = nums[j];
                ++j;
            } else {
                if (Math.abs(nums[i] - inserted) < Math.abs(nums[j] - inserted)) {
                    ret[index] = nums[i];
                    --i;
                } else {
                    ret[index] = nums[j];
                    ++j;
                }
            }
            index++;
        }
        return ret;
    }

    private int findCenter(int[] nums, int inserted) {
        final int N = nums.length;
        int start = 0;
        int end = N - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] <= inserted) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (nums[start] >= inserted) return start;
        return end;
    }

    public static void main(String[] args) {
        FindNNearestElementsInSortedList findNNearestElementsInSortedList = new FindNNearestElementsInSortedList();
        for (int i: findNNearestElementsInSortedList.findNElements(new int[] {1, 2, 5, 6, 17, 20, 21}, 12, 3)) {
            System.out.println(i);
        }
    }

}
