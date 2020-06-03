package BinaryTree.Easy_Problems;

import BinaryTree.TreeNode;

/**
 * 687. Longest Univalue Path
 * Given a binary tree, find the length of the longest path where each node in the path has the same value.
 * This path may or may not pass through the root.
 * The length of path between two nodes is represented by the number of edges between them.
 *
 * Example 1:
 * Input:
 *
 *               5
 *              / \
 *             4   5
 *            / \   \
 *           1   1   5
 * Output: 2
 *
 * Example 2:
 * Input:
 *
 *               1
 *              / \
 *             4   5
 *            / \   \
 *           4   4   5
 * Output: 2
 *
 * Note: The given binary tree has not more than 10000 nodes. The height of the tree is not more than 1000.
 */
public class LongestUnivaluePath {

  /**
   * 方法1：类似于543. Diameter of Binary Tree的思路
   * 每次返回上一层的是比较左右子树的符合条件的最长的长度
   * 但是需要一个global的变量去记录max
   * 因为这个结果要参与到recursion中并且要在每一层的recursion更新，但是不是每一层recursion返回给上一层的值
   */
  int max = 0;
  public int longestUnivaluePath(TreeNode root) {
    if(root == null) return 0;
    dfs(root, root.val); //因为dfs里面传进去了node.val，所以一开始的root不能是null 虽然dfs base case写了null return 0, 这里还是要在最前面写corner case
    return max;
  }

  private int dfs(TreeNode node, int val) {
    if(node == null) return 0;
    int leftMax = dfs(node.left, node.val);
    int rightMax = dfs(node.right, node.val);
    max = Math.max(max, leftMax + rightMax);
    if(val == node.val) return Math.max(leftMax, rightMax) + 1;
    return 0;
  }

  /**
   * 方法2：更加直观的方法，如果数值相等就++，不相等就清零
   * bottom - up 的 recursion
   * https://leetcode.com/problems/longest-univalue-path/discuss/130315/Java-Solution-With-Explanation
   */
  int max2 = 0;
  public int longestUnivaluePath2(TreeNode root) {
    helper(root);
    return max2;
  }
  private int helper(TreeNode curr){
    if(curr == null) return 0;
    int left = helper(curr.left), right = helper(curr.right);
    if(curr.left != null && curr.val == curr.left.val)
      left += 1;
    else
      left = 0;
    if(curr.right != null && curr.val == curr.right.val)
      right += 1;
    else
      right = 0;
    max2 = Math.max(max2, left+right);
    return Math.max(left, right);
  }
}
