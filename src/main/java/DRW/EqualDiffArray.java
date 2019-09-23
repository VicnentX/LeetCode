package DRW;

/**
 * 找等差数列 所有大于等于三的子集的数量的sum
 */

public class EqualDiffArray {
    public int findCntSum(int[] A) {
        int ARRAY_LENGTH = A.length;
        int periodsCount = 0;

        for (int i = 1 ; i < ARRAY_LENGTH - 1; ++i) {
            int[] pairOfCntAndI = find(A, i, ARRAY_LENGTH);
            periodsCount += pairOfCntAndI[0];
            i = pairOfCntAndI[1];
        }

        return periodsCount;
    }

    private int[] find(int[] nums, int i, int n) {
        int localCnt = 2;

        while (i < n - 1 && nums[i] - nums[i - 1] == nums[i + 1] - nums[i]) {
            ++localCnt;
            ++i;
        }

        return new int[] {(localCnt - 1) * (localCnt - 2) / 2, i};
    }

    public static void main(String[] args) {
        EqualDiffArray equalDiffArray = new EqualDiffArray();
        System.out.println(equalDiffArray.findCntSum(new int[] {-1,1,3,3,3,2,3,2,1,0}));
    }
}
