package google.google2020.google_加油少年_OnS;

/*
父母生了很多孩子，找两个人的lowest common ancestor,
虽然看到过面经，但是不太会，而且那时候时间也不多了，
就讲了讲思路，感觉用Bi-BFS? 就大概写了pesudo code
 */

import java.util.ArrayList;
import java.util.List;

public class LowestCommonAncestor {
    public class Node {
        int val;
        List<Node> children;
        Node (int x) {
            val = x;
        }
        Node (int x, List<Node> kids) {
            val = x;
            children = kids;
        }
    }

    public Node find(Node root, Node p, Node q) {
        if (root == null || root == p || root == q) return root;
        int i = 0;
        Node[] ret = new Node[2];
        for (Node node: root.children) {
            Node temp = find(node, p, q);
            if (temp != null) {
                ret[i++] = temp;
            }
        }
        return i == 2 ? root : i == 1 ? ret[0] : null;
    }
}
