package BinaryTree.Easy_Problems;

import BinaryTree.TreeNode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 637. Average of Levels in Binary Tree
 * Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.
 * Example 1:
 * Input:
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * Output: [3, 14.5, 11]
 * Explanation:
 * The average value of nodes on level 0 is 3,  on level 1 is 14.5, and on level 2 is 11. Hence return [3, 14.5, 11].
 * Note:
 * The range of node's value is in the range of 32-bit signed integer.
 */
public class AverageOfLevelsInBinaryTree {
  public List<Double> averageOfLevels(TreeNode root) {
    Queue<TreeNode> queue = new LinkedList<>();
    List<Double> res = new ArrayList<>();
    //corner case
    if(root == null) return res;

    //BFS
    queue.offer(root);
    while(!queue.isEmpty()) {
      int size = queue.size();
      double curr = 0d;
      for(int i = 0; i < size; i++) {
        TreeNode node = queue.poll();
        curr = curr + node.val;
        //不能累加：不能写成：curr = (curr + node.val) /(i + 1); 因为每一步除法会整除会污染数字变dirty
        //System.out.println(curr);
        if(node.left != null) queue.offer(node.left);
        if(node.right != null) queue.offer(node.right);
      }
      res.add(curr / size);
    }
    return res;
  }
}
