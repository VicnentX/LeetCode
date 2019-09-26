package google;

public class Solu {
    public int sum(int[] nums) {
        int cnt = 0;
        int sum = 0;
        int max = 0;

        for(int i = 0 ; i < nums.length; ++i) {
            if (cnt == 5) {
                max = Math.max(max, sum);
                sum = sum - nums[i - 5];
                sum += nums[i];
            } else {
                sum += nums[i];
                cnt++;
            }
        }

        return max;
    }



    public static void main(String[] args) {
        Solu s = new Solu();
        System.out.println(s.sum(new int[] {-5,-19,0,-20,-11,12,27,-16,-2,-2,23,0,-3,4,7,-1,-28,18,21,17,-23,9,2,-19,8}));
    }
}
