package leetcode.Sort;

/*
Given a collection of intervals, merge all overlapping intervals.

Example 1:

Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
Example 2:

Input: [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 */

import java.util.*;
public class MergeIntervals56ListSort {
    public static class Interval {
        int start;
        int end;
        Interval(){start = 0;end = 0;}
        Interval(int s , int e) {
            start = s;
            end = e;
        }
    }

    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> ret = new ArrayList<>();
        if (intervals.size() == 0) return ret;
        Collections.sort(intervals , Comparator.comparing(interval -> interval.start));
        Interval cur = intervals.get(0);
        for (int i = 1 ; i < intervals.size() ; ++i) {
            Interval tem = intervals.get(i);
            if (tem.start <= cur.end) {
                cur = new Interval(cur.start , Math.max(cur.end , tem.end));
            } else {
                ret.add(cur);
                cur = tem;
            }
        }
        ret.add(cur);
        return ret;
    }

    public static void main (String[] args) {
        MergeIntervals56ListSort mi = new MergeIntervals56ListSort();
        List<Interval> input = new ArrayList<>();
        input.add(new Interval(2,6));
        input.add(new Interval(1,3));
        input.add(new Interval(8,10));
        input.add(new Interval(15,18));
        for (Interval it : mi.merge(input)) {
            System.out.println(it.start + "-" + it.end);
        }
    }
}
