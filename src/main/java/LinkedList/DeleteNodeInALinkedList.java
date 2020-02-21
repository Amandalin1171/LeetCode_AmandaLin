package LinkedList;
/**
 * 题目：
 * 237. Delete Node in a Linked List
 *
 * Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.
 * Example 1:
 *
 * Input: head = [4,5,1,9], node = 5
 * Output: [4,1,9]
 * Explanation: You are given the second node with value 5, the linked list should become 4 -> 1 -> 9 after calling your function.
 *
 * The linked list will have at least two elements.
 * All of the nodes' values will be unique.
 * The given node will not be the tail and it will always be a valid node of the linked list.
 * Do not return anything from your function.
 */

/**
 * 笔记：
 * 只给要删掉的那个点的地址，怎么删掉？
 * 1. 把这个点的值改为它后面那个点的值
 * 2. 把它后面那个点删掉（替死鬼，可怜）
 */
public class DeleteNodeInALinkedList {
  public void deleteNode(ListNode node) {
    /**
     * 在LC237中明确指出：The given node will not be the tail
     * 所以不需要写corner case： node.next != null
     * 不然的话，如果指向null,会throw null pointer exception
     */
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
