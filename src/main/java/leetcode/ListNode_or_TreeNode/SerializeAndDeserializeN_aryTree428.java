package leetcode.ListNode_or_TreeNode;

import java.util.*;

class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};


public class SerializeAndDeserializeN_aryTree428 {
    //dfs
    // Encodes a tree to a single string.
//    public String serialize(Node root) {
//        List<String> list = new LinkedList<>();
//        serializeHelper(root , list);
//        return String.join("," , list);
//    }
//    private void serializeHelper(Node root , List<String> list){
//        if(root == null){
//            return ;
//        }else{
//            list.add(String.valueOf(root.val));
//            list.add(String.valueOf(root.children.size()));
//            for(Node k : root.children){
//                serializeHelper(k , list);
//            }
//        }
//    }
//
//    // Decodes your encoded data to tree.
//    public Node deserialize(String data) {
//        if(data.isEmpty()){
//            return null;
//        }
//        String[] s = data.split(",");
//        Queue<String> q = new LinkedList<>(Arrays.asList(s));
//        return deserializeHelper(q);
//    }
//    private Node deserializeHelper(Queue<String> q){
//        Node root = new Node();
//        root.val = Integer.parseInt(q.remove());
//        int size = Integer.parseInt(q.remove());
//        root.children = new ArrayList<>(size);
//        for(int i = 0 ; i < size ; ++i){
//            root.children.add(deserializeHelper(q));
//        }
//        return root;
//    }

    //bfs
    // Encodes a tree to a single string.
    public String serialize(Node root) {
        List<String> ret = new LinkedList<>();
        if(root == null){
            return "";
        }
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            Node node = q.remove();
            int size = node.children.size();
            ret.add(node.val + "-" + size);
            for(Node k : node.children){
                q.add(k);
            }
        }
        System.out.println(String.join("," , ret));
        return String.join("," , ret);
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if(data.isEmpty() || data.length() == 0){
            return null;
        }

        String[] s = data.split(",");
        int n = s.length;
        String[] ss = s[0].split("-");
        Node root = DIYnode(ss[0] , ss[1]);

        Queue<Node> q = new LinkedList<>();
        int index = 1;
        q.add(root);

        while(!q.isEmpty() && index < n){
            Node node = q.remove();
            int childrenCnt = node.children.size();
            System.out.println("child count: " + childrenCnt);
            if(childrenCnt > 0){
                for(int i = 0 ; i < childrenCnt ; ++i){
                    if(index < n){
                        String[] sss = s[index].split("-");
                        Node child = DIYnode(sss[0], sss[1]);
                        node.children.set(i, child);
                        q.add(child);
                        ++index;
                    }
                }
            }
        }
        return root;

    }

    private Node DIYnode(String v , String count) {
        int val = Integer.parseInt(v);
        int c = Integer.parseInt(count);

        List<Node> children = new ArrayList<>(c);

        for(int i = 0 ; i < c ; ++i){
            children.add(null);
        }

        return new Node(val , children);
    }
}
