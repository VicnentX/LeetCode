package google.google2020.google_加油少年_VO;

/*
返回一个node 这个node 有两个爸爸
 */

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class NodeWithTwoFather {

    public class Node {
        int val;
        Node left, right;
        Node (int x) {
            val = x;
        }
    }

    public Node getSonWithTwoFather(Node root) {
        Queue<Node> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.add(root);
        visited.add(root.val);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                Node cur = queue.remove();
                if (!visited.add(cur.val)) {
                    return cur;
                }
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
            }
        }


        System.out.println("no node with 2 fathers");
        return new Node (-1);
    }

}
