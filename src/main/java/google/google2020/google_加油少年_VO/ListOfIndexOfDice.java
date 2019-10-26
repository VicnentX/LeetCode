package google.google2020.google_加油少年_VO;

/*
map就是 字母 - list of index of dice

一道题目：给你一些骰子，每个面有一个字母，再给你一个单词，让你从这些骰子中取出几个拼成这个单词。


答案：做一个字母-》所有骰子的hashmap,然后用set做backtracking。

再问time complexity。

直接回答exponential。

又追问什么的exponential。一时没想起来，当场画了个树，找到height（单词的字母数目）。

再再追问：给出具体哪些骰子。

答案：backtracking加个list，然后一起backtrack。


再再再追问：如果一些字母可以用其他字母替换，请问怎么做。

答案：再backtrack中加入相应的edge traversal。


最后问能不能优化：我觉得不能，但陪他走了一遍思路，结果他发现自己想错了，直接结束coding问题。
 */

import java.util.*;

public class ListOfIndexOfDice {
    public List<String> findAllPathOfIndex(String[][] dices, String target) {
        //make maps
        Map<String, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < dices.length; ++i) {
            for (String c: dices[i]) {
                if (!map.containsKey(c)) {
                    map.put(c, new ArrayList<>());
                }
                map.get(c).add(i);
            }
        }

        List<String> ret = new ArrayList<>();

        dfs(map, target, 0, ret, new HashSet<Integer>(), "");

        return ret;
    }

    private void dfs(Map<String, List<Integer>> map, String target, int i, List<String> ret, HashSet<Integer> visited, String path) {

        if (i == target.length()) {
            ret.add(path);
        }

        String cur = String.valueOf(target.charAt(i));

        if (map.containsKey(cur)) {
            for (int index: map.get(cur)) {
                if (!visited.contains(index)) {
                    visited.add(index);
                    dfs(map, target, i + 1, ret, visited, path + index + "-");
                    visited.remove(index);
                }
            }
        }
    }
}
