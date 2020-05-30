package BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 题目：
 * 94. Binary Tree Inorder Traversal
 *
 * Given a binary tree, return the inorder traversal of its nodes' values.
 *
 * Example:
 *
 * Input: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * Output: [1,3,2]
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 */

//方法1： Recursion
public class BinaryTreeInorderTraversal {
  public List<Integer> inorderTraversalRecursion(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    if(root == null) return res;
    helper(root, res);
    return res;
  }

  private void helper(TreeNode root, List<Integer> res) {
    if(root.left != null) helper(root.left, res);
    res.add(root.val);
    if(root.right != null) helper(root.right, res);
  }

  //方法2： Iterate
  public List<Integer> inorderTraversalIterate(TreeNode root) {
    List<Integer> res = new LinkedList<>();
    Stack<TreeNode> stack = new Stack<>();

    if(root == null) return res;
    while(root != null || !stack.isEmpty()) {
      while(root != null) {
        stack.push(root);
        root = root.left;
      }
      root = stack.pop();
      res.add(root.val);
      root = root.right;
    }
    return res;
  }

  //Test Case
  public static void main(String[] args) {
    BinaryTreeInorderTraversal testClass = new BinaryTreeInorderTraversal();
    TreeNode a = new TreeNode(1);
    TreeNode b = new TreeNode(2);
    TreeNode c = new TreeNode(3);
    a.right = b;
    b.left = c;
    List<Integer> res = new ArrayList<>();
    List<Integer> res2 = new LinkedList<>();
    res = testClass.inorderTraversalRecursion(a);
    res2 = testClass.inorderTraversalIterate(a);
    for(int i = 0; i < res.size(); i++) {
      System.out.println(res.get(i));
    }

    for(int i = 0; i < res2.size(); i++) {
      System.out.println(res2.get(i));
    }
  }
}
