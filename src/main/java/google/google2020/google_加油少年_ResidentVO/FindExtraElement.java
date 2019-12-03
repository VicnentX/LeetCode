package google.google2020.google_加油少年_ResidentVO;

/*
给两个数字array，这两个数组里的内容基本一样，
顺序也一样 只不过其中一个数组比另一个数组多了一个数字（
这个数字可以在任何位置）找出这个多余的数字
 */

public class FindExtraElement {
    public int solve(int[] nums1, int[] nums2) {
        if (nums1.length == 0 && nums2.length == 0) return -1;
        int i = 0;
        int j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] != nums2[j]) {
                return nums1.length > nums2.length ? nums1[i] : nums2[j];
            }
            ++i;
            ++j;
        }
        return nums1.length > nums2.length ? nums1[i] : nums2[j];
    }

    //这题可以是二分法来处理
    //这题要求出现不一样数字之后 后面所有的数字都是不一样的 ！！！！！！！！！不然不能用
    public int solveBinarySeaarch(int[] nums1, int[] nums2) {
        int n = Math.min(nums1.length, nums2.length);
        int start = 0;
        int end = n - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums1[mid] != nums2[mid]) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (nums1[start] != nums2[start]) {
            return nums1.length > nums2.length ? nums1[start] : nums2[start];
        }
        if (nums1[end] != nums2[end]) {
            return nums1.length > nums2.length ? nums1[end] : nums2[end];
        }
        return nums1.length > nums2.length ? nums1[end + 1] : nums2[end + 1];
    }

    public static void main(String[] args) {
        FindExtraElement findExtraElement = new FindExtraElement();
        System.out.println(findExtraElement.solve(new int[] {1,2,3,4,5,6,7}, new int[] {1,2,3,4,5,66,6,7}));
        System.out.println(findExtraElement.solveBinarySeaarch(new int[] {1,2,3,4,5,6,7}, new int[] {1,2,3,4,5,66,6,7}));
        System.out.println(findExtraElement.solveBinarySeaarch(new int[] {1,2,3,4,5}, new int[] {1,2,3,4}));
    }
}
