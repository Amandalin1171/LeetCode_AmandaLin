package BinaryTree.Easy_Problems;

import BinaryTree.TreeNode;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 104. Maximum Depth of Binary Tree
 * Given a binary tree, find its maximum depth.
 *
 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 *
 * Note: A leaf is a node with no children.
 *
 * Example:
 *
 * Given binary tree [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its depth = 3.
 */

/**
 * 方法1： recursion
 * 探索树的深度的recursion是一种分治或者bottom up的思想，就是：
 * Height_root = max(Height_left, Height_right) + 1;
 * 每一个node的高度都是它的左右子树的高度 + 1， 一路向上，最终的高度就是root这一个node的高度
 *
 * 方法2： iteration
 * 基本的BFS一层一层往里面放就完事了
 */
public class MaximumDepthOfBinaryTree {
  //方法1： recursion:
  public int maxDepth(TreeNode root) {
    if(root == null) return 0;
    int leftHeight = maxDepth(root.left);
    int rightHeight = maxDepth(root.right);
    return Math.max(leftHeight, rightHeight) + 1;
  }

  //方法2：iterate
  public int maxDepth_2(TreeNode root) {
    if(root == null) return 0;
    Queue<TreeNode> queue = new LinkedList<>();
    int depth = 0;

    queue.offer(root);
    while(!queue.isEmpty()) {
      depth++;
      int size = queue.size();
      for(int i = 0; i < size; i++) {
        TreeNode curr = queue.poll();
        if(curr.left != null) queue.offer(curr.left);
        if(curr.right != null) queue.offer(curr.right);
      }
    }
    return depth;
  }
}
