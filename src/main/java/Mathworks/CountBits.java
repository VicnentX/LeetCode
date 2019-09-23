package Mathworks;

import java.util.ArrayList;
import java.util.List;

/**
 * 把一个数变成二进制，输出1的总个数及每个1所在的位数。比如说 18 = 10010，就输出{2，1,  4}
 */

public class CountBits {
    public List<Integer> count(int n) {
        List<Integer> indexs = new ArrayList<>();
        int onesSum = 0, index = 0;

        while (n > 0) {
            if ((n & 1) == 1) {
                onesSum++;
                indexs.add(index);
            }
            index++;
            n = n >> 1;
        }

        List<Integer> ret = new ArrayList<>();
        ret.add(onesSum);
        ret.addAll(indexs);

        return ret;
    }

    public static void main(String[] args) {
        CountBits countBits = new CountBits();
        for (int i: countBits.count(18)) {
            System.out.println(i);
        }
    }
}
