package BinaryTree.ConstructBinaryTree;

import BinaryTree.TreeNode;
import java.util.HashMap;
import java.util.Map;

/**
 * 105. Construct Binary Tree from Preorder and Inorder Traversal
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 *
 * Note:
 * You may assume that duplicates do not exist in the tree.
 *
 * For example, given
 *
 * preorder = [3,9,20,15,7]
 * inorder = [9,3,15,20,7]
 * Return the following binary tree:
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */

/**
 * 笔记：
 * 是Binary Tree不是BST， BST 只需要一个preOrder就可以construct了。
 * 不信见： 1008. Construct Binary Search Tree from Preorder Traversal
 *
 * 用俩traversal来build binary tree有三个题，排列组合嘛：ღ( ´･ᴗ･` )
 * 第一题：pre + in
 * pre & in 的逻辑相对简单：
 * pre[0]就会根节点，然后到in里面去找到这个点，in在这个点左边的就是left subtree，右边就是right subtree
 * Say we have 2 arrays, PRE and IN.
 * Preorder traversing implies that PRE[0] is the root node.
 * Then we can find this PRE[0] in IN, say it's IN[5].
 * Now we know that IN[5] is root, so we know that IN[0] - IN[4] is on the left side, IN[6] to the end is on the right side.
 * Recursively doing this on subarrays, we can build a tree out of it :)
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
  public TreeNode buildTree(int[] preorder, int[] inorder) {
    if(preorder == null || inorder == null) return null;

    //map: key: node.val of inorder, value: index of this node
    Map<Integer, Integer> nodeValToIndex = new HashMap<>();
    for(int i = 0; i < inorder.length; i++) {
      nodeValToIndex.put(inorder[i], i);
    }

    return buildHelper(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1, nodeValToIndex);
  }

  private TreeNode buildHelper(int[] preorder, int[] inorder, int p1, int p2, int i1, int i2, Map<Integer, Integer> nodeValToIndex) {
    if(p1 > p2) return null;
    TreeNode root = new TreeNode(preorder[p1]);
    if(p1 == p2) return root;

    int idx = nodeValToIndex.get(preorder[p1]); // index of root in inorder array
    int leftLength = idx - i1;
    int rightLength = i2 - idx;

    root.left = buildHelper(preorder, inorder, p1 + 1, p1 + leftLength, i1, idx - 1, nodeValToIndex);
    root.right = buildHelper(preorder, inorder, p1 + leftLength + 1, p2, idx + 1, i2, nodeValToIndex);

    return root;
  }
}
