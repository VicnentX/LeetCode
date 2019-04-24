package Amazon.cv;

/*
given a set of N cards, A could be 1 or 14 , so we have 1-14 ,
check if there exists 5 continuous cards like 12345 , 65432
 */

public class ContinuousCards {
    public boolean checkExist (int[] nums) {

        int n = nums.length;

        int[] input1 = new int[n];
        for (int i = 0 ; i < n ; ++i) {
            if (nums[i] == 14) {
                input1[i] = 1;
            } else {
                input1[i] = nums[i];
            }
        }
        int[] input2 = new int[n];
        for (int i = 0 ; i < n ; ++i) {
            if (nums[i] == 1) {
                input2[i] = 14;
            } else {
                input2[i] = nums[i];
            }
        }

        if (check(input1) || check(input2)) {
            return true;
        }
        return false;
    }

    private boolean check (int[] nums) {
        int pattern = 0;
        int n = nums.length;
        int cnt = 1;
        for (int i = 1 ; i < n ; ++i) {
            if (pattern == 0 && nums[i] == nums[i - 1] + 1) {
                if (++cnt == 5) return true;
                pattern = 1;
            } else if (pattern == 0 && nums[i] == nums[i - 1] - 1) {
                if (++cnt == 5) return true;
                pattern = -1;
            } else if (pattern == 1 && nums[i] == nums[i - 1] + 1) {
                if (++cnt == 5) return true;
            } else if (pattern == -1 && nums[i] == nums[i - 1] - 1) {
                if (++cnt == 5) return true;
            } else {
                pattern = 0;
            }
        }
        return false;
    }

    public static void main (String[] args) {
        ContinuousCards cc = new ContinuousCards();
        //true
        System.out.println(cc.checkExist(new int[] {14,1,2,3,4,5}));
        //false
        System.out.println(cc.checkExist(new int[] {14,1,2,3,4,6}));
        //true
        System.out.println(cc.checkExist(new int[] {14,2,3,4,5,7}));
        //false
        System.out.println(cc.checkExist(new int[] {14,1,2,3,4,14}));
        //true
        System.out.println(cc.checkExist(new int[] {3,4,5,6,14,2,3,4,5}));
        //false
        System.out.println(cc.checkExist(new int[] {14,1,2,7,4,5}));
        //true
        System.out.println(cc.checkExist(new int[] {2,4,5,1,13,12,11,10}));
    }
}
