package google.google2020.google_加油少年_ResidentVO;

/*
Ram was driving past a street with a lot of high rise buildings. Ram wondered, were there any three buildings such that each is double the height of the previous one. Could you help him figure out that?

Assume that heights are whole numbers.

Main question: Can you find out how many such occurrences are there?

3, 1, 2, 7, 4
return 1

3 1 2 7 4 4 1
return 2
 */

import java.util.HashMap;
import java.util.Map;

public class RamDriving {
    public int solve(int[] nums) {
        int ret = 0;
        // num and its occurrence
        Map<Integer, Integer> map = new HashMap<>();
        for (int num: nums) {
            if (num % 4 == 0 && map.containsKey(num / 4) && map.containsKey(num / 2)) {
                ret += (map.get(num / 4) * map.get(num / 2));
            }
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        return ret;
    }

    public static void main(String[] args) {
        RamDriving ramDriving = new RamDriving();
        //1
        System.out.println(ramDriving.solve(new int[] {3, 1, 2, 7, 4}));
        //2
        System.out.println(ramDriving.solve(new int[] {3, 1, 2, 7, 4, 4, 1}));
    }
}
