package BinaryTree.Easy_Problems;

import BinaryTree.TreeNode;
import java.util.ArrayList;
import java.util.List;

/**
 * 783. Minimum Distance Between BST Nodes
 * Given a Binary Search Tree (BST) with the root node root, return the minimum difference between the values of any two different nodes in the tree.
 *
 * Example :
 *
 * Input: root = [4,2,6,1,3,null,null]
 * Output: 1
 * Explanation:
 * Note that root is a TreeNode object, not an array.
 *
 * The given tree [4,2,6,1,3,null,null] is represented by the following diagram:
 *
 *           4
 *         /   \
 *       2      6
 *      / \
 *     1   3
 *
 * while the minimum difference in this tree is 1, it occurs between node 1 and node 2, also between node 3 and node 2.
 * Note:
 *
 * The size of the BST will be between 2 and 100.
 * The BST is always valid, each node's value is an integer, and each node's value is different.
 * This question is the same as 530: https://leetcode.com/problems/minimum-absolute-difference-in-bst/
 */
public class MinimumDistanceBetweenBSTNodes {
  //一看到BST，第一个想法就是利用inorder traversal得到sorted array然后干啥都方便
  public int minDiffInBST(TreeNode root) {
    List<Integer> list = new ArrayList<>();
    inorder(root, list);
    int min = Integer.MAX_VALUE;
    for(int i = 1; i < list.size(); i++) {
      min = Math.min(min, Math.abs(list.get(i) - list.get(i - 1)));
    }
    return min;
  }

  private void inorder(TreeNode node,  List<Integer> list) {
    if(node == null) return;
    inorder(node.left, list);
    list.add(node.val);
    inorder(node.right, list);
  }
}
