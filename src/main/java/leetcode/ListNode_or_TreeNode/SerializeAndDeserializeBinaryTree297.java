package leetcode.ListNode_or_TreeNode;

import java.util.*;

public class SerializeAndDeserializeBinaryTree297 {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        code(sb , root);
        return sb.toString();
    }
    private void code(StringBuilder sb ,  TreeNode node){
        if(node == null){
            sb.append("N").append(",");
        }else{
            sb.append(node.val).append(",");
            code(sb , node.left);
            code(sb , node.right);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> q = new LinkedList<>();
        q.addAll(Arrays.asList(data.split(",")));
        return decode(q);
    }
    private TreeNode decode(Queue<String> q){
        String cur = q.poll();
        if(cur.equals("N")){
            return null;
        }else{
            TreeNode node = new TreeNode(Integer.parseInt(cur));
            node.left = decode(q);
            node.right = decode(q);
            return node;
        }
    }
}
