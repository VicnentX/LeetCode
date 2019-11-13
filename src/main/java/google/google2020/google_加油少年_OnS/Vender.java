package google.google2020.google_加油少年_OnS;

/*
高频题饮料贩卖机。三个button，
第一个 [30,40]第二个[20,40] 第三个[60,70]
（具体的数字我忘了，瞎编的）。有一个杯子装[130,160]，
能不能有一种方法能够确保倒的饮料正好在这个杯子的范围里。
lz正好面试前一天晚上睡觉前做了这道题，所以记忆犹新。
不过还是装作没听过一样问了一些自己都觉得蠢的问题。
然后花了十分钟写完了。方法就是递归+（二维）memery.

 2比如你获取到了一个 [60,80]这样的范围，
 backtracking之后知道这个数再怎么组合别的按钮也没法到最后的范围，
 下次再遇到[60,80]直接就剪枝了。就不用在backtracking一遍了。
 */

import java.util.*;

public class Vender {
    private Map<String, Boolean> map = new HashMap<>();

    public boolean canMeetRange(int[] input, int[][] dictionary) {
        Arrays.sort(dictionary, (a,b) -> (b[0] + b[1] - a[0] - a[1]));
        return dfs(input, dictionary, map);
    }

    private boolean dfs(int[] input, int[][] dictionary, Map<String, Boolean> map) {
        String key = input[0] + "-" + input[1];

        if (input[0] == 0 && input[1] == 0) {
            return true;
        }

        if (input[0] < 0 && input[1] < 0) {
            return false;
        }

        if (map.containsKey(key)) {
            return map.get(key);
        }

        for (int[] dic: dictionary) {
            if (dfs(new int[] {input[0] - dic[0], input[1] - dic[1]}, dictionary, map)) {
                map.put(key, true);
                return true;
            }
        }

        map.put(key, false);
        return false;
    }

    public static void main(String[] args) {
        Vender vender = new Vender();
        //false
        System.out.println(vender.canMeetRange(new int[] {130, 160}, new int[][] {{30,40},{20,40},{60,70}}));
        //true
        System.out.println(vender.canMeetRange(new int[] {110, 150}, new int[][] {{30,40},{20,40},{60,70}}));
    }
}
