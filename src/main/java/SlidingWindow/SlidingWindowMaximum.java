package SlidingWindow;

/**
 * 239. Sliding Window Maximum
 *
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
 * You can only see the k numbers in the window.
 * Each time the sliding window moves right by one position. Return the max sliding window.
 *
 * Follow up:
 * Could you solve it in linear time?
 *
 * Example:
 *
 * Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 *
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *
 *
 * Constraints:
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 1 <= k <= nums.length
 */

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 先写brute forth然后发现 time complexity 太高了，于是思考优化，想到deque
 * Deque用法补充：
 * First Element (Head): addFirst(); removeFirst(); getFirst() which will throw EXCEPTION for illegal operation
 *                       offerFirst(); pollFirst(); peekFirst() which will return special value
 *
 * Last Element (Tail): addLast(); removeLast(); getLast() which will throw EXCEPTION for illegal operation
 *                      offerLast(); pollLast(); peekLast() which will return special value
 *
 * All Known Implementing Classes:
 * ArrayDeque, ConcurrentLinkedDeque, LinkedBlockingDeque, LinkedList
 */
public class SlidingWindowMaximum {
  //Brute Force
  public int[] maxSlidingWindow_BF(int[] nums, int k) {
    int n = nums.length;
    int[] res =  new int[n - k + 1];
    for(int i = 0; i < n - k + 1; i++) {
      int max = Integer.MIN_VALUE;
      for(int j = i; j < i + k; j++) {
        max = Math.max(max, nums[j]);
      }
      res[i] = max;
    }
    return res;
  }
  //Time complexity: O(n*k)

  /**
   * Optimize: use Deque
   * Deque里面放的index
   * The idea is to use deque to hold the index of maximum element and restrict deque size to k.
   * In first while loop, we make sure that we remove the elements which are not longer in the sliding k range.
   * In second loop is we make sure that the elements in the deque is not smaller than the current element.
   * Then we add the element to the deque.
   *
   * The if(i >= k - 1) is just to skip the first few elements that is less than k.
   * For example, if k = 3, then we don't want the first two elements added to the array.
   */

  public int[] maxSlidingWindow_Deque(int[] nums, int k) {
    int n = nums.length;
    int[] res = new int[n - k + 1];
    Deque<Integer> dq = new ArrayDeque<>();
    int idx = 0; //fill the result from start
    for(int i = 0; i < n; i++) {
      //左边越界就挪出去
      while(!dq.isEmpty() && dq.peekFirst() < i - k + 1) {
        dq.pollFirst();
      }
      //右边每次遇到current element，都比较一下，保持只有更大的数进来deque
      //也就是右边永远保持着deque里面的elements >= current
      //一旦最右侧的element < current, 就pollLast()扔出去
      //这样下面offer进来的就是这个window里最大的element的index
      while(!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) {
        dq.pollLast();
      }
      //offer进来最大的
      dq.offerLast(i);
      //最前面k-1的那几个数不能放到结果中
      if(i >= k - 1) {
        res[idx++] = nums[dq.peekFirst()];
      }
    }
    return res;
  }

  //Test Case
  public static void main(String[] args) {
    SlidingWindowMaximum test = new SlidingWindowMaximum();
    int[] res = test.maxSlidingWindow_Deque(new int[] {1,3,-1,-3,5,3,6,7}, 3);
    //下面这种打印方法特别惊艳！！！
    //长这样！！！ [3, 3, 5, 5, 6, 7]
    String resVisual = Arrays.toString(res);
    System.out.println(resVisual);
  }
}
