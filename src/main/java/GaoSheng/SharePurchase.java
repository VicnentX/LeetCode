package GaoSheng;

import java.util.*;

/**
 * given is string (consist of all capital letter),
 * find the # of all the substring consist A B C
 */

public class SharePurchase {
    public int analyzeInvestments(String s) {
        final int N = s.length();
        int cnt = 0;
        Map<Character, LinkedList<Integer>> map = new HashMap<>();
        Set<Character> set = new HashSet<Character>(){{
            add('A');
            add('B');
            add('C');
        }};

        for (int i = 0 ; i < N ; ++i) {
            char c = s.charAt(i);
            if (set.contains(c)) {
                if (!map.containsKey(c)) map.put(c, new LinkedList<>());
                map.get(c).add(i);
            }
        }

        if (!map.containsKey('A') || !map.containsKey('B') || !map.containsKey('C'))
            return cnt;

        for (char c : s.toCharArray()) {
            cnt += validCntFromC(map, N);
            if (set.contains(c)) {
                if (map.get(c).size() == 1) break;
                map.get(c).remove(0);
            }
        }

        return cnt;
    }

    private int validCntFromC(Map<Character, LinkedList<Integer>> map, int N) {
        int cloestAIndex = map.get('A').get(0);
        int cloestBIndex = map.get('B').get(0);
        int cloestCIndex = map.get('C').get(0);
        return N - Math.max(cloestAIndex, Math.max(cloestBIndex, cloestCIndex));
    }

    public static void main(String[] args) {
        SharePurchase sharePurchase = new SharePurchase();
        System.out.println(sharePurchase.analyzeInvestments("ABBCZBAC"));
        System.out.println(sharePurchase.analyzeInvestments("ABBZBA"));
    }
}
