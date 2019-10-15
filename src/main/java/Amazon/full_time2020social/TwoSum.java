package Amazon.full_time2020social;

/*
一道一个list求等于target的目标对，
如果好几对都等于target，
返回含有最大值的那对。
不知道为啥两指针有四个隐藏的test case不过，
试着sort了一下答案，
还有三个不过，换成brute force就全都过了
 */

import java.util.*;

public class TwoSum {
    /*
一道一个list求等于target的目标对，
如果好几对都等于target，
返回含有最大值的那对。
不知道为啥两指针有四个隐藏的test case不过，
试着sort了一下答案，
还有三个不过，换成brute force就全都过了
 */
    public List<Integer> getTwoSum(List<Integer> list, int target) {
        List<Integer> ret = new ArrayList<>();
        if (list == null || list.size() <= 1) return ret;
        Set<Integer> set = new HashSet<>();
        int max = Integer.MIN_VALUE;

        for (int i: list) {

            int tempMax = Math.max(i, target - i);
            int tempMin = Math.min(i, target - i);

            if (set.contains(i) && tempMax > max) {
                ret = new ArrayList<>(Arrays.asList(tempMin, tempMax));
                max = tempMax;
            }

            set.add(target - i);
        }

        return ret;
    }

    /*
    还有一道是两个list找小于等于target的‍‍‍‌‌‌‍‌‍‌‌‍‍‍‍‌‌‌‍最大目标对，
    这道的test case两指针就全过了，
    要注意会有相同值得情况，需要返回所有情况
     */

    /**
     * time complexity is max（  max(mlogm, nlogn) ， m + n ） -> max(mlogm,nlogn)
     * @param list1
     * @param list2
     * @param target
     * @return
     */

    public List<int[]> getAllSum(List<Integer> list1, List<Integer> list2, int target) {
        ArrayList<int[]> ret = new ArrayList<>();
        Collections.sort(list1);
        Collections.sort(list2);
        final int M = list1.size();
        final int N = list2.size();
        int i = 0;
        int j = N - 1;
        int optTarget = Integer.MIN_VALUE;

        //first loop to find the optTarget
        while (i < M && j >= 0) {
            int val1 = list1.get(i);
            int val2 = list2.get(j);
            if (val1 + val2 > target) {
                j--;
            } else {
                if (val1 + val2 > optTarget) {
                    optTarget = val1 + val2;
                }
                i++;
            }
        }

        //now we know that the optTarget is the sum we want to find
        i = 0;
        j = N - 1;
        while (i < M && j >= 0) {
            int val1 = list1.get(i);
            int val2 = list2.get(j);
            if (val1 + val2 > optTarget) {
                j--;
            } else if (val1 + val2 == optTarget) {
                ret.add(new int[] {val1, val2});
                i++;
                j--;
            } else {
                i++;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        for (int[] pair: twoSum.getAllSum(new ArrayList<>(Arrays.asList(1,4,1)), new ArrayList<>(Arrays.asList(7,7,5)), 10)) {
            System.out.println(pair[0] + " " + pair[1]);
        }
        System.out.println("======================");
        for (int[] pair: twoSum.getAllSum(new ArrayList<>(Arrays.asList(1,4,1)), new ArrayList<>(Arrays.asList(7,7,3)), 10)) {
            System.out.println(pair[0] + " " + pair[1]);
        }
    }
}
