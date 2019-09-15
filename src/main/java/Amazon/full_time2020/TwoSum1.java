package Amazon.full_time2020;

import java.util.HashMap;
import java.util.Map;

/*
1. 变种Two Sum, 根据场景来看所有正常‍‍‍‌‌‌‍‌‍‌‌‍‍‍‍‌‌‌‌的input都是正数，
要求multiple answers的时候输出最大值最大的那组.
Testcase 12/13（最蛋疼的是还看不到没过的那个testcase是因为什么没过的zzzz）
... 考虑了input数组为空，target<=0和candidate<=0的corner case，暂时想不起来还有哪个没考虑...
 */

public class TwoSum1 {
    public int[] twoSum(int[] numbers, int target) {
        int max = Integer.MIN_VALUE;
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(target - numbers[i])) {
                int tempMax = Math.max(numbers[i], target - numbers[i]);
                if (tempMax> max) {
                    result[1] = i;
                    result[0] = map.get(target - numbers[i]) - 1;
                    max = tempMax;
                }
            }
            map.put(numbers[i], i + 1);
        }
        return result;
    }

    public static void main(String[] args) {
        TwoSum1 twoSum1 = new TwoSum1();
        int[] ret = twoSum1.twoSum(new int[] {1,2,3,4,5}, 5);
        for (int i: ret) {
            System.out.println(i);
        }
    }
}
