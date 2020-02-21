package LinkedList;

import java.util.List;

/**
 * 需要注意的点有：
 * 1. 返回的是new head也就是prev这个点，因为while循环的条件是curr!= null，所以
 * 最后退出的时候是curr= temp是null就不进入循环了，所以此时prev是第一个有地址的node,也就是new head
 * 2. 攒点的code写法是有规律的：temp = curr.next; curr.next = prev; prev = curr; curr = temp;
 * 是一个闭环！！！
 * 3. 思想步骤：找个新的尾巴null, 让一个temp脑袋拎住后面的部分，此刻的curr点指向新的尾巴，然后被拎住
 * 的部分进行重复操作(更新prev和curr的位置）
 */
public class ReverseLinkedList {
  public ListNode reverseList(ListNode head) {
    if(head == null || head.next == null) return head;
    ListNode prev = null;
    ListNode curr = head;
    while(curr != null) {
      ListNode temp = curr.next;
      curr.next = prev;
      prev = curr;
      curr = temp;
    }
    return prev;
  }

  public static void main(String[] args) {
    ListNode a = new ListNode(1);
    ListNode b = new ListNode(2);
    ListNode c = new ListNode(3);
    ListNode d = new ListNode(4);
    ListNode e = new ListNode(5);

    a.next = b;
    b.next = c;
    c.next = d;
    d.next = e;

    ReverseLinkedList testClass = new ReverseLinkedList();
    ListNode res = new ListNode(0);
    res.next = testClass.reverseList(a);
    while(res.next != null) {
      System.out.println(res.next.val);
      res = res.next;
    }
  }
}
