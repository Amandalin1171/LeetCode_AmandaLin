package TwoPointers;

import java.util.Arrays;

/**
 * 1471. The k Strongest Values in an Array
 * Given an array of integers arr and an integer k.
 *
 * A value arr[i] is said to be stronger than a value arr[j] if |arr[i] - m| > |arr[j] - m| where m is the median of the array.
 * If |arr[i] - m| == |arr[j] - m|, then arr[i] is said to be stronger than arr[j] if arr[i] > arr[j].
 *
 * Return a list of the strongest k values in the array. return the answer in any arbitrary order.
 *
 * Median is the middle value in an ordered integer list. More formally, if the length of the list is n, the median is the element in position ((n - 1) / 2) in the sorted list (0-indexed).
 *
 * For arr = [6, -3, 7, 2, 11], n = 5 and the median is obtained by sorting the array arr = [-3, 2, 6, 7, 11] and the median is arr[m] where m = ((5 - 1) / 2) = 2. The median is 6.
 * For arr = [-7, 22, 17, 3], n = 4 and the median is obtained by sorting the array arr = [-7, 3, 17, 22] and the median is arr[m] where m = ((4 - 1) / 2) = 1. The median is 3.
 */

/**
 * 不能while(l <= r)里面再套一个for loop
 * 这样for loop会一直被执行，直到最外面的while循环条件不再满足为止。
 * 当写成两层循环嵌套的时候一定要谨慎思考
 */
public class TheKStrongestValuesInAnArray {
  public int[] getStrongest(int[] arr, int k) {
    int[] res = new int[k];
    int n = arr.length;
    Arrays.sort(arr);
    int median = arr[(n - 1) / 2];
    int l = 0;
    int r = n - 1;
    int i = 0; //pointer on res
    while(i < k && l <= r) {
      int left = arr[l];
      int right = arr[r];
      if(Math.abs(left - median) <= Math.abs(right - median)) {
        res[i] = right;
        r--;
      } else if(Math.abs(left - median) > Math.abs(right - median)) {
        res[i] = left;
        l++;
      }
      i++;
    }
    return res;
  }
}
