package TwoPointers;

/**
 * 题目：215. Kth Largest Element in an Array
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * Example 1:
 *
 * Input: [3,2,1,5,6,4] and k = 2
 * Output: 5
 * Example 2:
 *
 * Input: [3,2,3,1,2,4,5,5,6] and k = 4
 * Output: 4
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 */

import java.util.PriorityQueue;

/**
 * 笔记：第一种解法是快排，
 * Find kith largest element is equivalent to find (n - k)th smallest element in array.
 * It is worth mentioning that (n - k) is the real index (start from 0) of an element.
 * 但是要注意快排的时间复杂度问题：
 * general situation when we shuffle array before doing quickSelect is O(n).
 * Worse case when the array is sorted in descending order is O(n^2)
 *
 * 第二种解法是用PQ O(nlogn)
 */
public class KthLargestElementInAnArray {
  public int findKthLargest(int[] nums, int k) {
    int left = 0;
    int right = nums.length - 1;
    int index = nums.length - k;
    while(left < right) {
      int pivot = partition(nums, left, right);
      if(pivot < index) {
        left = pivot + 1;
      }
      else if(pivot > index) {
        right = pivot - 1;
      }
      else return nums[pivot];
    }
    return nums[left];
  }

  private int partition(int[] nums, int l, int r) {
    if(l == r) {
      return l;
    }
    int left = l;
    int right = r;
    int pivot = nums[left];
    while(left < right) {
      while(left < right && nums[right] >= pivot) {
        right--;
      }
      nums[left] = nums[right];
      while(left < right && nums[left] <= pivot) {
        left++;
      }
      nums[right] = nums[left];
    }
    nums[left] = pivot;
    return left;
  }

  //另一个方法就是维持一格长度为k+1的pq, 然后先塞数，当size大于k就poll出来，最后poll出来的就是答案
  public int findKthLargest1(int[] nums, int k) {
    PriorityQueue<Integer> pq = new PriorityQueue<>(k + 1);
    for(int num : nums) {
      pq.offer(num);
      if(pq.size() > k) {
        pq.poll();
      }
    }
    return pq.poll();
  }

  //Test Case
  public static void main(String[] args) {
    KthLargestElementInAnArray test = new KthLargestElementInAnArray();
    int[] nums = {3,2,3,1,2,4,5,5,6};
    int k = 4;
    System.out.println(test.findKthLargest(nums,k));
  }
}
