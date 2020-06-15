package TwoPointers;

/**
 * 977. Squares of a Sorted Array
 * Given an array of integers A sorted in non-decreasing order, return an array of the squares of each number, also in sorted non-decreasing order.
 *
 * Example 1:
 * Input: [-4,-1,0,3,10]
 * Output: [0,1,9,16,100]
 *
 * Example 2:
 * Input: [-7,-3,2,3,11]
 * Output: [4,9,9,49,121]
 *
 * Note:
 * 1 <= A.length <= 10000
 * -10000 <= A[i] <= 10000
 * A is sorted in non-decreasing order.
 */

/**
 * 第一眼肯定想到的是用sort的方法，就是丢到priorityqueue里面，一个个poll
 * 但是要想得到O(n)的时间复杂度，就需要用2 pointer
 * 需要注意的2 pointers比较tricky的点是：两边填找结果，往结果里面填的时候注意是从后往前填
 */
public class SquaresOfASortedArray {
  public int[] sortedSquares(int[] A) {
    int n = A.length;
    int l = 0;
    int r = n - 1;
    int[] res = new int[n];

    for(int i = n - 1; i >= 0; i--) {
      if(Math.abs(A[l]) >= Math.abs(A[r]) ) {
        res[i] = (int)Math.pow(A[l], 2);
        l++;
      } else {
        res[i] = (int)Math.pow(A[r], 2);
        r--;
      }
    }
    return res;
  }
}
