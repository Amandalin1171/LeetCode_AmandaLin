package BinaryTree.Easy_Problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 589. N-ary Tree Preorder Traversal
 * Given an n-ary tree, return the preorder traversal of its nodes' values.
 * Nary-Tree input serialization is represented in their level order traversal,
 * each group of children is separated by the null value (See examples).
 */
public class N_aryTreePreorderTraversal {
  //方法1：iterate:
  public List<Integer> preorder(Node root) {
    Stack<Node> stack = new Stack<>();
    List<Integer> list = new ArrayList<>();

    //corner case
    if(root == null) return list;
    stack.push(root);
    while(!stack.isEmpty()) {
      Node curr = stack.pop();
      list.add(curr.val);
      if(curr.children != null) {
        //从后往前加
        for(int i = curr.children.size() - 1; i >= 0; i--) {
          stack.push(curr.children.get(i));
        }
      }
    }
    return list;
  }

  //方法2：recursion
  public List<Integer> preorder2(Node root) {
    List<Integer> list = new ArrayList<>();
    dfs(root, list);
    return list;
  }

  private void dfs(Node node, List<Integer> list) {
    if(node == null) return;
    list.add(node.val);
    for(Node child : node.children) {
      dfs(child, list);
    }
  }
}
