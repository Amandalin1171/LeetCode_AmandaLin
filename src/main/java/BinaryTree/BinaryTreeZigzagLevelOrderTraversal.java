package BinaryTree;

/**
 * 题目：
 * 103. Binary Tree Zigzag Level Order Traversal
 * 说白了就是从上至下level-order-traversal的时候扭扭捏捏，每一行从左至右从右至左不一样
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its zigzag level order traversal as:
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 */

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import sun.awt.image.ImageWatched.Link;

/**
 * 笔记：
 * <1> 这里面来练习一个很常用的东西，一个boolean flag，用来进行标记，每次循环的时候颠倒黑白起到改变逻辑的作用
 * 这里就用一个zigzag的Boolean flag来标记：
 * if zigzag == false, traverse from left to right;
 * if zigzag == true, traverse from right to left.
 * <2> 有一个关键点就是，可以用linkedlist的add(index=0, value)语法决定每次插入的位置都是第一个
 * Signature里面写的返回值是list,所以可以是linkedlist也可以是arraylist，linkedlist每次插在最前面时间复杂度低
 * 当然本题也可以用Deque每次塞的位置是头或者尾巴，就是写的时候脑子要清晰，这种写法不需要Boolean flag
 */
public class BinaryTreeZigzagLevelOrderTraversal {
  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> res = new LinkedList<>();
    if(root == null) return res;
    Queue<TreeNode> queue = new LinkedList<>();
    boolean zigzag = false;
    queue.offer(root);
    while(!queue.isEmpty()) {
      List<Integer> level = new LinkedList<>();
      int size = queue.size();
      for(int i = 0; i < size; i++) {
        TreeNode node = queue.poll();
        if(zigzag) level.add(0, node.val);
        else level.add(node.val);
        if(node.left != null) queue.offer(node.left);
        if(node.right != null) queue.offer(node.right);
      }
      res.add(level);
      zigzag = !zigzag;
    }
    return res;
  }

  //Test case
  public static void main(String[] args) {
    BinaryTreeZigzagLevelOrderTraversal testClass = new BinaryTreeZigzagLevelOrderTraversal();
    TreeNode a = new TreeNode(3);
    TreeNode b = new TreeNode(9);
    TreeNode c = new TreeNode(20);
    TreeNode d = new TreeNode(15);
    TreeNode e = new TreeNode(7);
    a.left = b;
    a.right = c;
    c.left = d;
    c.right = e;
    List<List<Integer>> res = testClass.zigzagLevelOrder(a);
    for(int i = 0; i < res.size(); i++) {
      List<Integer> curr = res.get(i);
      for(int j = 0; j < curr.size(); j++) {
        System.out.println(curr.get(j));
      }
    }
  }

}
