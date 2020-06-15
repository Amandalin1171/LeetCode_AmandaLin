package BinarySearch;

/**
 * 852. Peak Index in a Mountain Array
 * Let's call an array A a mountain if the following properties hold:
 *
 * A.length >= 3
 * There exists some 0 < i < A.length - 1 such that A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1]
 * Given an array that is definitely a mountain, return any i such that A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1].
 *
 * Example 1:
 * Input: [0,1,0]
 * Output: 1
 *
 * Example 2:
 * Input: [0,2,1,0]
 * Output: 1
 *
 * Note:
 * 3 <= A.length <= 10000
 * 0 <= A[i] <= 10^6
 * A is a mountain, as defined above.
 */

/**
 * 判断的条件是：此刻的数字跟后面的一个数字比较，
 * A[i] < A[i + 1]那就还在递增区间，还没找到peak, 把左指针挪到A[i + 1]
 * A[i] >= A[i + 1]那就已经越过了peak，进入了下降区间了，把右指针挪到 A[i]
 */
public class PeakIndexInAMountainArray {
  public int peakIndexInMountainArray(int[] A) {
    //corner case
    if(A == null || A.length < 3) return -1;

    int n = A.length;
    int l = 0;
    int r = n - 1;

    //binary search
    while(l < r) {
      int mid = l + (r - l) / 2;
      if(A[mid] < A[mid + 1]) {
        l = mid + 1;
      } else {
        r = mid;
      }
    }
    return l;
  }
}
