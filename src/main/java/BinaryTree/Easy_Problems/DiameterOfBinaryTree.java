package BinaryTree.Easy_Problems;

/**
 * 543. Diameter of Binary Tree
 * Given a binary tree, you need to compute the length of the diameter of the tree.
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
 * This path may or may not pass through the root.
 *
 * Example:
 * Given a binary tree
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
 *
 * Note: The length of path between two nodes is represented by the number of edges between them.
 */

import BinaryTree.TreeNode;

/**
 * 非常典型的dfs recursion function每一层返回给上一层的东西不是最终要的答案
 * 用一个global的变量去记录要记录的最终答案。
 * 类似题：687. Longest Univalue Path
 *        124. Binary Tree Maximum Path Sum
 */
public class DiameterOfBinaryTree {
  int max = 0; //最终要的答案
  public int diameterOfBinaryTree(TreeNode root) {
    dfs(root);
    return max;
  }

  private int dfs(TreeNode node) {
    if(node == null) return 0;
    int leftMax = dfs(node.left);
    int rightMax = dfs(node.right);
    max = Math.max(max, leftMax + rightMax); //记录最终要的答案：直径，不是每层返回的值，只是每一次结算存储到global变量里
    return Math.max(leftMax, rightMax) + 1; //recursion每一层返回给上一层的东西
  }
}
