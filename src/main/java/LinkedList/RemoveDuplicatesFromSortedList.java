package LinkedList;

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
