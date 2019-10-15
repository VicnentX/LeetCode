package bloomreach.bloomreach_加油少年_VO;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
找not cover的interval
// (1, 5)
// (2, 4)
// (4, 7)
// (10, 13)

    // =>
    //   (7, 10)
 */
public class IntervalNotCovered {

    public List<int[]> getUncoveredInterval(int[][] intervals) {
        List<int[]> ret = new ArrayList<>();
        //each pair store time and its type(start or end); start = 1, end = -1;
        int M = intervals.length;
        int[][] pairs = new int[2 * M][2];
        int index = 0;
        for (int[] interval: intervals) {
            pairs[index][0] = interval[0];
            pairs[index++][1] = 1;
            pairs[index][0] = interval[1];
            pairs[index++][1] = -1;
        }

        Arrays.sort(pairs, (a,b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        int pre = pairs[0][0];
        int sum = 0;
        for (int[] pair: pairs) {
            if (sum == 0 && pre < pair[0]) {
                ret.add(new int[] {pre, pair[0]});
            }
            sum += pair[1];
            pre = pair[0];
        }

        return ret;
    }

    public static void main(String[] args) {
        IntervalNotCovered intervalNotCovered = new IntervalNotCovered();
        for (int[] pair: intervalNotCovered.getUncoveredInterval(new int[][] {{1,5},{2,4},{4,7},{10,13}})) {
            System.out.println(pair[0] + "-" + pair[1]);
        }
    }

}
