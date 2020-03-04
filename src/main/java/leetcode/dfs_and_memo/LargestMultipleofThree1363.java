package leetcode.dfs_and_memo;

/*
Given an integer array of digits, return the largest multiple of three that can be formed by concatenating some of the given digits in any order.

Since the answer may not fit in an integer data type, return the answer as a string.

If there is no answer return an empty string.



Example 1:

Input: digits = [8,1,9]
Output: "981"
Example 2:

Input: digits = [8,6,7,1,0]
Output: "8760"
Example 3:

Input: digits = [1]
Output: ""
Example 4:

Input: digits = [0,0,0,0,0,0]
Output: "0"


Constraints:

1 <= digits.length <= 10^4
0 <= digits[i] <= 9
The returning answer must not contain unnecessary leading zeros.
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LargestMultipleofThree1363 {

    //这种方法是最有效的，下面我写的dfs + mem是超时的
    public String largestMultipleOfThreeMathmaticl(int[] digits) {
        int[] count = new int[10];
        for (int d : digits) count[d]++;
        int two = count[2] + count[5] + count[8], one = count[1] + count[4] + count[7];
        if ((two * 2 + one) % 3 == 1) {
            if (one > 0) --one;
            else two -= 2;
        } else if ((two * 2 + one) % 3 == 2) {
            if (two > 0) --two;
            else one -= 2;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 9; i > 0; --i) {
            if (i % 3 == 0) while (count[i]-- > 0) sb.append(i);
            else if (i % 3 == 1) while (count[i]-- > 0 && one-- > 0) sb.append(i);
            else while (count[i]-- > 0 && two-- > 0) sb.append(i);
        }
        if (sb.length() == 0) return count[0] > 0 ? "0" : "";
        while (count[0]-- > 0) sb.append(0);
        return sb.toString();
    }

    //这题可以做的地方就是当我余1的时候，不管我后面乘以多少个0都是余1，对于2同理
    public String largestMultipleOfThree(int[] digits) {
        int[] count = new int[10];
        Map<String, String[]> map = new HashMap<>();

        for (int digit: digits) {
            count[digit]++;
        }
        String s =  dfs(count,map)[0];
        StringBuilder sb = new StringBuilder(s);
        while(sb.length() > 1 && sb.charAt(0) == '0') sb.deleteCharAt(0);
        return sb.toString();
    }

    private String[] dfs(int[] count, Map<String, String[]> map) {
        String key = Arrays.toString(count);

        if (map.containsKey(key)) {
            return map.get(key);
        }
        //calculate
        String[] values = new String[]{"", null, null};

        for (int i = 0; i < 10; ++i) {
            if (count[i] != 0) {
                count[i]--;
                String[] rest = dfs(count, map);
                //先试着不使用当前的这个数字
                for (int j = 0; j < 3; ++j) {
                    if (values[j] == null || (rest[j] != null && (rest[j].length() > values[j].length() || ( rest[j].length() == values[j].length() && rest[j].compareTo(values[j]) > 0)))) {
                        values[j] = rest[j];
                    }
                }
                //这里每得到一个 都要尝试取更新values里面的所有的值

                int curRes = i % 3;

                for (int restRes = 0; restRes <= 2; ++restRes) {

                    int totalRes = (curRes + restRes) % 3;

                    if (restRes == 0) {
                        if (values[totalRes] == null || (i + rest[restRes]).length() > values[totalRes].length() || ((i + rest[restRes]).length() == values[totalRes].length() && (i + rest[restRes]).compareTo(values[totalRes]) > 0)) {
                            values[totalRes] = i + rest[restRes];
                        }
                    } else {
                        if (rest[restRes] != null && (values[totalRes] == null || (i + rest[restRes]).length() > values[totalRes].length() || ((i + rest[restRes]).length() == values[totalRes].length() && (i + rest[restRes]).compareTo(values[totalRes]) > 0))) {
                            values[totalRes] = (i + rest[restRes]);
                        }
                    }
                }

                count[i]++;
            }
        }

        map.put(key, values);

        return values;
    }

    public static void main(String[] args) {
        LargestMultipleofThree1363 largestMultipleofThree1363 = new LargestMultipleofThree1363();
        System.out.println(largestMultipleofThree1363.largestMultipleOfThree(new int[] {1,8,9}));
        System.out.println(largestMultipleofThree1363.largestMultipleOfThree(new int[] {8,6,7,1,0}));
        System.out.println(largestMultipleofThree1363.largestMultipleOfThree(new int[] {0,0,0,0,0}));
        System.out.println(largestMultipleofThree1363.largestMultipleOfThree(new int[] {1,1}));
        System.out.println(largestMultipleofThree1363.largestMultipleOfThree(new int[] {1}));
        System.out.println(largestMultipleofThree1363.largestMultipleOfThree(new int[] {1,1,1,2}));
    }
}
