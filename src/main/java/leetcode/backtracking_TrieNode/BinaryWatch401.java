package leetcode.backtracking_TrieNode;

/*
A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59).

Each LED represents a zero or one, with the least significant bit on the right.


For example, the above binary watch reads "3:25".

Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times the watch could represent.

Example:

Input: n = 1
Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
Note:
The order of output does not matter.
The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02".
 */

import java.util.*;

public class BinaryWatch401 {
    public List<String> readBinaryWatch(int num) {
        List<String> ret = new ArrayList<>();
        int[] h = new int[] {1,2,4,8};
        int[] m = new int[] {1,2,4,8,16,32};
        for (int i = 0 ; i <= Math.min(num , 3) ; ++i) {
            List<Integer> hList = dfs(h , i);
            List<Integer> mList = dfs(m , num - i);
            for (int k1 : hList) {
                if (k1 >= 12) continue;
                for (int k2 : mList) {
                    if (k2 >= 60) continue;
                    ret.add(k1 + ":" + (k2 < 10 ? "0" + k2 : k2));
                }
            }
        }
        return ret;
    }

    private List<Integer> dfs (int[] nums , int cnt) {
        List<Integer> list = new ArrayList<>();
        helper (nums , 0 , cnt , 0 , list);
        return list;
    }



    private void helper (int[] nums , int pos , int cnt , int time , List<Integer> ret) {
        if (cnt == 0) {
            ret.add(time);
            return;
        }

        for (int i = pos ; i < nums.length ; ++i) {
            helper (nums , i + 1 , cnt - 1 , time + nums[i] , ret);
        }
    }
}
