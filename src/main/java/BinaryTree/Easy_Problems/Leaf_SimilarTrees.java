package BinaryTree.Easy_Problems;

/**
 * 872. Leaf-Similar Trees
 * Consider all the leaves of a binary tree.  From left to right order, the values of those leaves form a leaf value sequence.
 * For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).
 *
 * Two binary trees are considered leaf-similar if their leaf value sequence is the same.
 *
 * Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.
 *
 * Constraints:
 * Both of the given trees will have between 1 and 200 nodes.
 * Both of the given trees will have values between 0 and 200
 */

import BinaryTree.TreeNode;
import java.util.ArrayList;
import java.util.List;

/**
 * 敲重点！！！！！！！！！
 * 一开始自己的想法是level order traversal是错误的
 * 因为这里面顺序很重要，但是比如：
 * [3,5,1,6,2,9,8,null,null,7,4]
 * [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
 * 这两个的leaf是同样的，但是由于处在的层不一样所以存储的时候的顺序不一样，以为存在set里就OK啦？
 * 那也不行啊，因为顺序很重要
 * [1, 2, 3]和[1, 3, 2]是不一样的leaf
 * 所以这题只能DFS
 * 附上错误代码：
 * public boolean leafSimilar(TreeNode root1, TreeNode root2) {
 *         //corner case
 *         if(root1 == null && root2 == null) return true;
 *         else if(root1 == null || root2 == null) return false;
 *
 *         Set<Integer> list1 = levelOrderTraversal(root1);
 *         Set<Integer> list2 = levelOrderTraversal(root2);
 *
 *         return list1.equals(list2);
 *     }
 *
 *     private Set<Integer> levelOrderTraversal(TreeNode node) {
 *         Queue<TreeNode> queue = new LinkedList<>();
 *         Set<Integer> res = new HashSet<>();
 *
 *         queue.offer(node);
 *
 *         while(!queue.isEmpty()) {
 *             int size = queue.size();
 *             for(int i = 0; i < size; i++) {
 *                 TreeNode curr = queue.poll();
 *                 if(curr.left == null && curr.right == null) res.add(curr.val);
 *                 if(curr.left != null) queue.offer(curr.left);
 *                 if(curr.right != null) queue.offer(curr.right);
 *             }
 *         }
 *
 *         return res;
 *     }
 *
 */
public class Leaf_SimilarTrees {
  public boolean leafSimilar(TreeNode root1, TreeNode root2) {
    List<Integer> leaves1 = new ArrayList<>();
    List<Integer> leaves2 = new ArrayList<>();
    dfs(root1, leaves1);
    dfs(root2, leaves2);
    return leaves1.equals(leaves2);
  }

  private void dfs(TreeNode node, List<Integer> leaves) {
    if(node == null) return;
    if(node.left == null && node.right == null) leaves.add(node.val);
    dfs(node.left, leaves);
    dfs(node.right, leaves);
  }
}
