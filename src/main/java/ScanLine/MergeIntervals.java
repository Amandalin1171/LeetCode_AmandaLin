package ScanLine;

/**
 * 56. Merge Intervals
 * Given a collection of intervals, merge all overlapping intervals.
 *
 * Example 1:
 *
 * Input: [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * Example 2:
 *
 * Input: [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 自己写代码遇到的困难和坑：
 * 0.最最重要的一点：指针，obj pass by reference。 int pass by value
 * 具体看55行注释
 * 1. 之前code不work是因为要一直合并直到遇到一个不需要合并的区间才可以安全写入结果，
 * 不然合并一个就写入结果的话，这个可能与下一个interval继续合并，就会重复写这段。
 *
 * 2. 提出pre然后for loop要从0开始，而不是1开始，因为这样会在攒pre和curr的时候丢掉最后一截
 * 自己跟自己先比一次也没什么不好，为了保证连续性。
 */
public class MergeIntervals {
  public int[][] merge(int[][] intervals) {
    //corner case
    if(intervals == null || intervals.length <= 1) return intervals;

    //sort by start
    Arrays.sort(intervals, new Comparator<int[]>() {
      @Override
      public int compare(int[] a1, int[] a2) {
        return a1[0] - a2[0];
      }
    });

    //find result
    List<int[]> res = new ArrayList<>();
    int[] newInterval = intervals[0];
    res.add(newInterval);

    for(int[] interval : intervals) {
      //overlap
      if(interval[0] <= newInterval[1]) {
        //[[1,3],[2,6],[8,10],[15,18]]这个例子中
        //下面这种是改变了原始的intervals[0]的值（里面是int pass by value)，newInterval是指向它的指针，跟着变(pass by reference)
        newInterval[1] = Math.max(interval[1], newInterval[1]);
      } else {
        //disjoint
        //这是挪动了指针的指向，newInterval的指针指向了当前的interval
        newInterval = interval;
        res.add(newInterval);
      }
    }
    return res.toArray(new int[res.size()][2]); //arraylist 转int[][]的写法：需要define array的length
  }
}
