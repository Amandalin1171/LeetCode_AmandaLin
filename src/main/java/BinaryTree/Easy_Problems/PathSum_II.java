package BinaryTree.Easy_Problems;

import BinaryTree.TreeNode;
import java.util.ArrayList;
import java.util.List;

/**
 * 113. Path Sum II
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 *
 * Note: A leaf is a node with no children.
 *
 * Example:
 *
 * Given the below binary tree and sum = 22,
 *
 *       5
 *      / \
 *     4   8
 *    /   / \
 *   11  13  4
 *  /  \    / \
 * 7    2  5   1
 * Return:
 *
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 */
//还有一个要注意的点就是deep copy，dfs helper function里面带着一个list容器添加每一层的答案的时候，加进list list里面要new deep copy 见46行
public class PathSum_II {
  //自己的code：虽然有重复的code, 但是结构比较傻瓜易懂，尤其注意backtrack的时候如果return才挪，会不会挪不掉
  public List<List<Integer>> pathSum(TreeNode root, int sum) {
    List<List<Integer>> res = new ArrayList<>();
    dfs(root, sum, res, new ArrayList<>());
    return res;
  }

  private void dfs(TreeNode node, int sum, List<List<Integer>> res, List<Integer> curr) {
    //corner case
    if(node == null) return;
    //settlement
    if(node.left == null && node.right == null && node.val == sum) {
      curr.add(node.val);
      res.add(new ArrayList<Integer>(curr)); //deep copy, new object, not just reference
      curr.remove(curr.size() - 1);
      return;
    }
    //add node value
    curr.add(node.val);
    //DFS
    dfs(node.left, sum - node.val, res, curr);
    dfs(node.right, sum - node.val, res, curr);
    //backtrack
    curr.remove(curr.size() - 1);
  }

  //网上网友代码：灵活，在一开始加点的value,而不是一定要在最顶上结算，body部分进行加点
  public List<List<Integer>> pathSum2(TreeNode root, int sum) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    pathSum(root, sum, new ArrayList<Integer>(), res);
    return res;
  }

  void pathSum(TreeNode root, int sum, List<Integer> sol, List<List<Integer>> res) {
    if (root == null) {
      return;
    }

    sol.add(root.val); //在此处加点这样可以避免重复code

    if (root.left == null && root.right == null && sum == root.val) {
      res.add(new ArrayList<Integer>(sol));
    } else {
      pathSum(root.left, sum - root.val, sol, res);
      pathSum(root.right, sum - root.val, sol, res);
    }

    sol.remove(sol.size() - 1);
  }
}
