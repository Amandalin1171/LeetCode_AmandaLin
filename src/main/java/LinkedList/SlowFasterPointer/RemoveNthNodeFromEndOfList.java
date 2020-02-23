package LinkedList.SlowFasterPointer;

/**
 * 题目：
 * 19. Remove Nth Node From End of List
 *
 * Given a linked list, remove the n-th node from the end of list and return its head.
 *
 * Example:
 *
 * Given linked list: 1->2->3->4->5, and n = 2.
 *
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 */

import LinkedList.ListNode;

/**
 * 笔记：
 * 这题就是个校园爱情呐呐呐！
 * 跑步很快的男神对可爱的学妹说： 我事先让你几步，你站我前面开始跑，然后...在一起！！！你懂得
 * 就是让第一个指针先跑出去n的位置，然后第二个指针出发，俩一起跑
 * 等到第一个指针到终点的时候，后出发的指针就是要被删除的地方。
 * 注意的地方是：第二次跑的时候让指针停在要被删掉的点的前面比较好链接，看code
 */
public class RemoveNthNodeFromEndOfList {
  public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode dummy = new ListNode(0);
    ListNode boy = dummy;
    ListNode girl = dummy;
    dummy.next = head;

    for(int i = 1; i <= n; i++) {
      girl = girl.next;
    }

    while(girl.next != null) {
      boy = boy.next;
      girl = girl.next;
    }

    //退出的时候boy站在的点的后面的点就是我们要删掉的点
    boy.next = boy.next.next;
    return dummy.next;
  }

  public static void main(String[] args) {
    RemoveNthNodeFromEndOfList testClass = new RemoveNthNodeFromEndOfList();

    ListNode a = new ListNode(1);
    ListNode b = new ListNode(2);
    ListNode c = new ListNode(3);
    ListNode d = new ListNode(4);
    ListNode e = new ListNode(5);

    a.next = b;
    b.next = c;
    c.next = d;
    d.next = e;

    ListNode res = testClass.removeNthFromEnd(a, 2);
    while(res != null) {
      System.out.println(res.val);
      res = res.next;
    }
  }

}
