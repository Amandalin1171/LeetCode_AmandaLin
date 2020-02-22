package LinkedList;

/**
 * 题目：
 * 86. Partition List
 *
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 *
 * You should preserve the original relative order of the nodes in each of the two partitions.
 *
 * Example:
 *
 * Input: head = 1->4->3->2->5->2, x = 3
 * Output: 1->2->2->4->3->5
 */

/**
 * 笔记：
 * 注意点：1. 一定要复制点，不是直接连接head吖！！会乱七八糟全是环的！！！
 * 2. 最后连接的时候又多余一步让dummyL.next = null；说过啦这个dummy永远不会被访问到了，所以直接被回收了
 * 思路就是建立两个表，一个比x小，一个比x，这样不破坏原有的顺序，再连接起来！
 */
public class PartitionList {
  public ListNode partition(ListNode head, int x) {
    ListNode dummyS = new ListNode(0); //存储比x小的点，建一条新的链表
    ListNode currS = dummyS;

    ListNode dummyL = new ListNode(0); //存储比x大的点，建一条新的链表
    ListNode currL = dummyL;

    while(head != null) {
      if(head.val < x) {
        //一定要生成新的点赋予head的值，不能直接连head，两边都
        //瞎连以head开始的原表的话会到处是环，欢乐奥运五环吗？？？
        ListNode tempS = new ListNode(head.val);
        currS.next = tempS;
        currS = currS.next;
      } else {
        ListNode tempL = new ListNode(head.val);
        currL.next = tempL;
        currL = currL.next;
      }
      head = head.next;
    }

    //connect together
    currS.next = dummyL.next;
    //dummyL.next = null; 又犯同样的错误了四不四！！这步没必要！！
    return dummyS.next;
  }

  public static void main(String[] args) {
    PartitionList testClass = new PartitionList();
    ListNode a = new ListNode(1);
    ListNode b = new ListNode(4);
    ListNode c = new ListNode(3);
    ListNode d = new ListNode(2);
    ListNode e = new ListNode(5);
    ListNode f = new ListNode(2);

    a.next = b;
    b.next = c;
    c.next = d;
    d.next = e;
    e.next = f;

    ListNode res = testClass.partition(a, 3);
    while(res != null) {
      System.out.println(res.val);
      res = res.next;
    }
  }
}
