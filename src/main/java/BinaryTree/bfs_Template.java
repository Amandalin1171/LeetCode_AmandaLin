package BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 说是BFS的template
 * 其实就是最基础的level-order-travesal
 * 102. Binary Tree Level Order Traversal
 *
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its level order traversal as:
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 */
public class bfs_Template {
  public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
    ArrayList res = new ArrayList();
    if(root == null) return res;

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while(!queue.isEmpty()) {
      ArrayList<Integer> level = new ArrayList<>();
      int size = queue.size();
      for(int i = 0; i < size; i++) {
        TreeNode head = queue.poll();
        level.add(head.val);
        if(head.left != null) queue.offer(head.left);
        if(head.right != null) queue.offer(head.right);
      }
      res.add(level);
    }
    return res;
  }

  public static void main(String[] args) {
    bfs_Template testClass = new bfs_Template();
    TreeNode a = new TreeNode(3);
    TreeNode b = new TreeNode(9);
    TreeNode c = new TreeNode(20);
    TreeNode d = new TreeNode(15);
    TreeNode e = new TreeNode(7);
    a.left = b;
    a.right = c;
    c.left = d;
    c.right = e;
    ArrayList<ArrayList<Integer>> res = testClass.levelOrder(a);
    for(int i = 0; i < res.size(); i++) {
      ArrayList<Integer> curr = res.get(i);
      for(int j = 0; j < curr.size(); j++) {
        System.out.println(curr.get(j));
      }
    }
  }
}
