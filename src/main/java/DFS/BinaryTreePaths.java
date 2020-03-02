package DFS;

import BinaryTree.TreeNode;
import java.util.ArrayList;
import java.util.List;

/**
 * 题目：
 * 257. Binary Tree Paths
 *
 * Given a binary tree, return all root-to-leaf paths.
 *
 * Note: A leaf is a node with no children.
 *
 * Example:
 *
 * Input:
 *
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 *
 * Output: ["1->2->5", "1->3"]
 *
 * Explanation: All root-to-leaf paths are: 1->2->5, 1->3
 */

/**
 * 笔记：
 * StringBuilder append(X x): Java自带的type可以append的时候被转换成String比如Integer会变成String
 * This method appends the string representation of the X type argument to the sequence.
 */
public class BinaryTreePaths {
  //方法1： Using helper function, 遵循DFS + Backtracking的一贯套路：
  //DFS + Backtracking 都有三个步骤：
  //Add element
  //DFS
  //Remove element
  public List<String> binaryTreePaths(TreeNode root) {
    List<String> res = new ArrayList<>();
    if(root == null) return res;
    dfs(root, res, new StringBuilder());
    return res;
  }

  private void dfs(TreeNode node, List<String> res, StringBuilder sb) {
    //每层循环先判断此刻的node是不是null
    if(node == null) return;
    int l = sb.length(); // 记录加新node.val的时候sb的长度
    sb.append(node.val); // 加入此刻的node的val，这也保证了后面再append->的时候保证箭头前面有数
    //退出条件：node是leaf左子树右子树都木有了
    if(node.left == null && node.right == null) {
      res.add(sb.toString());
      sb.setLength(l); // remove最后一位，就是把长度set成之前记录的那个l
      return;
    }
    sb.append("->");
    dfs(node.left, res, sb);
    dfs(node.right, res, sb);
    sb.setLength(l);
  }

  //Test Case
  public static void main(String[] args) {
    BinaryTreePaths testClass = new BinaryTreePaths();
    TreeNode a = new TreeNode(1);
    TreeNode b = new TreeNode(2);
    TreeNode c = new TreeNode(3);
    TreeNode d = new TreeNode(5);
    a.left = b;
    a.right = c;
    b.right = d;
    List<String> res = testClass.binaryTreePaths(a);
    for(int i = 0; i < res.size(); i++) {
      System.out.println(res.get(i));
    }
  }
}
