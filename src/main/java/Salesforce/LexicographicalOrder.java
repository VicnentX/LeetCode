package Salesforce;

/*
第二题：给你一个string 是一堆pair-(parent node, child node) 括号之间空格隔开，节点都是A-Z, 最多26个nodes，例如 input string = “(A,B) (B,D) (A,C)”。这些pair代表tree里父节点和子节点的对应关系。然后让你输出binary tree长啥样，父节点如果有两个儿子按照字典顺序输出。例如之前的input对应的tree的输出是：(A(B(D))(C))
 (A(B)(C(D))), (parent.val(left expression, right express) )
其中有写错误的情况
一个节点有超过两个子节点 -》返回字符串 E1
有两个相同的pair -》返回E2，
如果有环 -》返回E3，
根节点大于一个 -》返回E4，
其他错误（例如invalid输入） -》返回E5
【build graph阶段可以检测E1 E2 和部分E3】
【剩余E3用拓扑查（有向）】
【E4用node数和edge数关系判断】
【restore阶段阶段找root】


E1 a node has more than two children
E2 more than one identical edge
E3 Tree would contain a cycle
E4 more than one root node
E5 all other errors(eg. invalid input, two fathers)
 */

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LexicographicalOrder {
    public String solve(char[][] pairs) {

        String error = "E6";
        StringBuilder ret = new StringBuilder();
        Map<Character, Integer> indegree = new HashMap<>();
        //第27位记录的是有多少的儿子
        Map<Character, int[]> parentToChildren = new HashMap<>();
        //有一个unionFind roots
        int[] roots = new int[26];
        for(int i = 0; i < roots.length; ++i) {
            roots[i] = i;
        }

        //fill two maps and check if there is error
        for (char[] pair: pairs) {
            char parent = pair[0];
            char child = pair[1];
            if (!parentToChildren.containsKey(parent)) {
                parentToChildren.put(parent, new int[27]);
            }
            if (parentToChildren.get(parent)[child - 'A'] == 1) {
                error = updateError("E2", error);
            } else {
                parentToChildren.get(parent)[child - 'A'] = 1;
                if (parentToChildren.get(parent)[26]++ > 2) {
                    error = updateError("E1", error);
                }
                indegree.put(child, indegree.getOrDefault(child, 0) + 1);
                if (indegree.get(child) >= 2) {
                    //有两个爸爸的错误
                    error = updateError("E5", error);
                }
                if (!indegree.containsKey(parent)) {
                    indegree.put(parent, 0);
                }
            }
            //把找有没有环的工作也一起做掉把 会方便一些 用unionFind
            int root1 = findRoot(roots, parent - 'A');
            int root2 = findRoot(roots, child - 'A');
            if (root1 != root2) {
                roots[root2] = root1;
            } else {
                error = updateError("E3", error);
            }

        }

        //看下有几个根节点 同时存放dfs的开始节点
        List<Character> entry = new ArrayList<>();
        for(char c: indegree.keySet()) {
            if (indegree.get(c) == 0) {
                entry.add(c);
            }
        }
        if (entry.size() > 1) {
            error = updateError("E4", error);
        }

        //如果已经有error 就不用往下走了
        if (!error.equals("E6")) {
            return error;
        }

        //到了这里说明会有答案的，做dfs get result
        dfs(entry.get(0), parentToChildren, ret);
        return ret.toString();
    }

    private void dfs(char c, Map<Character, int[]> parentToChildren, StringBuilder ret) {
        ret.append('(').append(c);
        for(int i = 0; i < 26; ++i) {
            if (parentToChildren.containsKey(c) && parentToChildren.get(c)[i] == 1) {
                dfs((char)(i + 'A'), parentToChildren, ret);
            }
        }
        ret.append(')');
    }

    private int findRoot(int[] roots, int id) {
        if (roots[id] == id) return id;
        roots[id] = findRoot(roots, roots[id]);
        return roots[id];
    }

    private String updateError(String newError, String error) {
        return newError.compareTo(error) < 0 ? newError : error;
    }

    public static void main(String[] args) {
        LexicographicalOrder lexicographicalOrder = new LexicographicalOrder();
        String ret = lexicographicalOrder.solve(new char[][] {{'B','D'},{'D','E'},{'A','B'},{'C','F'},{'E','G'},{'A','C'},});
        System.out.println(ret);
        System.out.println(lexicographicalOrder.solve(new char[][] {{'A','B'},{'A','C'},{'B','D'},{'D','C'}}));
    }
}
