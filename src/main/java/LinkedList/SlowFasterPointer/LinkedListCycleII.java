package LinkedList.SlowFasterPointer;

/**
 * 题目：
 * 142. Linked List Cycle II
 *
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 *
 * To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.
 *
 * Note: Do not modify the linked list.
 *
 * Follow-up: can you count the cycle size?
 */

import LinkedList.ListNode;

/**
 * 笔记：
 * 这道题基本上就是记住套路： Floyd–Warshall algorithm
 * 快慢指针相遇的同时，起点再次出发一个慢指针，与之前的慢指针一定会再次在环的开口处相遇。
 * 这就是所谓的有缘人一定会再次相遇叭<23333
 * 最神奇的是这个思想可以运用到另一道题：Find the Duplicate Number
 * 去看看叭~~~
 */
public class LinkedListCycleII {
  public ListNode deleteCycle(ListNode head) {
    if(head == null || head.next == null) return null;
    ListNode runner = head;
    ListNode walker = head;
    while(runner != null && runner.next != null) {
      walker = walker.next;
      runner = runner.next.next;
      if(walker == runner) {
        ListNode walker2 = head;
        while(walker != walker2) {
          walker2 = walker2.next;
          walker = walker.next;
        }
        return walker;
      }
    }
    return null;
  }

  public static void main(String[] args) {
    LinkedListCycleII testClass = new LinkedListCycleII();
    ListNode a = new ListNode(3);
    ListNode b = new ListNode(2);
    ListNode c = new ListNode(0);
    ListNode d = new ListNode(-4);

    a.next = b;
    b.next = c;
    c.next = d;
    d.next = b;

    ListNode res = testClass.deleteCycle(a);
    System.out.println(res.val);
  }
}
