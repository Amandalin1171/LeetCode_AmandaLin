package LinkedList;

/**
 * 题目：
 * 203. Remove Linked List Elements
 *
 * Remove all elements from a linked list of integers that have value val.
 *
 * Example:
 *
 * Input:  1->2->6->3->4->5->6, val = 6
 * Output: 1->2->3->4->5
 */

/**
 * 笔记：下面第一部分是最初的魔幻想法，利用deletenode那道题写个helper function去做
 * BUT！这道题不能利用那个迷幻的只给要删掉的node地址删点的方法来延伸，那个题可是有很多附加条件的
 * 比如这种情况下就一个点，6， 你要删掉6，那你返回啥，null不能作为helper返回值，
 * 你说返回void，那你为了连接需要知道前面和后面的点，这个helper就形同虚设，
 * 还不如for loop然后记录prev, curr和next就完事了，见第二种方法！！！
 * 切记切记切记！！！不要惯性思维不要投机取巧不要耍小聪明！！！
 * 先给Brute Forth!!!! --#
 */
//这是错误方法！！！
public class RemoveLinkedListElements {
  public ListNode removeElementsWrong(ListNode head, int val) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    while(head != null) {
      if(head.val == val) {
        head = removeNode(head);
      } else {
        head = head.next;
      }
    }
    return dummy.next;
  }

  private ListNode removeNode(ListNode node) {
    while(node.next != null) {
      node.val = node.next.val;
      node.next = node.next.next;
    }
    return node;
  }

  //这是正确方法，记录之前的点，此刻的点即可
  public ListNode removeElementsRight(ListNode head, int val) {
    ListNode dummy = new ListNode(0);
    ListNode prev = dummy;
    ListNode curr = head;
    dummy.next = head;

    while(curr != null) {
      if(curr.val == val) {
        prev.next = curr.next;
        //curr.next = null; 再次警告自己一次，这一步骤多余！！！curr已经不会被访问到，回收了拜拜了您嘞！
        curr = prev.next;
      } else {
        curr = curr.next;
        prev = prev.next;
      }
    }
    return dummy.next;
  }

  public static void main(String[] args) {
    RemoveLinkedListElements testClass = new RemoveLinkedListElements();
    ListNode a = new ListNode(1);
    ListNode b = new ListNode(2);
    ListNode c = new ListNode(6);
    ListNode d = new ListNode(3);
    ListNode e = new ListNode(4);
    ListNode f = new ListNode(5);
    ListNode g = new ListNode(6);

    a.next = b;
    b.next = c;
    c.next = d;
    d.next = e;
    e.next = f;
    f.next = g;

    ListNode res = testClass.removeElementsRight(a, 6);
    while(res != null) {
      System.out.println(res.val);
      res = res.next;
    }
  }
}
