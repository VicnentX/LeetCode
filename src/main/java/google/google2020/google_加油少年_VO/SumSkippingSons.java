package google.google2020.google_加油少年_VO;

/*
就是每个点的node的sum是不包含他的儿子 只包含自己和孙子以及下面
这里的点可能有负数
 */

/**
 * return int[]
 * first num is the sum of itself and all its descendants
 * first num is the sum of all its descentdant without its own val
 */

import java.util.ArrayList;
import java.util.List;

public class SumSkippingSons {

    public class Node {
        int val;
        List<Node> children = new ArrayList<>();

        Node (int x) {
            val = x;
        }

        Node (int x, List<Node> kids) {
            val = x;
            children = kids;
        }
    }


    //assume sum will less than Integer.MIN_VALUE;
    private Node max = new Node (Integer.MIN_VALUE);

    public Node getNodeWithMaxSum(Node root) {
        dfs(root);
        System.out.println(max.val);
        return max;
    }

    //第一个值不包含自己 第二个值包含自己

    private int[] dfs(Node node) {
        if (node == null) {
            return new int[] {0, 0};
        }

        int sumTotal = node.val;
        int sumParital = node.val;

        for (Node child: node.children) {
            int[] ret = dfs(child);
            sumParital += ret[0];
            sumTotal += ret[1];
        }

        if (sumParital + node.val > max.val) {
            max = node;
        }

        return new int[] {sumTotal, sumTotal + node.val};
    }

}
