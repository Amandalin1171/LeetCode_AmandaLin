package BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 题目：
 * 107. Binary Tree Level Order Traversal II
 * 说白了就是level-order-traversal(BFS template)的从下至上颠倒过来打印
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its bottom-up level order traversal as:
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 */

/**
 * 笔记：
 * 想从底下往上面打印，那就不直接将每一层的结果存在res里面去，而是先放到一个stack中呗
 * 然后再捯饬一遍Stack从里面一个个pop放到res就完事啦！
 * 颠倒顺序这种事情，一开始就想到Stack啦~~~
 */
public class BinaryTreeLevelOrderTraversal_II {
  public List<List<Integer>> levelOrderBottom(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    if(root == null) return res;
    Queue<TreeNode> queue = new LinkedList<>();
    Stack<List<Integer>> stack = new Stack<>(); //用来调换结果顺序的Stack

    queue.offer(root);
    while(!queue.isEmpty()) {
      List<Integer> level = new ArrayList<>();
      int size = queue.size();
      for(int i = 0; i < size; i++) {
        TreeNode head = queue.poll();
        level.add(head.val);
        if(head.left != null) queue.offer(head.left);
        if(head.right != null) queue.offer(head.right);
      }
      stack.push(level);//先把结果一层一层放到stack里面
    }
    //再从stack里面pop出来一个个放到res里面去，根据后进先出原则，就成了！
    while(!stack.isEmpty()) {
      res.add(stack.pop());
    }
    return res;
  }

  //Test case
  public static void main(String[] args) {
    BinaryTreeLevelOrderTraversal_II testClass = new BinaryTreeLevelOrderTraversal_II();
    TreeNode a = new TreeNode(3);
    TreeNode b = new TreeNode(9);
    TreeNode c = new TreeNode(20);
    TreeNode d = new TreeNode(15);
    TreeNode e = new TreeNode(7);
    a.left = b;
    a.right = c;
    c.left = d;
    c.right = e;
    List<List<Integer>> res = testClass.levelOrderBottom(a);
    for(int i = 0; i < res.size(); i++) {
      List<Integer> curr = res.get(i);
      for(int j = 0; j < curr.size(); j++) {
        System.out.println(curr.get(j));
      }
    }
  }
}
