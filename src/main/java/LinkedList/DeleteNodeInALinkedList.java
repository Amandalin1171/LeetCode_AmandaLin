package LinkedList;

/**
 * 只给要删掉的那个点的地址，怎么删掉？
 * 1. 把这个点的值改为它后面那个点的值
 * 2. 把它后面那个点删掉（替死鬼，可怜）
 */
public class DeleteNodeInALinkedList {
  public void deleteNode(ListNode node) {
    node.val = node.next.val;
    node.next = node.next.next;
  }

  public static void main(String[] args) {
    ListNode a = new ListNode(4);
    ListNode b = new ListNode(5);
    ListNode c = new ListNode(1);
    ListNode d = new ListNode(9);
    a.next = b;
    b.next = c;
    c.next = d;
    DeleteNodeInALinkedList testClass = new DeleteNodeInALinkedList();
    ListNode res = new ListNode(0); //此时的res相当于一个dummy node
    res.next = a;
    testClass.deleteNode(b);
    while(res.next != null) {
      System.out.println(res.next.val);
      res = res.next;
    }
  }
}
