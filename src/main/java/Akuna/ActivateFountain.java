package Akuna;

/**
 * As you can see, in above code, we try to choose the first fountain which can cover as much right as possible, and then once we found a garden
 * which is not cover by this fountain, then we update this fountain range and increase the number of fountain.
 * <p>
 * To do so, we need to know from each index, how far we can go.
 * Hence at each index we'll store the max right possible from this index.
 * <p>
 * *1 2 3 4 5 6
 * [2,1,2,1,2,4] n = 6
 * [(1,3),(1,3),(1,5),(3,5),(3,6), (2,6)]
 * <p>
 * [1->5, 2->6, 3->6, 4->0, 5->0, 6->0] => [5,6,6,0,0,0] => 2
 * <p>
 * O(n)
 * <p>
 * It turns out that after the pre-processing, it is same as below problem
 * {@link MinimumJumpToReachLastJumpGame}
 */

public class ActivateFountain {
    /**
     * *1 2 3 4 5  6
     * [2,1,2,1,2,4] n = 6
     * <p>
     * [(1,3),(1,3),(1,5),(3,5),(3,6), (2,6)]
     * <p>
     *
     * @param fountains
     * @return
     */
    public int activateFountains(int[] fountains) {
        final int GARDEN_LENGTH = fountains.length;
        int[] interval = new int[GARDEN_LENGTH];

        for (int i = 1; i <= GARDEN_LENGTH; ++i) {
            int left = Math.max(i - fountains[i - 1], 1);
            int right = Math.min(i + fountains[i - 1], GARDEN_LENGTH);
            interval[left - 1] = Math.max(interval[left - 1], right);
        }

        int right = interval[0];
        int nextGreaterRight = right;
        int fountainsActivate = 1;

        for (int i = 1; i < GARDEN_LENGTH; ++i) {
            nextGreaterRight = Math.max(nextGreaterRight, interval[i]);
            /**
             * If the last fountain can cover only this point, then update with next fountain.
             */
            if (i == right) {
                fountainsActivate++;
                right = nextGreaterRight;
            }
        }

        return fountainsActivate;
    }

    public static void main(String[] args) {
        ActivateFountain activateFountain = new ActivateFountain();
        System.out.println(activateFountain.activateFountains(new int[] {1,2,1}));
    }
}
