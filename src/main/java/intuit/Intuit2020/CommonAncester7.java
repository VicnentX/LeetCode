package intuit.Intuit2020;

/*
这题就是给很多个pair 是（父亲，儿子）的pair
三个问题
1。 那些事父节点
2。 那些事第一层节点
3。 问两个节点有没有共同的父节点
 */

import java.util.*;

public class CommonAncester7 {



    public List<Integer> findFirstLevelNode(int[][] pairs) {
        Map<Integer, Integer> hasPreNodeCnt = (Map<Integer, Integer>) buildRelationsAndHasPreNodeCnt(pairs)[1];
        List<Integer> firstLevelNodes = new ArrayList<>();

        for (int node: hasPreNodeCnt.keySet()) {
            if (hasPreNodeCnt.get(node) == 0) {
                firstLevelNodes.add(node);
            }
        }

        return firstLevelNodes;
    }

    public List<Integer> findSecondLevelNodes(int[][] pairs) {
        Map<Integer, List<Integer>> relations = (Map<Integer, List<Integer>>) buildRelationsAndHasPreNodeCnt(pairs)[0];
        List<Integer> firstLevelNodes = findFirstLevelNode(pairs);
        Set<Integer> secondLevelNodes = new HashSet<>();

        for (int firstNode: firstLevelNodes) {
            for (int secondNode: relations.get(firstNode)) {
                secondLevelNodes.add(secondNode);
            }
        }

        return new ArrayList<>(secondLevelNodes);
    }

    public boolean hasCommonAncester(int[][] pairs, int i, int j) {
        Object[] temp = buildRelationsAndHasPreNodeCnt(pairs);
        Map<Integer, List<Integer>> relations = (Map)temp[0];
        List<Integer> firstLevelNodes = findFirstLevelNode(pairs);

        for (int node: firstLevelNodes) {
            if (hasIandJ(relations, node, i , j)) {
                return true;
            }
        }

        return false;
    }

    private boolean hasIandJ(Map<Integer, List<Integer>> relations, int node, int i, int j) {
        int notFoundCnt = 2;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (cur == i || cur == j) {
                notFoundCnt--;
                if (notFoundCnt == 0) {
                    return true;
                }
            }
            if (relations.containsKey(cur)) {
                queue.addAll(relations.get(cur));
            }
        }

        return false;
    }

    private Object[] buildRelationsAndHasPreNodeCnt(int[][] pairs) {
        //father and sons
        Map<Integer, List<Integer>> relations = new HashMap<>();
        //node and how many fathers
        Map<Integer, Integer> hasPreNodeCnt = new HashMap<>();
        //result
        List<Integer> firstLevelNodes = new ArrayList<>();

        //fill the map
        for (int[] pair: pairs) {
            int father = pair[0];
            int son = pair[1];
            if (!relations.containsKey(pair[0])) {
                relations.put(father, new ArrayList<>());
            }
            relations.get(father).add(son);

            hasPreNodeCnt.put(son, hasPreNodeCnt.getOrDefault(son, 0) + 1);
            if (!hasPreNodeCnt.containsKey(father)) {
                hasPreNodeCnt.put(father, 0);
            }
        }

        return new Object[] {relations, hasPreNodeCnt};
    }
}
