package google.google2020.google_加油少年_ResidentVO;

import apple.laf.JRSUIUtils;

public class CRUD {

    class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode (int x) {
            val = x;
        }
    }

    public static TreeNode root;

    public CRUD(int x) {
        root = new TreeNode(x);
    }

    public void generte(TreeNode root) {

    }

    public boolean find(int target) {
        TreeNode current = root;
        while (current != null) {
            if (current.val == target) return true;
            else if (current.val > target) current = current.left;
            else current = current.right;
        }
        return false;
    }

    /**
     * Insert(int n):
     *
     * Very much similar to find() operation.
     * To insert a node our first task is to find the place to insert the node.
     * Take current = root .
     * start from the current and compare root.data with n
     * if current.data is greater than n that means we need to go to the left of the root.
     * if current.data is smaller than n that means we need to go to the right of the root.
     * if any point of time current is null that means we have reached
     * to the leaf node, insert your node here with the help of parent node.
     * (See code)
     * @param num
     * @return
     */

    public int insert(int num) {
        if (root == null) {
            root = new TreeNode(num);
            return root.val;
        }
        TreeNode current = root;
        TreeNode parent = null;
        while(true) {
            parent = current;
            if (num < current.val) {
                current = current.left;
                if (current == null) {
                    parent.left = new TreeNode(num);
                    return num;
                }
            } else {
                current = current.right;
                if (current == null) {
                    parent.right = new TreeNode(num);
                    return num;
                }
            }
        }
    }

    /**
     * Delete(int n):
     *
     * Complicated than Find() and Insert() operations.
     * Here we have to deal with 3 cases.
     *
     * Node to be deleted is a leaf node ( No Children).
     * Node to be deleted has only one child.
     * Node to be deleted has two childrens.
     * @param remove
     * @return
     */

    public boolean delete(int remove) {
        TreeNode parent = root, current = root;
        boolean isLeftChild = false;
        while (current.val != remove) {
            parent = current;
            if (current.val > remove) {
                isLeftChild = true;
                current = current.left;
            } else {
                isLeftChild = false;
                current = current.right;
            }
            if (current == null) {
                return false;
            }
        }
        //if I am here that means we have found the node to be deleted

        //case 1: if node to be deleted has no children
        if (current.left == null && current.right == null) {
            if (current == root) {
                root = null;
            }
            if (isLeftChild == true) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }


        //case 2: if node to be deleted has only one child
        else if (current.right == null) {
            if (current == root) root = current.left;
            else if (isLeftChild) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
        }

        else if (current.left == null) {
            if (current == root) root = root.right;
            else if (isLeftChild) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        }

        //case 3: has two children and we want to find the min element in the right sub tree
        else {
            TreeNode successor = getSuccessor(current);
            if (current == root) root = successor;
            else if (isLeftChild) parent.left = successor;
            else parent.right = successor;
            successor.left = current.left;
        }

        return true;
    }

    private TreeNode getSuccessor(TreeNode deleteNode) {
        TreeNode successor = null;
        TreeNode successorParent = null;
        TreeNode current = deleteNode.right;
        while(current != null) {
            successorParent = successor;
            successor = current;
            current = current.left;
        }
        //check if successor has the right child, it cannot has left child for sure
        //if it does have the right child, add it to the left of successorParent
        if (successor != deleteNode.right) {
            successorParent.left = successor.right;
            successor.right = deleteNode.right;
        }
        return successor;
    }

    public void inOrderPrint(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        inOrderPrint(root.left);
        inOrderPrint(root.right);
    }

    public static void main(String[] args) {
        CRUD crud = new CRUD(0);
        System.out.println(crud.insert(1));
        System.out.println(crud.insert(2));
        crud.inOrderPrint(root);
        System.out.println("loop done ");
        System.out.println(crud.find(1));
        System.out.println(crud.find(6));
        System.out.println(crud.insert(-1));
        System.out.println(crud.delete(1));
        crud.inOrderPrint(root);
        System.out.println("loop done ");
    }
}
