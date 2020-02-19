import java.util.List;

public class AddTwoNumbers {

  public static void main(String[] args) {
    ListNode root = new ListNode(1);
    root.next = new ListNode(4);
    root.next.next = new ListNode(3);
    root.next.next.next = new ListNode(2);
    root.next.next.next.next = new ListNode(5);
    root.next.next.next.next.next = new ListNode(2);
    partition(root, 3);
  }

  public static ListNode partition(ListNode head, int x) {

    ListNode first = new ListNode(Integer.MIN_VALUE); //store nodes with value less than x
    ListNode firstDummy = first;
    ListNode dummy = new ListNode(Integer.MIN_VALUE); //store nodes with value greater than or equal to x
    dummy.next = head;
    ListNode prev = dummy;

    while(head != null) {
      ListNode next = head.next;
      if(head.val >= x) {
        first.next = head;
        deleteNode(prev, head);
        first = first.next;
      } else {
        prev = head;
      }
      head = next;
    }
    prev.next = firstDummy.next;

    return dummy.next;
  }

  private static void deleteNode(ListNode prev, ListNode node) {
    prev.next = node.next;
    node.next = null;

    // node.next = node.next.next;
  }
}

class ListNode {
  int val;
  ListNode next;

  public ListNode(int val) {
    this.val = val;
  }
}
