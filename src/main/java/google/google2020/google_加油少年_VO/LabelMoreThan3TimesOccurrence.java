package google.google2020.google_加油少年_VO;

/*
给一个string: abbbcccc,
标记出 连续重复三次或以上的
字母的初始位置和其实位置;
abbbcccc, result: [(1, 3), (4, 7)]

 */

import java.util.ArrayList;
import java.util.List;

public class LabelMoreThan3TimesOccurrence {

    public List<int[]> labelPosition (String s) {
        List<int[]> ret = new ArrayList<>();
        int sum = 1;
        int start = 0;
        final int N = s.length();
        for (int i = 0; i < N - 1; ++i) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                sum++;
            } else {
                if (sum >= 3) {
                    ret.add(new int[] {start, i});
                }
                sum = 1;
                start = i + 1;
            }
        }
        if (sum >= 3) {
            ret.add(new int[] {start, N - 1});
        }

        return ret;
    }

    public static void main(String[] args) {
        LabelMoreThan3TimesOccurrence labelMoreThan3TimesOccurrence = new LabelMoreThan3TimesOccurrence();
        for (int[] period: labelMoreThan3TimesOccurrence.labelPosition("abbbcccc")) {
            System.out.println(period[0] + "  " + period[1]);
        }
    }

}
