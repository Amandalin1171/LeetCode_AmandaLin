package DFS;

import BinaryTree.TreeNode;

/**
 * 988. Smallest String Starting From Leaf
 *
 * Given the root of a binary tree, each node has a value from 0 to 25 representing the letters 'a' to 'z':
 * a value of 0 represents 'a', a value of 1 represents 'b', and so on.
 *
 * Find the lexicographically smallest string that starts at a leaf of this tree and ends at the root.
 *
 * (As a reminder, any shorter prefix of a string is lexicographically smaller: for example,
 * "ab" is lexicographically smaller than "aba".  A leaf of a node is a node that has no children.)
 */
public class SmallestStringStartingFromLeaf {

  /**
   * 方法1， 自己写的方法，就是暴力找路径然后满足到leaf就reverse，维持一个全局变量去比较存储最小的String
   */
  String min = "æ";
  public String smallestFromLeaf(TreeNode root) {
    //最开始想到的暴力解：打印tree path 从root到leaf，然后翻转string比较得出最小的
    dfs(root, new StringBuilder());
    return min;

  }
  //dfs helper function to find all tree paths
  private void dfs(TreeNode node, StringBuilder sb) {
    int l = sb.length();
    if(node == null) return;
    if(node.left == null && node.right == null) {
      sb.append((char)('a' + node.val));
      String curr = reverseString(sb.toString());
      if(min.equals("æ") || curr.compareTo(min) < 0) {
        min = curr;
        //System.out.println(min);
      }
      sb.setLength(l);
      return;
    }
    sb.append((char)('a' + node.val));
    dfs(node.left, sb);
    dfs(node.right, sb);
    sb.setLength(l);
  }
  //helper function to help reverse String (root -> leaf) to (leaf -> root)
  private String reverseString(String s) {
    char[] sChar = s.toCharArray();
    int left = 0;
    int right = s.length() - 1;
    while(left < right) {
      char temp = sChar[left];
      sChar[left] = sChar[right];
      sChar[right] = temp;
      left++;
      right--;
    }
    return new String(sChar);
  }

  /**
   * 方法2： surffix方法，每次衔接的时候去判断左右子树哪一个更小
   */
  String res = "%";
  public String smallestFromLeaf2(TreeNode root) {
    dfs(root, "");
    return res;
  }

  private void dfs(TreeNode node, String suffix) {
    if(node == null) return;
    if(node.left == null && node.right == null) {
      suffix = (char)('a' + node.val) + suffix;
      if(res.equals("%") || suffix.compareTo(res) < 0) {
        res = suffix;
      }
    }
    dfs(node.left, (char)('a' + node.val) + suffix);
    dfs(node.right, (char)('a' + node.val) + suffix);
  }
}
