package Amazon.full_time2020.final_round_加油少年_coding;

/*
输入两个有序数组，输出交集。 followup: unsorted, duplicate, unduplicate.
 */

import java.util.ArrayList;
import java.util.List;

public class IntersectionOfTwoArray {

    public List<Integer> findIntersection(int[] array1, int[] array2) {
        List<Integer> ret = new ArrayList<>();
        if (array1.length == 0 || array2.length == 0) {
            return ret;
        }
        int i = 0, j = 0;
        while (i < array1.length && j < array2.length) {
            if (array1[i] < array2[j]) {
                i++;
            } else if (array1[i] > array2[j]) {
                j++;
            } else {
                ret.add(array1[i]);
                i++;
                j++;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        IntersectionOfTwoArray intersectionOfTwoArray = new IntersectionOfTwoArray();
        List<Integer> ret = intersectionOfTwoArray.findIntersection(
                new int[] {1,3,5,6,7}, new int[] {1,4,6,7,8}
        );
        for (int i: ret) {
            System.out.println(i);
        }
    }

}
