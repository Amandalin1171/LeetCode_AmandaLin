package PreSum;

import BinaryTree.TreeNode;
import java.util.HashMap;
import java.util.Map;

/**
 * 437. Path Sum III
 * You are given a binary tree in which each node contains an integer value.
 *
 * Find the number of paths that sum to a given value.
 *
 * The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).
 *
 * The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
 *
 * Example:
 *
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 *
 *       10
 *      /  \
 *     5   -3
 *    / \    \
 *   3   2   11
 *  / \   \
 * 3  -2   1
 *
 * Return 3. The paths that sum to 8 are:
 *
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3. -3 -> 11
 */
//PreSum + dfs + backtrack
public class PathSum_III {
  int count = 0;
  public int pathSum(TreeNode root, int sum) {
    Map<Integer, Integer> preSum = new HashMap<>();
    preSum.put(0, 1);
    dfs(root, 0, sum, preSum);
    return count;
  }

  private void dfs(TreeNode node, int currSum, int sum, Map<Integer, Integer> preSum) {
    if(node == null) return;
    currSum += node.val;
    if(preSum.containsKey(currSum - sum)) count += preSum.get(currSum - sum);
    if(preSum.containsKey(currSum)) preSum.put(currSum, preSum.get(currSum) + 1);
    else if(!preSum.containsKey(currSum)) preSum.put(currSum, 1);
    dfs(node.left, currSum, sum, preSum);
    dfs(node.right, currSum, sum, preSum);
    preSum.put(currSum, preSum.get(currSum) - 1);
  }
}
