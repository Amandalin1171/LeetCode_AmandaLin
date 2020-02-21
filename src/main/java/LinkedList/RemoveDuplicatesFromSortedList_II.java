package LinkedList;
/**
 * 题目：
 * 82. Remove Duplicates from Sorted List II
 *
 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
 *
 * Example 1:
 *
 * Input: 1->2->3->3->4->4->5
 * Output: 1->2->5
 * Example 2:
 *
 * Input: 1->1->1->2->3
 * Output: 2->3
 */

/**
 * 笔记：
 * 最关键的一个步骤就是生成dummy head之后把head挪到dummy这个位置来，然后找重复操作，
 * 这样的话可以保证把包括原始head在内的所有出现重复的全部删掉
 */
public class RemoveDuplicatesFromSortedList_II {
  public ListNode deleteDuplicatesII(ListNode head) {
    if(head == null || head.next == null) return head;
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    head = dummy; //说的就是这步最关键
    while(head.next != null && head.next.next != null) {
      if(head.next.val == head.next.next.val) {
        int val = head.next.val;
        while(head.next != null && head.next.val == val) {
          head.next = head.next.next;
        }
      } else {
        head = head.next;
      }
    }
    return dummy.next;
  }

  public static void main(String[] args) {
    ListNode a = new ListNode(1);
    ListNode b = new ListNode(1);
    ListNode c = new ListNode(1);
    ListNode d = new ListNode(2);
    ListNode e = new ListNode(3);
    a.next = b;
    b.next = c;
    c.next = d;
    d.next = e;
    RemoveDuplicatesFromSortedList_II testClass = new RemoveDuplicatesFromSortedList_II();
    ListNode res = testClass.deleteDuplicatesII(a);
    while(res != null) {
      System.out.println(res.val);
      res = res.next;
    }
  }

}
