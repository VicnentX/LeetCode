package google.google2020.google_加油少年_VO;

/*
给一堆未知数之间大于小于的关系，判断所有关系之间是否成立。
比如输入 a<b, b<c, a<c, 输出就是成立；输入 a<b, b<c, c<a, 输出就是不成立。符号仅限大于号和小于号
本质可以转化为一道有向图找环的题，经典解法有很多，用dfs/bfs均可

然后follow up1是，如果输入端除了未知数还包含数字怎么办？
比如输入 a>2, a<b, b<1，输出就是不成立
这问相对简单，我的做法是把所有出现过的数字记录下来，在create有向图的时候按大小顺序把数字也加到有向图里，然后再用dfs/bfs找环即可

然后follow up2是，如果符号包含大于等于号，小于等于号怎么办？
比如输入 a>b, b<=a，输出不成立；输入a>=b, b>=c, c>=a, 输出成立；
这问感觉挺难，现场答的磕磕绊绊没完全做出来。面完之后又想了想觉得问的十分精彩
我的想法是
1.首先先忽略等于号的存在create有向图。
2.然后求图的strongly connected component。
3.然后输入端成立的充要条件是所有scc内部的连接都必须带等号
其实本质也是将问题转化成现有的经典数据结构问题：有向图中求scc。

 */

import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class RelationshipChainValidation {


    //assume pair1 - a; pair2 - b mean a < b
    public boolean validateChain(char[][] pairs) {
        Map<Character, Integer> inDegree = new HashMap<>();
        Map<Character, List<Character>> map = new HashMap<>();
        //build graph
        for (char[] pair: pairs) {
            char small = pair[0];
            char big = pair[1];
            inDegree.put(big, inDegree.getOrDefault(big, 0) + 1);
            inDegree.putIfAbsent(small, 0);
            if (!map.containsKey(small)) {
                map.put(small, new ArrayList<>());
            }
            map.get(small).add(big);
        }
        //get the node whose indegree is 0;
        Set<Character> set = new HashSet<>();
        for (char c: inDegree.keySet()) {
            if (inDegree.get(c) == 0) {
                set.add(c);
            }
        }

        //cnt of all the different char
        final int size = inDegree.size();
        int cnt = 0;

        while (!set.isEmpty()) {
            Set<Character> next = new HashSet<>();
            for (char c: set) {
                cnt++;
                if (map.containsKey(c)) {
                    for (char nextChar: map.get(c)) {
                        inDegree.put(nextChar, inDegree.get(nextChar) - 1);
                        if (inDegree.get(nextChar) == 0) {
                            next.add(nextChar);
                        }
                    }
                }

            }
            set = next;
        }

        return cnt == size;
    }



    /*
    然后follow up1是，如果输入端除了未知数还包含数字怎么办？
比如输入 a>2, a<b, b<1，输出就是不成立
这问相对简单，我的做法是把所有出现过的数字记录下来，在create有向图的时候按大小顺序把数字也加到有向图里，然后再用dfs/bfs找环即可

     */
    public boolean validationChainContainingNumber(String[][] pairs) {
        Map<String, List<String>> map = new HashMap<>();
        //build graph
        for (String[] pair: pairs) {
            String small = pair[0];
            String big = pair[1];
            if (!map.containsKey(small)) {
                map.put(small, new ArrayList<>());
            }
            map.get(small).add(big);
        }
        //
        for (String s: map.keySet()) {
            if (!dfs("*", s, map, new HashSet<>(), new HashMap<>())) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(String preNum, String curNode, Map<String, List<String>> map, HashSet<String> visited, HashMap<String, String> mem) {

        if (visited.contains(curNode)) {
            return false;
        }
        visited.add(curNode);

        if (mem.containsKey(curNode)) {
            if (!preNum.equals("*") && !mem.get(curNode).equals("*")) {
                if (Integer.valueOf(preNum) >= Integer.valueOf(mem.get(curNode))) {
                    return false;
                }
            }
            return true;
        }

        if (StringUtils.isNumeric(curNode)) {
            mem.put(curNode, curNode);
            if (StringUtils.isNumeric(preNum)
                    && Integer.valueOf(curNode) <= Integer.valueOf(preNum)) {
                return false;
            }
            preNum = curNode;
        }

        int tempMin = Integer.MAX_VALUE;
        boolean flag = false;

        if (map.containsKey(curNode)) {
            for (String next: map.get(curNode)) {
                if (!dfs(preNum, next, map, visited, mem)) {
                    return false;
                }
                if (mem.containsKey(next) && !mem.get(next).equals("*")) {
                    flag = true;
                    tempMin = Math.min(tempMin, Integer.valueOf(mem.get(next)));
                }
            }
        }

        visited.remove(curNode);

        if (!mem.containsKey(curNode)) {
            if (flag) {
                mem.put(curNode, String.valueOf(tempMin));
            } else {
                mem.put(curNode, "*");
            }
        }

        return true;
    }

    /**
     * 第三题有《= 的情况 个人感觉第二种dfs的方法稍加修改就能用了
     * @param args
     */

    /*
    这个下面是别人写的东西 我只是参考
     */

    private static class Edge {
        String targetVertex;
        boolean includeEqual;

        Edge(String t, boolean e) {
            targetVertex = t;
            includeEqual = e;
        }
    }

    // suppose we already build a graph
    public static boolean checkNumCycle(Map<String, List<Edge>> graph) {
        Set<String> visited = new HashSet<>();
        for (String point : graph.keySet()) {
            Set<String> curSet = new HashSet<>();
            if (!dfs_1(point, graph, visited, curSet, true)) return false;
        }
        return true;
    }

    private static boolean dfs_1(String v, Map<String, List<Edge>> graph, Set<String> visited, Set<String> curSet, boolean includeEqual) {
        if (visited.contains(v)) return true;
        if (curSet.contains(v)) return includeEqual;
        curSet.add(v);
        if (graph.containsKey(v) && graph.get(v).size() > 0) {
            for (Edge n : graph.get(v)) {
                boolean e = n.includeEqual & includeEqual;
                String next = n.targetVertex;
                if (!dfs_1(next, graph, visited, curSet, e)) return false;
            }
        }
        curSet.remove(v);
        visited.add(v);
        return true;
    }



    public static void main(String[] args) {
        RelationshipChainValidation relationshipChainValidation = new RelationshipChainValidation();
        //true
        System.out.println(relationshipChainValidation.validateChain(new char[][] {{'a','b'},{'a','c'},{'b','d'},{'c','d'}}));
        //false
        System.out.println(relationshipChainValidation.validateChain(new char[][] {{'a','b'},{'a','c'},{'b','d'},{'c','a'}}));


        //false
        System.out.println(relationshipChainValidation.
                validationChainContainingNumber(new String[][] {{"a","2"},{"2","b"},{"b","5"},{"5","a"}}));

        //true
        System.out.println(relationshipChainValidation.
                validationChainContainingNumber(new String[][] {{"a","2"},{"2","b"},{"b","5"}}));

        //true
        System.out.println(relationshipChainValidation.
                validationChainContainingNumber(new String[][] {{"a","2"},{"2","b"},{"b","5"},{"1","2"}}));

        //true
        System.out.println(relationshipChainValidation.
                validationChainContainingNumber(new String[][] {{"a","2"},{"2","b"},{"b","5"},{"1","2"}}));

        //false
        System.out.println(relationshipChainValidation.
                validationChainContainingNumber(new String[][] {{"a","2"},{"2","b"},{"b","5"},{"8","b"}}));
    }

}
