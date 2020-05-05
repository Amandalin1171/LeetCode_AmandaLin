package SlidingWindow;

/**
 * 1248. Count Number of Nice Subarrays
 *
 * Given an array of integers nums and an integer k. A subarray is called nice if there are k odd numbers on it.
 *
 * Return the number of nice sub-arrays.
 *
 * Example 1:
 *
 * Input: nums = [1,1,2,1,1], k = 3
 * Output: 2
 * Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].
 * Example 2:
 *
 * Input: nums = [2,4,6], k = 1
 * Output: 0
 * Explanation: There is no odd numbers in the array.
 * Example 3:
 *
 * Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
 * Output: 16
 *
 * Constraints:
 * 1 <= nums.length <= 50000
 * 1 <= nums[i] <= 10^5
 * 1 <= k <= nums.length
 */

/**
 * 第一种解法， 也是我很喜欢的解法，就是类似于：992. Subarrays with K Different Integers
 * (*^▽^*)个人称之为 “分解因式法”
 * 利用了思想：Exactly K times = at most K times - at most K - 1 times
 * 直接将其转换成一个常规的sliding window
 *
 * Generally, the sliding window problems have some kind of aggregate, atMost k, largest substring,
 * min substring with k etc. They're always "given an array or string, find some computed sub problem" value.
 *
 * 第二种：上来直接sliding window, tricky的地方就是如何generalize计算subarray个数的方法，看代码吧，感觉不好扩展
 */
public class CountNumberOfNiceSubarrays {
  //方法一：分解因式法： Exactly K = atMost K - atMost K-1
  public int numberOfSubarrays(int[] nums, int k) {
    //exactly k odd numbers = at most k odd numbers - at most (k-1) odd numbers
    return atMost(nums, k) - atMost(nums, k - 1);
  }

  private int atMost(int[] nums, int k) {
    int res = 0; //final result
    int l = 0; //left pointer

    for(int r = 0; r < nums.length; r++) {
      k -= nums[r] % 2;
      while(k < 0) {
        k += nums[l++] % 2;
      }
      res += r - l + 1;
    }
    return res;
  }

  //方法二：上来就硬刚sliding window法
  public int numberOfSubarrays2(int[] nums, int k) {
    int left = 0; //left pointer
    int countOdd = 0; //count odd numbers in the window
    int countPre = 0; //count valid results in left part in the window
    int res = 0; // final result

    for(int right = 0; right < nums.length; right++) {
      if(nums[right] % 2 != 0) {
        countOdd++;
        if(countOdd >= k) {
          countPre = 1;
          while(nums[left++] % 2 == 0) {
            countPre++;
          }
          res += countPre;
        }
      } else if(countOdd >= k) {
        res += countPre;
      }
    }
    return res;
  }
}
