package LinkedList;

/**
 * 题目：
 * 61. Rotate List
 *
 * Given a linked list, rotate the list to the right by k places, where k is non-negative.
 *
 * Example 1:
 *
 * Input: 1->2->3->4->5->NULL, k = 2
 * Output: 4->5->1->2->3->NULL
 * Explanation:
 * rotate 1 steps to the right: 5->1->2->3->4->NULL
 * rotate 2 steps to the right: 4->5->1->2->3->NULL
 * Example 2:
 *
 * Input: 0->1->2->NULL, k = 4
 * Output: 2->0->1->NULL
 * Explanation:
 * rotate 1 steps to the right: 2->0->1->NULL
 * rotate 2 steps to the right: 1->2->0->NULL
 * rotate 3 steps to the right: 0->1->2->NULL
 * rotate 4 steps to the right: 2->0->1->NULL
 */

/**
 * 笔记：
 * 这里面是有一个数学逻辑的：就是有可能扣圈
 * 如果linkedlist的长度是l，那么我们要做的就是把“ l - k % l ”位置开始的后半截挪到前面来
 * 【注意】这题标题的是rotateRight, 所以rotate到底是个啥操作每次要跟面试官爷确认一哈
 */
public class RotateList {
  public ListNode rotateRight(ListNode head, int k) {
    if(head == null || head.next == null) return head;
    ListNode dummy = new ListNode(0);
    dummy.next = head;

    int l; // length of the given list
    ListNode tail = dummy; // find length(tail) of the given list
    ListNode rotationStartingPoint = dummy; // find the rotation starting point

    for(l = 0; tail.next != null; l++) {
      tail = tail.next;
    }

    for(int i = l - k % l; i > 0; i--) {
      rotationStartingPoint = rotationStartingPoint.next;
    }

    //do rotation
    tail.next = dummy.next;
    dummy.next = rotationStartingPoint.next;
    rotationStartingPoint.next = null;

    return dummy.next;
  }

  public static void main(String[] args) {
    RotateList newClass = new RotateList();
    ListNode a = new ListNode(1);
    ListNode b = new ListNode(2);
    ListNode c = new ListNode(3);
    ListNode d = new ListNode(4);
    ListNode e = new ListNode(5);

    a.next = b;
    b.next = c;
    c.next = d;
    d.next = e;

    ListNode res = newClass.rotateRight(a, 2);
    while(res != null) {
      System.out.println(res.val);
      res = res.next;
    }
  }
}
