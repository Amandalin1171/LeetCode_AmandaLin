package BinaryTree.Easy_Problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * 590. N-ary Tree Postorder Traversal
 * Given an n-ary tree, return the postorder traversal of its nodes' values.
 *
 * Nary-Tree input serialization is represented in their level order traversal,
 * each group of children is separated by the null value (See examples).
 */

/**
 * 正常put的顺序往stack里面放，一层一层拿出来得到的是自己，右子树，左子树
 * 反过来不就是 左子树，右子树，自己 postorder
 */
public class N_aryTreePostorderTraversal {
  public List<Integer> postorder(Node root) {
    List<Integer> list = new ArrayList<>();
    Stack<Node> stack = new Stack<>();
    //corner case
    if(root == null) return list;
    stack.push(root);
    while(!stack.isEmpty()) {
      Node curr = stack.pop();
      list.add(curr.val);
      if(curr.children != null) {
        for(Node child : curr.children) {
          stack.push(child);
        }
      }
    }
    Collections.reverse(list);
    return list;
  }
}
