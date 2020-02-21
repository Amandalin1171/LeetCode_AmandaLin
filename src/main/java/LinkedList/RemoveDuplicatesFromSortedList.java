package LinkedList;
/**
 * 题目：
 * 83. Remove Duplicates from Sorted List
 *
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 *
 * Example 1:
 *
 * Input: 1->1->2
 * Output: 1->2
 * Example 2:
 *
 * Input: 1->1->2->3->3
 * Output: 1->2->3
 */

/**
 * 笔记：
 * 把一个点连接到它后面的后面的点，直接用cur.next = cur.next.next;就可以啦， 为啥呢？
 * 因为：这样的话，cur.next就没有任何node指向它了，这个点就永远无法被访问到，就会被回收了
 * 心疼一秒的同时，节约了我们很多时间。
 */
public class RemoveDuplicatesFromSortedList {
  public ListNode deleteDuplicates(ListNode head) {
    ListNode cur = head;
    while(cur != null && cur.next != null) {
      if(cur.val == cur.next.val) {
        cur.next = cur.next.next;
        //cur = cur.next; 这步完全没用
      } else {
        cur = cur.next;
      }
    }
    return head;
  }


  public static void main(String[] args) {
    ListNode a = new ListNode(1);
    ListNode b = new ListNode(1);
    ListNode c = new ListNode(2);
    a.next = b;
    b.next = c;
    RemoveDuplicatesFromSortedList testClass = new RemoveDuplicatesFromSortedList();
    ListNode res = testClass.deleteDuplicates(a);
    while(res != null) {
      System.out.println(res.val);
      res = res.next;
    }

  }
}
