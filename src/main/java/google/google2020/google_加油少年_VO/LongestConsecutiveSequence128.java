package google.google2020.google_加油少年_VO;

/*
We will use HashMap.
The key thing is to keep track of the sequence length
and store that in the boundary points of the sequence.
For example, as a result, for sequence {1, 2, 3, 4, 5},
map.get(1) and map.get(5) should both return 5.

Whenever a new element n is inserted into the map, do two things:

See if n - 1 and n + 1 exist in the map,
and if so, it means there is an existing sequence next to n.
Variables left and right will be the length of those two sequences,
while 0 means there is no sequence and n will be the boundary point later.
Store (left + right + 1) as the associated value to key n into the map.

Use left and right to locate the other end of the sequences to the left
and right of n respectively, and replace the value with the new length.
Everything inside the for loop is O(1) so the total time is O(n).
 Please comment if you see something wrong. Thanks.
 */

/**
 * 中间那步把num, left + right + 1 放进去很关键...
 */

import java.util.HashMap;
import java.util.Map;

public class LongestConsecutiveSequence128 {
    public int longestConsecutive(int[] nums) {
        int ret = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num: nums) {
            if (!map.containsKey(num)) {
                int left = map.getOrDefault(num - 1, 0);
                int right = map.getOrDefault(num + 1, 0);
                int len = left + right + 1;
                map.put(num, len);
                ret = Math.max(ret, len);
                //update map
                map.put(num - left, len);
                map.put(num + right, len);
            }
        }
        return ret;
    }
}
