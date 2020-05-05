package BinaryTree.ConstructBinaryTree;

/**
 * 1008. Construct Binary Search Tree from Preorder Traversal
 *
 * Return the root node of a binary search tree that matches the given preorder traversal.
 *
 * (Recall that a binary search tree is a binary tree where for every node,
 * any descendant of node.left has a value < node.val,
 * and any descendant of node.right has a value > node.val.
 * Also recall that a preorder traversal displays the value of the node first,
 * then traverses node.left, then traverses node.right.)
 *
 * It's guaranteed that for the given test cases there is always possible
 * to find a binary search tree with the given requirements.
 *
 * Constraints:
 *
 * 1 <= preorder.length <= 100
 * 1 <= preorder[i] <= 10^8
 * The values of preorder are distinct.
 *
 * Example 1:
 *
 * Input: [8,5,1,7,10,12]
 * Output: [8,5,10,1,7,null,12]
 */

import BinaryTree.TreeNode;

/**
 * 笔记：
 * 方法1： recursion O(n)
 * 由于是BST，所以recursion的helper function 里面需要传参 upper bound 和 lower bound 去限制node.val的范围
 * 方法2： iteration using Stack
 */
public class ConstructBinarySearchTreeFromPreorderTraversal {

}
