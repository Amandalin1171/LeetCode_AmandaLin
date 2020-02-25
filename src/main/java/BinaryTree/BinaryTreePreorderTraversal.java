package BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 题目：
 * 144. Binary Tree Preorder Traversal
 *
 * Given a binary tree, return the preorder traversal of its nodes' values.
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
 * Output: [1,2,3]
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 */

//方法1： Recursion
public class BinaryTreePreorderTraversal {
  public List<Integer> PreorderTraversalRecursion(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    if(root == null) return res;
    helper(root, res);
    return res;
  }
  private void helper(TreeNode root, List<Integer> res) {
    res.add(root.val);
    if(root.left != null) helper(root.left, res);
    if(root.right != null) helper(root.right, res);
  }

  //方法2： Iterate
  public List<Integer> reorderTraversalIterate(TreeNode root) {
    Stack<TreeNode> stack = new Stack<>();
    List<Integer> list = new LinkedList<>();
    if(root == null) return list;
    //iterate的方法就是：用stack
    //先把root push进去，然后先pop出来
    //之后就先push右边，再push，这样pop出来的是先左边再右边
    stack.push(root);
    while(!stack.isEmpty()) {
      TreeNode node = stack.pop();
      list.add(node.val);
      if(node.right != null) stack.push(node.right);
      if(node.left != null) stack.push(node.left);
    }
    return list;
  }


  //Test Case
  public static void main(String[] args) {
    BinaryTreePreorderTraversal testClass = new BinaryTreePreorderTraversal();
    TreeNode a = new TreeNode(1);
    TreeNode b = new TreeNode(2);
    TreeNode c = new TreeNode(3);
    a.right = b;
    b.left = c;
    List<Integer> res = new ArrayList<>();
    List<Integer> res2 = new LinkedList<>();
    res = testClass.PreorderTraversalRecursion(a);
    res2 = testClass.reorderTraversalIterate(a);
    for(int i = 0; i < res.size(); i++) {
      System.out.println(res.get(i));
    }

    for(int i = 0; i < res2.size(); i++) {
      System.out.println(res2.get(i));
    }
  }
}
