package bloomreach.bloomreach_加油少年_VO;

/*
电面：给一些string，将首尾字符相同的串联，求最大可串连得字符串个数。follo up：需要返回最长的字符串。
          举例： abc，cde，efg，这三个可以串联起来。
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *assume of the letter is low case
 *
 */

public class ConnectStringHeadTail {
    private static int deepLevel = 0;
    private static int longest = 0;
    private static String longestString = "";

    public void getLongestStringcChainAndCnt(String[] strings) {
        //map of starting letter and this corresponding string
        Map<Character, Set<String>> map = new HashMap<>();
        for (String s: strings) {
            if (!map.containsKey(s.charAt(0))) {
                map.put(s.charAt(0), new HashSet<>());
            }
            map.get(s.charAt(0)).add(s);
        }
        //use a set to records the string which has been used
        Set<String> visited = new HashSet<>();

        //search from every s in string as head
        for (String s: strings) {
            visited.add(s);
            dfs(1, s, visited, map);
            visited.remove(s);
        }
    }

    private void dfs(int level, String cur, Set<String> visited, final Map<Character, Set<String>> map) {
        //update the result if necessary
        deepLevel = Math.max(deepLevel, level);
        if (cur.length() > longest) {
            longestString = cur;
            longest = cur.length();
        }

        char c = cur.charAt(cur.length() - 1);
        if (map.containsKey(c)) {
            for (String next: map.get(c)) {
                if (!visited.contains(next)) {
                    visited.add(next);
                    dfs(level + 1, cur.substring(0, cur.length() - 1) + next, visited, map);
                    visited.remove(next);
                }
            }
        }
    }

    public static void main(String[] args) {
        ConnectStringHeadTail connectStringHeadTail = new ConnectStringHeadTail();
        //8
        //asdfghjk
        connectStringHeadTail.getLongestStringcChainAndCnt(new String[] {"asd", "dfg", "ghj", "ghjk"});
        System.out.println(deepLevel);
        System.out.println(longestString);
    }
}
