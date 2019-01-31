package leetcode.Sort;

/*
Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:

Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
Example 2:

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
Seen this question in a real interview before?

 */


import java.util.*;
public class InsertInterval57 {
    public class Interval {
        int start , end;
        Interval(int s , int e) {
            start = s;
            end = e;
        }
    }

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> ret = new ArrayList<>();
        int n = intervals.size();
        int[][] nums = new int[2 * n + 2][2];
        int index = 0;
        for (Interval in : intervals) {
            nums[index][0] = in.start;
            nums[index++][1] = 1;
            nums[index][0] = in.end;
            nums[index++][1] = -1;
        }
        nums[index][0] = newInterval.start;
        nums[index++][1] = 1;
        nums[index][0] = newInterval.end;
        nums[index++][1] = -1;
        Arrays.sort(nums , (a , b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);

        int cnt = 0;
        int start = -1;
        for (int[] k : nums) {
            if (cnt == 0) {
                start = k[0];
            }
            cnt += k[1];
            if (cnt == 0) {
                ret.add(new Interval(start , k[0]));
            }
        }

        return ret;
    }
}
