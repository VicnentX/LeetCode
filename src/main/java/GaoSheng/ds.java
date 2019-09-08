package GaoSheng;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ds {
    public int sharePurchase(String s) {
        int pair = 0;
        int len = s.length();
        Set<Character> set = new HashSet<>();
        set.add('A');
        set.add('B');
        set.add('C');

        int left = 0, right = 0;
        int count = 0;
        Map<Character, Integer> winmap = new HashMap<>();

        while (right < s.length()) {
            char c = s.charAt(right);

            if (!winmap.containsKey(c) && set.contains(c)) {
                count++;
                winmap.put(c, 1);
            } else if (winmap.containsKey(c)) {
                winmap.put(c, winmap.get(c) + 1);
            }

            while (count == 3 && left <= right) {
                pair += len - right;
                char cL = s.charAt(left);
                if (!winmap.containsKey(cL)) {
                    left++;
                    continue;
                }
                if (winmap.get(cL) == 1) {
                    winmap.remove(cL);
                    count--;
                } else {
                    winmap.put(cL, winmap.get(cL) - 1);
                }
                left++;
            }
            right++;

        }

        return pair;
    }

    public static void main(String[] args) {
        ds ds = new ds();
        System.out.println(ds.sharePurchase("ABBCZBAC"));
        System.out.println(ds.sharePurchase("ABBZBA"));
    }
}




