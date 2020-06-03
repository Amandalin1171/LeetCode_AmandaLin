package ArrayAndNumbers;

import LinkedList.ListNode;
import java.util.ArrayList;
import java.util.List;

/**
 * 445. Add Two Numbers II
 * You are given two non-empty linked lists representing two non-negative integers.
 * The most significant digit comes first and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Follow up:
 * What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
 *
 * Example:
 * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 8 -> 0 -> 7
 */
public class AddTwoNumbers_II {

  /**
   * 自己的代码：
   * BUG 1: 重复code,而且进入循环是m >= 0不是 > 0
   * BUG 2: 41行是sum.add(curr); 不是sum.add(curr + carry);
   * 把一个arraylist打印成string: System.out.println(Arrays.toString(sum.toArray()));
   */
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    //1. store 2 numbers in arraylists
    List<Integer> num1 = numberWriter(l1);
    List<Integer> num2 = numberWriter(l2);
    int m = num1.size() - 1;
    int n = num2.size() - 1;
    //2. add 2 numbers and get sum in reverse order arraylist format
    List<Integer> sum = new ArrayList<>();
    int carry = 0;
    while(m >= 0 && n >= 0) {
      int curr = (num1.get(m) + num2.get(n) + carry) % 10;
      //System.out.println("current is " + curr);
      sum.add(curr);
      carry = (num1.get(m) + num2.get(n) + carry) / 10;
      //System.out.println("carry is " + carry);
      m--;
      n--;
    }
    if(m >= 0) {
      while(m >= 0) {
        int curr = (num1.get(m) + carry) % 10;
        sum.add(curr);
        carry = (num1.get(m) + carry) / 10;
        m--;
      }
    }
    if(n >= 0) {
      while(n >= 0) {
        int curr = (num2.get(n) + carry) % 10;
        sum.add(curr);
        carry = (num2.get(n) + carry) / 10;
        n--;
      }
    }
    if(carry > 0) sum.add(carry);
    //把一个arraylist打印成string: System.out.println(Arrays.toString(sum.toArray()));

    //3. convert sum to linkedlist format
    ListNode dummy = new ListNode(0);
    ListNode root = new ListNode(sum.get(sum.size() - 1));
    dummy.next = root;
    for(int i = sum.size() - 2; i >= 0; i--) {
      ListNode node = new ListNode(sum.get(i));
      root.next = node;
      root = node;
    }
    return dummy.next;
  }

  private List<Integer> numberWriter(ListNode l) {
    List<Integer> num = new ArrayList<>();
    while(l != null) {
      num.add(l.val);
      l = l.next;
    }
    return num;
  }

  /**
   * 老公的代码：
   */
  public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
    //1. store 2 numbers in arraylists
    List<Integer> num1 = numberWriter(l1);
    List<Integer> num2 = numberWriter(l2);
    int m = num1.size() - 1;
    int n = num2.size() - 1;
    //2. add 2 numbers and get sum in reverse order arraylist format
    // List<Integer> sum = new ArrayList<>();
    ListNode list = new ListNode(0);
    int carry = 0;
    while(m >= 0 || n >= 0) {
      // carry 1
      if(m >= 0) carry += num1.get(m);
      if(n >= 0) carry += num2.get(n);
      // carry = 12
      list.val = carry % 10;  //list.val = 2
      // 37
      // 81
      //
      ListNode head = new ListNode(carry / 10);
      head.next = list;  // 0/1? -> 1 -> 8
      list = head;
      carry /= 10;

      m--;
      n--;
    }

    return list.val == 0 ? list.next : list;
  }
}
