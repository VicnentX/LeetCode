package google.google2020.google_加油少年_ResidentVO;

/*
Inorder Tree Traversal without recursion and without stack!
Using Morris Traversal, we can traverse the tree without using stack and recursion.
The idea of Morris Traversal is based on Threaded Binary Tree.
In this traversal, we first create links to Inorder successor and print the data using these links,
and finally revert the changes to restore original tree.

1. Initialize current as root
2. While current is not NULL
   If the current does not have left child
      a) Print current’s data
      b) Go to the right, i.e., current = current->right
   Else
      a) Make current as the right child of the rightmost
         node in current's left subtree
      b) Go to this left child, i.e., current = current->left


Although the tree is modified through the traversal,
it is reverted back to its original shape after the completion. Unlike Stack based traversal,
no extra space is required for this traversal.
 */

// Java program to print inorder traversal without recursion and stack

/* A binary tree tNode has data, a pointer to left child
and a pointer to right child */
class tNode {
    int data;
    tNode left, right;

    tNode(int item)
    {
        data = item;
        left = right = null;
    }
}

class BinaryTree {
    tNode root;

    /* Function to traverse a binary tree without recursion and
    without stack */
    void MorrisTraversal(tNode root)
    {
        tNode current, pre;

        if (root == null)
            return;

        current = root;
        while (current != null) {
            if (current.left == null) {
                System.out.print(current.data + " ");
                current = current.right;
            }
            else {
                /* Find the inorder predecessor of current */
                pre = current.left;
                while (pre.right != null && pre.right != current)
                    pre = pre.right;

                /* Make current as right child of its inorder predecessor */
                if (pre.right == null) {
                    pre.right = current;
                    current = current.left;
                }

				/* Revert the changes made in the 'if' part to restore the
					original tree i.e., fix the right child of predecessor*/
                else {
                    pre.right = null;
                    System.out.print(current.data + " ");
                    current = current.right;
                } /* End of if condition pre->right == NULL */

            } /* End of if condition current->left == NULL*/

        } /* End of while */
    }

    public static void main(String args[])
    {
		/* Constructed binary tree is
			1
			/ \
			2	 3
		/ \
		4	 5
		*/
        BinaryTree tree = new BinaryTree();
        tree.root = new tNode(1);
        tree.root.left = new tNode(2);
        tree.root.right = new tNode(3);
        tree.root.left.left = new tNode(4);
        tree.root.left.right = new tNode(5);

        tree.MorrisTraversal(tree.root);
    }
}

// This code has been contributed by Mayank Jaiswal(mayank_24)

