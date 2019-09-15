package Amazon.full_time2020;

/**
 * 2. 飞机旅行问题。 给一个里程上限和两个list，一个是forward route的里程，一‍‍‍‌‌‌‍‌‍‌‌‍‍‍‍‌‌‌‍个是return route的里程。要求在里程上线的范围内找到一对forward route和return route的pair，使得这个pair的和最大。
 *
 * Input: int MaxTravelLimit, 两个list
 * Output: 最大的一对或多对pair。
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TravelLimit {
    public List<List<Integer>> maxTravelLimit(List<Integer> departs, List<Integer> returns, int limit) {
        Collections.sort(departs);
        Collections.sort(returns);
        List<List<Integer>> ret = new ArrayList<>();
        int i = 0, j = returns.size() - 1;
        int max = 0;

        while (i < departs.size() && j >= 0) {
            int tripSum = departs.get(i) + returns.get(j);
            if (tripSum <= limit) {
                if (tripSum == max) {
                    ret.add(Arrays.asList(departs.get(i), returns.get(j)));
                }
                else if (tripSum > max) {
                    ret.clear();
                    ret.add(Arrays.asList(departs.get(i), returns.get(j)));
                    max = tripSum;
                }
                ++i;
            } else {
                --j;
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        TravelLimit travelLimit = new TravelLimit();
        List<List<Integer>> ret = travelLimit.maxTravelLimit(Arrays.asList(1,3,5), Arrays.asList(5,6,3), 6);
        for (List<Integer> pair: ret) {
            System.out.println(pair.get(0) + "  " + pair.get(1));
        }
    }
}
