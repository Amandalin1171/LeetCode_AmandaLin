package BinaryTree.Easy_Problems;

import BinaryTree.TreeNode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 653. Two Sum IV - Input is a BST
 * Given a Binary Search Tree and a target number,
 * return true if there exist two elements in the BST such that their sum is equal to the given target.
 *
 * Example 1:
 * Input:
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 *
 * Target = 9
 *
 * Output: True
 */
public class TwoSumIV_InputIsABST {
  //方法1： using set
  //没有利用到BST的特征，就是暴力遍历一遍找
  public boolean findTarget(TreeNode root, int k) {
    Set<Integer> set = new HashSet<>();
    return dfs(root, k, set);
  }

  private boolean dfs(TreeNode node, int k, Set<Integer> set) {
    if(node == null) return false;
    if(set.contains(k - node.val)) return true;
    set.add(node.val);
    return dfs(node.left, k, set) || dfs(node.right, k, set);
  }

  //方法2： inorder遍历得到sorted array,然后2 pointers 找答案
  //虽然利用了BST的特征，但是时间复杂度是一样的
  public boolean findTarget2(TreeNode root, int k) {
    List<Integer> list = new ArrayList<>();
    //get sorter arraylist
    inorderWriter(root, list);
    //2 pointers
    int l = 0;
    int r = list.size() - 1;
    while(l < r) {
      if(list.get(l) + list.get(r) == k) return true;
      if(list.get(l) + list.get(r) < k) l++;
      else if(list.get(l) + list.get(r) > k) r--;
    }
    return false;
  }

  private void inorderWriter(TreeNode node, List<Integer> list) {
    if(node == null) return;
    inorderWriter(node.left, list);
    list.add(node.val);
    inorderWriter(node.right, list);
  }
}
