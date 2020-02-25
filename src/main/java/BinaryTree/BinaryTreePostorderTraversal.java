package BinaryTree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 题目：
 * 145. Binary Tree Postorder Traversal
 *
 * Given a binary tree, return the postorder traversal of its nodes' values.
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
 * Output: [3,2,1]
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 */

//方法1： Recursion
public class BinaryTreePostorderTraversal {
  public List<Integer> postorderTraversalRecursion(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    if(root == null) return res;
    helper(root, res);
    return res;
  }
  private void helper(TreeNode root, List<Integer> res) {
    if(root.left != null) helper(root.left, res);
    if(root.right != null) helper(root.right, res);
    res.add(root.val);
  }

  //方法2： Iterate使用Deque
  //关键是理解
  // 1. addFirst: Adds an element to the head.
  // 2. add(element): Adds an element to the tail.
  // 3. pollLast(): Retrieves and removes the last element of this deque,
  //    or returns null if this deque is empty.
  //***所以就算是先拿到的右子树，再拿到左子树，左子树那一步addFirst就让左子树在前了！！！***
  public List<Integer> postorderTraversalIterate(TreeNode root) {
    Deque<Integer> list = new LinkedList<>();
    Deque<TreeNode> temp = new LinkedList<>();

    List<Integer> res = new ArrayList<>();

    if(root == null) return res;

    temp.add(root);
    while(!temp.isEmpty()) {
      TreeNode node = temp.pollLast(); //每次都是从后面poll出来，加到结果的最前面
      list.addFirst(node.val); //addFirst是每次都把拿到的element加到最前面
      //所以就算是先拿到的右子树，再拿到左子树，左子树那一步addFirst就让左子树在前了！！！
      //总的看来：
      //上面两步一开始已经把root加进去再放到结果的尾巴上了；
      //之后加左子树 -> 加右子树 -> pollLast把右子树拿到 -> addFirst把右子树放到最前面
      //-> 再pollLast把左子树拿到 -> addFirst把左子树放到最前面，就瞬间到右子树前面了！！！
      //总体从前往后看就是 左 -> 右 -> root
      if(node.left != null) { //先加左子树
        temp.add(node.left);
      }

      if(node.right != null) { //再加右子树
        temp.add(node.right);
      }
    }
    while(!list.isEmpty()) {
      res.add(list.poll());
    }
    return res;
  }

  //Test Case
  public static void main(String[] args) {
    BinaryTreePostorderTraversal testClass = new BinaryTreePostorderTraversal();
    TreeNode a = new TreeNode(1);
    TreeNode b = new TreeNode(2);
    TreeNode c = new TreeNode(3);
    a.right = b;
    b.left = c;
    List<Integer> res = new ArrayList<>();
    List<Integer> res2 = new LinkedList<>();
    res = testClass.postorderTraversalRecursion(a);
    res2 = testClass.postorderTraversalIterate(a);
    for(int i = 0; i < res.size(); i++) {
      System.out.println(res.get(i));
    }

    for(int i = 0; i < res2.size(); i++) {
      System.out.println(res2.get(i));
    }
  }

}
