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


    /*
    这个是姐夫的方法 我其实对于怎么去重还是没明白 先写在这里吧
    最后一个例子结果应该是4 但是stackoverflow了 说明还是没有解决这个问题
     */
    public int solve(String[] strs) {
        Map<Character, Set<Integer>> map = new HashMap<>();
        //fill map of last char and all the index of string ending with this last char
        for (int i = 0; i < strs.length; ++i) {
            String s = strs[i];
            char lastChar = s.charAt(s.length() - 1);
            if (!map.containsKey(lastChar)) {
                map.put(lastChar, new HashSet<>());
            }
            map.get(lastChar).add(i);
        }

        //ret map of index of string and value is a pair of max chains and it is visited or not
        Map<Integer, Object[]> ret = new HashMap<>();
        for (int i = 0; i < strs.length; ++i) {
            ret.put(i, new Object[]{1, false});
        }

        for (int i = 0; i < strs.length; ++i) {
            if ((boolean)ret.get(i)[1]) { //visited
                continue;
            }
            dfs(i, strs, ret, map);
        }

        //get the result
        int maxChain = 0;
        for (Object[] pair: ret.values()) {
            if ((int)pair[0] > maxChain) {
                maxChain = (int)pair[0];
            }
        }

        return maxChain;
    }

    private int dfs(int index, String[] strs, Map<Integer, Object[]> ret, Map<Character, Set<Integer>> map) {
        int max_path = 0;
        String s = strs[index];
        char firstChar = s.charAt(0);

        if (map.containsKey(firstChar)) {
            for (int n: map.get(firstChar)) {
                if (n == index) {
                    continue;
                }
                if ((boolean)ret.get(n)[1]) {
                    max_path = Math.max(max_path, (int)ret.get(n)[0]);
                } else {
                    dfs(n, strs, ret, map);
                    max_path = Math.max(max_path, (int)ret.get(n)[0]);
                }
            }
        }
        ret.put(index, new Object[] {1 + max_path, true});

        return max_path;
    }


    public static void main(String[] args) {
        ConnectStringHeadTail connectStringHeadTail = new ConnectStringHeadTail();

        System.out.println("fangfa 1--------------");
        //8
        //asdfghjk
        connectStringHeadTail.getLongestStringcChainAndCnt(new String[] {"asd", "dfg", "ghj", "ghjk"});
        System.out.println(deepLevel);
        System.out.println(longestString);



        System.out.println("fangfa 2--------------");
        //3
        int ret = connectStringHeadTail.solve(new String[] {"abc", "cde", "efg"});
        System.out.println(ret);
        //3
        ret = connectStringHeadTail.solve(new String[] {"abc", "cde", "efg","eddfg"});
        System.out.println(ret);
        //4
        ret = connectStringHeadTail.solve(new String[] {"abc", "cde", "efg","eddfa", "akjip"});
        System.out.println(ret);
    }
}
