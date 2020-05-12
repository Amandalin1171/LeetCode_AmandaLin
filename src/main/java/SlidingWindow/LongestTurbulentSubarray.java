package SlidingWindow;

/**
 * 978. Longest Turbulent Subarray
 *
 * A subarray A[i], A[i+1], ..., A[j] of A is said to be turbulent if and only if:
 *
 * For i <= k < j, A[k] > A[k+1] when k is odd, and A[k] < A[k+1] when k is even;
 * OR, for i <= k < j, A[k] > A[k+1] when k is even, and A[k] < A[k+1] when k is odd.
 * That is, the subarray is turbulent if the comparison sign flips between each adjacent
 * pair of elements in the subarray.
 *
 * Return the length of a maximum size turbulent subarray of A.
 *
 * Example 1:
 *
 * Input: [9,4,2,10,7,8,8,1,9]
 * Output: 5
 * Explanation: (A[1] > A[2] < A[3] > A[4] < A[5])
 * Example 2:
 *
 * Input: [4,8,12,16]
 * Output: 2
 * Example 3:
 *
 * Input: [100]
 * Output: 1
 *
 * Note:
 * 1 <= A.length <= 40000
 * 0 <= A[i] <= 10^9
 */

/**
 * sliding window 我一开始想到的时候flipping coin就是每一次都换一个sign的符号
 * 代码见第一种方法；
 *
 * 还有一种方法是DP，迟早会遇到DP的还是赶紧吃透吧
 * 见第二种方法。
 */
public class LongestTurbulentSubarray {
  //方法1： flipping coin + sliding window
  public int maxTurbulenceSize(int[] A) {
    int r = 1; //right pointer
    int n = A.length;
    int flag = 0; //flipping sign
    int res = 0; //final result

    for(; r < n; r++) {
      if(A[r] > A[r - 1]) {
        if(flag > 0) flag += 1;
        else flag = 1; //此处不能写成：else if(flag < 0) 因为就算flag == 0 意味着之前俩数相等，那现在递增了，开始算成1
        flag = flag * (-1); //flip the flag
      } else if(A[r] < A[r - 1]) {
        if(flag < 0) flag -= 1;
        else flag = -1; //之前的数字相等，刚开始递减
        flag = flag * (-1); //flip the flag
      } else {
        flag = 0;
      }
      res = Math.max(res, Math.abs(flag));
    }
    return res + 1;
  }

  //Test Case
  public static void main(String[] args) {
    LongestTurbulentSubarray test = new LongestTurbulentSubarray();
    System.out.println(test.maxTurbulenceSize(new int[] {9,4,2,10,7,8,8,1,9}));
  }
}
