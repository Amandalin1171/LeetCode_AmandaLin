package DFS;

import BinaryTree.TreeNode;

/**
 * 129. Sum Root to Leaf Numbers
 *
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 *
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 *
 * Find the total sum of all root-to-leaf numbers.
 *
 * Note: A leaf is a node with no children.
 *
 * Example:
 *
 * Input: [1,2,3]
 *     1
 *    / \
 *   2   3
 * Output: 25
 * Explanation:
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * Therefore, sum = 12 + 13 = 25.
 */
public class SumRootToLeafNumbers {
  //方法1， 自己写的，但是由于dfs return void,所以要用array int[] res来传参记录结果。
  public int sumNumbers(TreeNode root) {
    int[] res = new int[1];
    dfs(root, new StringBuilder(), res);
    return res[0];
  }

  //dfs return void
  private void dfs(TreeNode node, StringBuilder sb, int[] res) {
    int l = sb.length();
    if(node == null) return;
    if(node.left == null && node.right == null) {
      sb.append(node.val);
      res[0] = res[0] + Integer.parseInt(sb.toString());
      sb.setLength(l);
    }
    sb.append(node.val);
    dfs(node.left, sb, res);
    dfs(node.right, sb, res);
    sb.setLength(l);
  }

  //方法2： dfs return int, 可以传入int作为参数：
  public int sumNumbers2(TreeNode root) {
    return helper(root, 0);
  }

  //dfs helper return int
  public int helper(TreeNode root, int sum) {
    if(root == null) return 0;
    if(root.left == null && root.right == null) {
      sum = sum * 10 + root.val;
    } else {
      return helper(root.left, sum * 10 + root.val) + helper(root.right, sum * 10 + root.val);
    }
    return sum;
  }
}
