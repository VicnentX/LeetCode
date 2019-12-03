package bloomreach.bloomreach_加油少年_VO;

/*
电面：给一些string，将首尾字符相同的串联，求最大可串连得字符串个数。follo up：需要返回最长的字符串。
          举例： abc，cde，efg，ctttc, 这三个可以串联起来。
 */

import java.util.*;

/**
 *assume of the letter is low case
 *
 */

public class ConnectStringHeadTail {
    private static int deepLevel = 0;
    private static int longest = 0;
    private static String longestString = "";

    public int getLongestStringcChainAndCnt(String[] strings) {
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

        return deepLevel;
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
     */

    /**
     * 这里写一点的解释：
     * memo是记录从某一个index出发的最长路径,
     * -1 means not accessed before,
     * 0 means 正在被访问中，
     * 当我dfs碰到环了，就skip掉
     * edges<前， list of 后>
     *
     */
    private Map<Integer, Set<Integer>> edges;
    private int[] memo;

    public int solve(String[] strs) {
        buildGraph(strs);
        dfsSearch(strs);

        int ret = 0;
        for (int i: memo) {
            if (i > ret) ret = i;
        }

        return ret;
    }

    private void buildGraph(String[] strs) {
        memo = new int[strs.length];
        Arrays.fill(memo, -1);

        Map<Character, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < strs.length; ++i) {
            String s = strs[i];
            char lastChar = s.charAt(s.length() - 1);
            if (!map.containsKey(lastChar)) {
                map.put(lastChar, new HashSet<>());
            }
            map.get(lastChar).add(i);
        }

        edges = new HashMap<>();
        for (int i = 0; i < strs.length; ++i) {
            edges.put(i, new HashSet<>());
        }
        for (int i = 0; i < strs.length; ++i) {
            String s = strs[i];
            char firstChar = s.charAt(0);
            if (map.containsKey(firstChar)) {
                for (int n: map.get(firstChar)) {
                    if (n == i) continue;
                    edges.get(n).add(i);
                }
            }
        }
    }

    private void dfsSearch(String[] strs) {
        for (int i = 0; i < strs.length; ++i) {
            if (memo[i] == -1) {
                System.out.println("Exploring from string: " + strs[i] + " ( index = " + i + " )");
                helper(i);
            }
        }
    }

    private int helper(int index) {
        if (memo[index] > 0) {
            return memo[index];
        }

        memo[index] = 0;
        for (int n: edges.get(index)) {
            if (memo[n] == 0) continue;
            else if (memo[n] == -1) {
                int t = helper(n);
                if (1 + t > memo[index]) {
                    memo[index] = 1 + t;
                }
            } else {
                int t = memo[n];
                if (t + 1 > memo[index]) {
                    memo[index] = t + 1;
                }
            }
        }

        if (memo[index] == 0) {
            memo[index] = 1;
        }

        return memo[index];
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
