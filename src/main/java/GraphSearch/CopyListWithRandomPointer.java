package GraphSearch;

import java.util.HashMap;
import java.util.Map;

/**
 * 题目：
 * 138. Copy List with Random Pointer
 *
 * A linked list is given such that each node contains an additional
 * random pointer which could point to any node in the list or null.
 *
 * Return a deep copy of the list.
 *
 * The Linked List is represented in the input/output as a list of n nodes.
 * Each node is represented as a pair of [val, random_index] where:
 *
 * val: an integer representing Node.val
 * random_index: the index of the node (range from 0 to n-1)
 * where random pointer points to, or null if it does not point to any node.
 */

/**
 * 笔记：
 * 这个解法虽然是for loop两遍，但是十分巧妙，第一次是把node的值复制一遍
 * 因为next和random指针所指向的点都已经在map里面了，而且key就是原始的node是包含指针的
 * 所以map.get(node).next = map.get(node).next;
 *     map.get(node).random = map.get(node.random);
 *     这样再for loop一遍就可以把map中的value新造的点的指针都加上了！！！
 */
public class CopyListWithRandomPointer {
  public Node1 copyRandomList(Node1 head) {
    Map<Node1, Node1> map = new HashMap<>();
    if(head == null) return null;
    //1st time for loop, put all nodes
    Node1 node = head;
    while(node != null) {
      map.put(node, new Node1(node.val));
      node = node.next;
    }

    //2nd for loop, put all next and random
    node = head;
    while(node != null) {
      map.get(node).next = map.get(node).next;
      map.get(node).random = map.get(node.random);
      node = node.next;
    }

    //return
    return map.get(head);
  }
}
