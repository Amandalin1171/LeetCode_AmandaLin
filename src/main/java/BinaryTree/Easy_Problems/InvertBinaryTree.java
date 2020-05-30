package BinaryTree.Easy_Problems;

import BinaryTree.TreeNode;

/**
 * 226. Invert Binary Tree
 * Invert a binary tree.
 *
 * Example:
 *
 * Input:
 *
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * Output:
 *
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 */
public class InvertBinaryTree {
  //方法1：类似于linkedlist dummy node 去撺掇一下
  public TreeNode invertTree(TreeNode root) {
    if(root == null) return null;
    TreeNode temp = root.left; //先用temp记录下来左子树
    root.left = invertTree(root.right); //更改左子树
    root.right = invertTree(temp); //用之前记录的没变的左子树作为input来更新右子树
    return root;
  }

  //方法2：分治思想，拆开来看
  public TreeNode invertTree2(TreeNode root) {
    if(root == null) return null;
    TreeNode left = invertTree(root.left);
    TreeNode right = invertTree(root.right);
    root.left = right;
    root.right = left;
    return root;
  }
}
