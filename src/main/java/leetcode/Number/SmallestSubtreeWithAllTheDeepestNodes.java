package leetcode.Number;
import javax.swing.tree.*;
import java.util.*;

public class SmallestSubtreeWithAllTheDeepestNodes {

//    Deque<TreeNode> dq = new ArrayDeque<>();
//    List<Deque<TreeNode>> ret = new ArrayList<>();
//
//    public TreeNode subtreeWithAllDeepest(TreeNode root){
//        getLongestPath(root);
//        HashMap<TreeNode,Integer> map = new HashMap<>();
//
//        int m = ret.size();
//        int n = ret.get(0).size();
//        for(int i = 0; i < n; ++i){
//            for(int j = 0; j < m; ++j){
//                TreeNode node = ret.get(j).getLast();
//                map.put(node, map.getOrDefault(node, 0 ) + 1);
//                if(map.get(node) == m) {
//                    return ret.get(j).getLast();
//                }
//                ret.get(j).pollLast();
//            }
//        }
//        return null;
//    }
//
//    private void getLongestPath (TreeNode root){
//        dq.addLast(root);
//
//        if (root.left == null && root.right == null) {
//            if (ret.size() == 0) {
//                ret.add(new ArrayDeque<>(dq));
//            }else{
//                if(dq.size() > ret.get(0).size()){
//                    ret.clear();
//                    ret.add(new ArrayDeque<>(dq));
//                }else if((dq.size() == ret.get(0).size())){
//                    ret.add(new ArrayDeque<>(dq));
//                }
//            }
//        }else{
//            if(root.left != null){
//                getLongestPath(root.left);
//            }
//            if(root.right != null){
//                getLongestPath(root.right);
//            }
//        }
//        dq.peekLast();
//    }


    Deque<TreeNode> dq = new ArrayDeque<>();
    List<Deque<TreeNode>> ret = new ArrayList<>();

    public TreeNode subtreeWithAllDeepest(TreeNode root){
        getLongestPath(root);
        HashMap<Integer,Integer> map = new HashMap<>();

        int m = ret.size();
        int n = ret.get(0).size();
        for(int i = 0; i < n; ++i){
            for(int j = 0; j < m; ++j){
                int value = ret.get(j).getLast().val;
                map.put(value, map.getOrDefault(value, 0 ) + 1);
                if(map.get(value) == m) {
                    return ret.get(j).getLast();
                }
                ret.get(j).pollLast();
            }
        }
        return null;
    }

    private void getLongestPath (TreeNode root){
        dq.addLast(root);

        if (root.left == null && root.right == null) {
            if (ret.size() == 0) {
                ret.add(new ArrayDeque<>(dq));
            }else{
                if(dq.size() > ret.get(0).size()){
                    ret.clear();
                    ret.add(new ArrayDeque<>(dq));
                }else if((dq.size() == ret.get(0).size())){
                    ret.add(new ArrayDeque<>(dq));
                }
            }
        }else{
            if(root.left != null){
                getLongestPath(root.left);
            }
            if(root.right != null){
                getLongestPath(root.right);
            }
        }
        dq.pollLast();
    }



    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

}

