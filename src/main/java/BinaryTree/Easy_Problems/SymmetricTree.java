package BinaryTree.Easy_Problems;

import BinaryTree.TreeNode;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 101. Symmetric Tree
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 *
 * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *
 *
 * But the following [1,2,2,null,3,null,3] is not:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 *
 * Follow up: Solve it both recursively and iteratively.
 */
public class SymmetricTree {
  //方法1： recursion
  public boolean isSymmetric(TreeNode root) {
    return isMirror(root, root); //35行的逻辑cover了root == null的情况，所以不用写corner case
  }

  private boolean isMirror(TreeNode left, TreeNode right) {
    if(left == null && right == null) return true;
    else if(left == null || right == null) return false;
    if(left.val == right.val) {
      return isMirror(left.left, right.right) && isMirror(left.right, right.left);
    }
    return false;
  }

  //方法2：iteration
  public boolean isSymmetric_2(TreeNode root) {
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    queue.offer(root);

    while(!queue.isEmpty()) {
      TreeNode curr1 = queue.poll();
      TreeNode curr2 = queue.poll();
      if(curr1 == null && curr2 == null) continue; //这里不能直接return true啊，可能到一边的leaf但是其余部分还没有遍历呢啊不能用recursion思维呀！！！
      else if(curr1 == null || curr2 == null) return false;
      else if(curr1.val != curr2.val) return false;
      else {
        queue.offer(curr1.left);
        queue.offer(curr2.right);
        queue.offer(curr1.right);
        queue.offer(curr2.left);
      }
    }
    return true;
  }
}
