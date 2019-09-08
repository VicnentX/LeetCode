package GaoSheng;

import java.util.HashMap;
import java.util.Map;

/**
 * 给一个string
 * 然后把他一分为二 找出最多的MCC
 * （比如左边两个a 两个b 右边也是 结果就是4 不在乎顺序）
 */

public class MaxCutCommonality {
    public int maxCommon(String s) {
        Map<Character, Integer> totalChar = new HashMap<>();
        int mcc = 0;

        for (char c : s.toCharArray()) {
            totalChar.put(c, totalChar.getOrDefault(c, 0) + 1);
        }
        for (char c : s.toCharArray()) {
            if (totalChar.containsKey(c) && totalChar.get(c) >= 2) {
                mcc ++;
                totalChar.put(c, totalChar.get(c) - 2);
            }
        }

        return mcc;
    }

    public static void main(String[] args) {
        MaxCutCommonality maxCutCommonality = new MaxCutCommonality();
        System.out.println(maxCutCommonality.maxCommon("abcdecdefg"));
        System.out.println(maxCutCommonality.maxCommon("aabbsdfgfjdhfbbaa"));
    }
}
