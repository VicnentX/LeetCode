package leetcode.Number;


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// Solution solution = new Solution();
// // case 1
// solution.str2tree("some test string");
// // case 2:
// solutino.str2tree("second test string");



public class ConstructBinaryTree {

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }


    private int k = 0;

    public TreeNode str2tree(String s){
        k = 0;//这里k=0是防止只建立一个solution然后做多次测试。。。
        return str2treeHelp(s);
    }

    public TreeNode str2treeHelp(String s){
        TreeNode cur = null;
        boolean isLeft = true;

        while (k < s.length()){

            if(s.charAt(k) == '('){
                ++k;

                if(isLeft){
                    cur.left = str2treeHelp(s);
                    isLeft = false;
                }else{
                    cur.right = str2treeHelp(s);
                    isLeft = true;
                }
            }

            else if(s.charAt(k) == ')'){
                ++k;
                return cur;
            }

            else{
                int start = k;
                while(k < s.length() && s.charAt(k) != '(' && s.charAt(k) != ')'){
                    ++k;
                }
                cur = new TreeNode(Integer.parseInt(s.substring(start,k)));
            }

        }

        return cur;
    }

    public static void main(String[] args){
        ConstructBinaryTree cb = new ConstructBinaryTree();
        System.out.println(cb.str2tree("4(2(3)(1))(6(5))").toString());//这边没办法输出tree 要写自己function
    }

}
