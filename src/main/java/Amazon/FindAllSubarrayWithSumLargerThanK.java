package Amazon;

public class FindAllSubarrayWithSumLargerThanK {
    public int findnumbers(int[] nums , int k) {
        int n = nums.length;
        int ret = 0;
        int sum = nums[0];
        int i = 0;
        int j = 0;
        while(j < n) {
            if (sum < k) {
                ++j;
                if (j == n) break;
                sum += nums[j];
            } else {
                ret += n - 1 - j + 1;
                sum -= nums[i++];
            }
        }
        return ret;
    }
    public static void main (String[] args) {
        FindAllSubarrayWithSumLargerThanK fs= new FindAllSubarrayWithSumLargerThanK();
        System.out.println(fs.findnumbers(new int[] {1,2,3,4,5} , 5));
        System.out.println(fs.findnumbers(new int[] {6,6,6} , 5));
    }
}
