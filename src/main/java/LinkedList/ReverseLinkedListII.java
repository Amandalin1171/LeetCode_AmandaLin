package LinkedList;

/**
 * 题目：
 * 92. Reverse Linked List II
 *
 * Reverse a linked list from position m to n. Do it in one-pass.
 *
 * Note: 1 ≤ m ≤ n ≤ length of list.
 *
 * Example:
 *
 * Input: 1->2->3->4->5->NULL, m = 2, n = 4
 * Output: 1->4->3->2->5->NULL
 */
public class ReverseLinkedListII {

  /**
   * 自己的想法很愚蠢，理想很丰满，现实很骨感，灵魂拷问：翻转后，新的尾巴是哪个点^^
   * Step1: 记录m-1和n+1的位置为衔接点，找到m和n
   * Step2: 对m和n这个区间进行翻转
   * Step3: 衔接
   * 这个方法的改良版如下：
   * 【DuangDuangDuang】官方方法，真实可靠^^果然更香
   * 关键点：最后衔接的时候：m和postn链接； prem和n链接
   * 终态思维：最终翻转完成后：
   * 移动后的最终的m.next指向n本来后面那个点（postn)，m本来之前的那个点（prem).next指向移动后最终的n
   */
  public ListNode reverseBetween2(ListNode head, int m, int n) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    head = dummy;

    //for loop少走一步，找到prem和m这两个点
    for (int i = 1; i < m; i++) {
      if (head == null)
        return null;
      head = head.next;
    }

    ListNode premNode = head;
    ListNode mNode = head.next;
    ListNode nNode = mNode;
    ListNode postnNode = mNode.next;

    for (int i = m; i < n; i++) {
      if (postnNode == null)
        return null;
      ListNode temp = postnNode.next;
      postnNode.next = nNode;
      nNode = postnNode;
      postnNode = temp;
    }
    //说的就是这里的衔接最重要
    //终态思维，一开始就要想到最后需要这两个步骤
    mNode.next = postnNode;
    premNode.next = nNode;

    return dummy.next;
  }

  public static void main(String[] args) {
    ReverseLinkedListII testClass = new ReverseLinkedListII();
    ListNode a = new ListNode(1);
    ListNode b = new ListNode(2);
    ListNode c = new ListNode(3);
    ListNode d = new ListNode(4);
    ListNode e = new ListNode(5);

    a.next = b;
    b.next = c;
    c.next = d;
    d.next = e;

    ListNode res = testClass.reverseBetween2(a, 2, 4);

    while(res != null) {
      System.out.println(res.val);
      res = res.next;
    }
  }
}
