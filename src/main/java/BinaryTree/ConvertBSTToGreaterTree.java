package BinaryTree;

/**
 * 538. Convert BST to Greater Tree
 * Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.
 *
 * Example:
 *
 * Input: The root of a Binary Search Tree like this:
 *               5
 *             /   \
 *            2     13
 *
 * Output: The root of a Greater Tree like this:
 *              18
 *             /   \
 *           20     13
 * Note: This question is the same as 1038: https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/
 *
 */

/**
 * 方法1： global变量设计recursion
 * 1.dfs右子树
 * 2. 累加tree node的value并赋予给遍历到的此刻的node
 * 3. dfs左子树
 *
 * 方法2： preSum
 */
public class ConvertBSTToGreaterTree {
  private int value; //using global variable
  public TreeNode convertBST(TreeNode root) {
    //corner case
    if(root == null) return root;

    dfs(root);
    return root;
  }

  private int dfs(TreeNode node) {
    if(node.right != null) dfs(node.right); //add right subTree

    //accumulate sum up all value
    value += node.val;
    node.val = value;

    if(node.left != null) dfs(node.left); //add left subTree
    return node.val;
  }

  //方法2： preSum, reverse inorder traversal
  //https://leetcode.com/problems/convert-bst-to-greater-tree/discuss/100619/Java-6-lines
  public TreeNode convertBST2(TreeNode root) {
    if(root == null) return null;
    DFS(root, 0);
    return root;
  }

  public int DFS(TreeNode root, int preSum){
    if(root.right != null) preSum = DFS(root.right, preSum);
    root.val = root.val + preSum;
    return (root.left != null) ? DFS(root.left, root.val) : root.val;
  }
}
