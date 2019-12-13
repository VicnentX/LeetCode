package google.google2020.google_加油少年_ResidentVO;

/*
题意是： 给定一个String的list,
把 相同rotation的字符串合并在一个list 里面输出。
list里面的字符串需要满足的条件是，
任意一个字符串对每一位字母加上相同数字后可以得到同一组的其它字符串。

举例： list: aaa, bbb, eee, abc, xyz
output: (aaa, bbb, eee)  (abc, xyz)。
   a + 1 = b  所以， aaa -> bbb。
 */

import java.util.*;

public class Rotation_AND_Classification {
    public List<List<String>> solve(String[] strs) {
        List<List<String>> ret = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String s: strs) {
            StringBuilder sb = new StringBuilder();
            // a = 97 Z = 90
            char min = 'z';
            for (char c: s.toCharArray()) {
                min = (char)Math.min(min, c);
            }
            for(char c: s.toCharArray()) {
                sb.append(c - min).append('*');
            }
            String key = sb.toString();
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(s);
        }
        ret.addAll(map.values());
        return ret;
    }

    public static void main(String[] args) {
        Rotation_AND_Classification rotation_and_classification = new Rotation_AND_Classification();
        List<List<String>> ret = rotation_and_classification.solve(new String[] {"aaa", "bbb", "eee", "abc" , "xyz"});
        for (List<String> list: ret) {
            for (String s: list) {
                System.out.println(s);
            }
            System.out.println("----------");
        }
    }
}
