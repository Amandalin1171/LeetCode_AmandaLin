package BinaryTree.Easy_Problems;

/**
 * 938. Range Sum of BST
 * Given the root node of a binary search tree, return the sum of values of all nodes with value between L and R (inclusive).
 *
 * The binary search tree is guaranteed to have unique values.
 *
 * Example 1:
 * Input: root = [10,5,15,3,7,null,18], L = 7, R = 15
 * Output: 32
 * Example 2:
 *
 * Input: root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
 * Output: 23
 *
 * Note:
 * The number of nodes in the tree is at most 10000.
 * The final answer is guaranteed to be less than 2^31.
 */

import BinaryTree.TreeNode;

/**
 * DFS：
 * 方法1： 自己写的代码比较冗长，但是无所谓traverse的顺序
 * 方法2： preSum的方法，每一层去累加，判断条件和遍历的顺序非常固定不能更改
 */
public class RangeSumOfBST {
  //方法1：自己写的
  int sum = 0;
  public int rangeSumBST(TreeNode root, int L, int R) {
    //corner case
    if(L > R || root == null) return 0;
    return sum(root, L, R);
  }

  //每一层return上去一个数，遍历的顺序无所谓
  private int sum(TreeNode node, int L, int R) {
    if(node == null) return 0;
    if(node.val >= L && node.val <= R) return node.val + sum(node.left, L, R) + sum(node.right, L, R);
    else if(node.val < L) return sum(node.right, L, R);
    else if(node.val > R) return sum(node.left, L, R);

    return sum;
  }

  //方法2：很干净的代码，但是需要思路清晰不能更改遍历的顺序
  public int rangeSumBST2(TreeNode root, int L, int R) {
    if(root == null) return 0;
    int sum = 0;
    if(L < root.val) sum += rangeSumBST(root.left, L, R);
    if(root.val < R) sum += rangeSumBST(root.right, L, R);
    if(root.val >= L && root.val <= R) sum += root.val;
    return sum;
  }

}
