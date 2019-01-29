package leetcode.Sort;

/*
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

Example 1:

Input: [[0, 30],[5, 10],[15, 20]]
Output: 2
Example 2:

Input: [[7,10],[2,4]]
Output: 1
 */

import java.util.*;
public class MeetingRoomsII253 {
    public class Interval {
        int start , end;
        Interval(){
            start = 0;
            end = 0;
        }
        Interval(int s , int e) {
            start = s;
            end = e;
        }
    }

    public int minMeetingRooms(Interval[] intervals) {
        int ret = 0;
        int cur = 0;
        if (intervals.length == 0) return ret;
        int n = intervals.length;
        int[][] nums = new int[2 * n][2];
        int index = 0;
        for (Interval in : intervals) {
            nums[index][0] = in.start;
            nums[index++][1] = 1;
            nums[index][0] = in.end;
            nums[index++][1] = 0;
        }

        Arrays.sort(nums , (a,b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        for (int[] num : nums) {
            if (num[1] == 1) {
                ++cur;
                ret = Math.max(ret , cur);
            } else {
                --cur;
            }
        }

        return ret;
    }
}
