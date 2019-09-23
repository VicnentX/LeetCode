package PEAK6;

import java.util.Arrays;
import java.util.List;

/**
 * 飞机到达时间 起飞时间 都是排好序的
 * 最大的等待时间在进入gate之前
 * 厨师情况有i个gate被占用
 * 问需要几个gate总共
 *
 *
 * Inputs (full example below)
 * - list of times when a plane will land (given sorted)
 * - list of times when a plane will take-off (given sorted)
 * - maximum time (in minutes) a plane can wait on the runway before it must go to a gate
 * - the initial number of planes occupying gates at the beginning of the day
 * Outputs (full example below)
 * Your job is to determine the minimum number of gates needed to ensure that no planes wait on the runway longer than the maxWaitTime before going to a gate. For the sake of this problem, you should assume the runway is of infinite length and planes will land and take-off at the exact time indicated.
 * Notes
 * Times will be given in 24-hour clock notation (e.g., 16:43 means 4:43pm)
 * Times will be given as integers (e.g., 935 means 9:35), and they will be valid times given between 0 and 2359 (12:00am to 11:59pm or 0:00 to 23:59). You can assume you will not be given malformed input (i.e., the last 2 digits will be less than 60)
 *  Airport Construction
 *   1 2
 *    landingTimes
 *   takeOffTimes
 *   maxWaitTime
 *   initialPlanes
 *
 *
 * landingTimes and will be given to you sorted
 * Duplicate times in     are valid, and means that multiple planes will be landing at that time (same for Planes must land at least 1 minute before they take-off. i.e., if a time appears in landingTimes and
 * from the one taking-off
 * As soon as a plane takes-off, its gate immediately becomes available for another plane
 *
 *
 * //
 *
 * //
 *
 *
 */

public class MaxGateCnt {
    public int findGateCnt(List<Integer> landingTime, List<Integer> takeOffTime, int maxWaitTime, int initial) {
        int max = initial;
        int gateCnt = initial;

        int i = 0, j = 0;
        while (i < landingTime.size() && j < takeOffTime.size()) {
            if (standardTime(landingTime.get(i)) + maxWaitTime < standardTime(takeOffTime.get(j))) {
                ++gateCnt;
                max = Math.max(max, gateCnt);
                ++i;
            } else {
                --gateCnt;
                ++j;
            }
        }

        if (i < landingTime.size()) {
            gateCnt += landingTime.size() - i;
            max = Math.max(max, gateCnt);
        }

        return max;

    }

    private int standardTime(int time) {
        return time / 100 * 60 + (time % 100);
    }

    public static void main(String[] args) {
        MaxGateCnt maxGateCnt = new MaxGateCnt();

        //case 2
        System.out.println(maxGateCnt.findGateCnt(
                Arrays.asList(340,1240,1250,1600,1715,1832,2204),
                Arrays.asList(1144,1305,1318,1612,1801,2141,2300),
                0,
                0
        ));

        //case 8
        System.out.println(maxGateCnt.findGateCnt(
                Arrays.asList(346,418,641,1834,2030,2035,2257,2343),
                Arrays.asList(609,755,1637,2018),
                23,
                2
        ));
    }
}
