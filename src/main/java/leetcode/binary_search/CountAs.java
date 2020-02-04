package leetcode.binary_search;

/*
不确定有多少b 不确定有多少a 不确定有多少c
顺序是bac
但是可能没有b 或 a 或 c
 */

import Mathworks.CountBits;

public class CountAs {
    public int solve(String s) {
        int left = cnt(s, 'b', 1);
        int right = cnt(s, 'c', -1);
        return right - left - 1;
    }

    private int cnt(String s, char c, int dir) {
        int start = 0;
        int end = s.length() - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (s.charAt(mid) != c) {
                if (dir > 0) {
                    end = mid;
                } else {
                    start = mid;
                }
            } else {
                if (dir > 0) {
                    start = mid;
                } else {
                    end = mid;
                }
            }
        }
        if (dir > 0) {
            return s.charAt(end) == c ? end : s.charAt(start) == c ? start : -1;
        } else {
            return s.charAt(start) == c ? start : s.charAt(end) == c ? end : s.length();
        }
    }

    public static void main(String[] args) {
        CountAs countAs = new CountAs();
        //0
        System.out.println(countAs.solve("bbbbbbcccccc"));
        //5
        System.out.println(countAs.solve("bbbbbbaaaaacccccc"));
        //2
        System.out.println(countAs.solve("aacccccc"));
        //3
        System.out.println(countAs.solve("bbbbbbaaa"));
    }
}
