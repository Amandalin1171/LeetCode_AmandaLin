package BinaryTree.Easy_Problems;

import BinaryTree.TreeNode;

/**
 * 669. Trim a Binary Search Tree
 * Given a binary search tree and the lowest and highest boundaries as L and R,
 * trim the tree so that all its elements lies in [L, R] (R >= L). You might need to change the root of the tree,
 * so the result should return the new root of the trimmed binary search tree.
 *
 * Example 1:
 * Input:
 *     1
 *    / \
 *   0   2
 *
 *   L = 1
 *   R = 2
 *
 * Output:
 *     1
 *       \
 *        2
 * Example 2:
 * Input:
 *     3
 *    / \
 *   0   4
 *    \
 *     2
 *    /
 *   1
 *
 *   L = 1
 *   R = 3
 *
 * Output:
 *       3
 *      /
 *    2
 *   /
 *  1
 */
public class TrimABinarySearchTree {
  //自己的code:
  public TreeNode trimBST(TreeNode root, int L, int R) {
    //base case
    if(root == null) return null;

    //符合往下挪动node的条件，挪动node
    if(L <= root.val) root.left = trimBST(root.left, L, R);
    if(root.val <= R) root.right = trimBST(root.right, L, R);

    //重复的逻辑：点out of bound就变成左边或者右边的点
    if(root.val < L) root = root.right;
    else if(root.val > R) root = root.left;
    return root;
  }

  //答案的code:
  public TreeNode trimBST_2(TreeNode root, int L, int R) {
    if (root == null) return root;
    if (root.val > R) return trimBST(root.left, L, R);
    if (root.val < L) return trimBST(root.right, L, R);

    root.left = trimBST(root.left, L, R);
    root.right = trimBST(root.right, L, R);
    return root;
  }
}
