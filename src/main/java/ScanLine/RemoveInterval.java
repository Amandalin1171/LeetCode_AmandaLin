package ScanLine;

import java.util.ArrayList;
import java.util.List;

/**
 * 1272. Remove Interval
 * Given a sorted list of disjoint intervals, each interval intervals[i] = [a, b] represents the set of real numbers x such that a <= x < b.
 *
 * We remove the intersections between any interval in intervals and the interval toBeRemoved.
 *
 * Return a sorted list of intervals after all such removals.
 *
 *
 *
 * Example 1:
 *
 * Input: intervals = [[0,2],[3,4],[5,7]], toBeRemoved = [1,6]
 * Output: [[0,1],[6,7]]
 * Example 2:
 *
 * Input: intervals = [[0,5]], toBeRemoved = [2,3]
 * Output: [[0,2],[3,5]]
 *
 *
 * Constraints:
 *
 * 1 <= intervals.length <= 10^4
 * -10^9 <= intervals[i][0] < intervals[i][1] <= 10^9
 */

/**
 * 自己的代码特别冗长，面试期间为了优化提炼出来writer但是逻辑还是有重复。
 * 看一下第二种方法，只要一开始判断了把interval劈成两半的那种情况，interva在remove内部的情况直接可以cover在后面两种情况下
 * 以及corner case想返回空的list的话：return Collections.emptyList();
 */
public class RemoveInterval {
  public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
    List<List<Integer>> res = new ArrayList<>();

    for(int[] interval : intervals) {
      int start = interval[0];
      int end = interval[1];

      // Without overlap
      if(end <= toBeRemoved[0] || start >= toBeRemoved[1]) {
        resultWriter(start, end, res);

        //total overlap
      } else if(toBeRemoved[0] > start && toBeRemoved[1] < end) {
        resultWriter(start, toBeRemoved[0], res);
        resultWriter(toBeRemoved[1], end, res);
      } else if(toBeRemoved[0] <= start && toBeRemoved[1] >= end) {

        continue;
        //overlap one side
      } else {
        int newStart = start;
        int newEnd = end;
        // overlap with head
        if(toBeRemoved[1] > start && toBeRemoved[1] <= end && toBeRemoved[0] <= start) {
          newStart = toBeRemoved[1];
          // overlap with tail
        } else if(toBeRemoved[0] > start && toBeRemoved[0] <= end && toBeRemoved[1] >= end) {
          newEnd = toBeRemoved[0];
          // overlap with whole interval
        }
        resultWriter(newStart, newEnd, res);
      }
    }
    return res;
  }

  //result writer
  private void resultWriter(int start, int end, List<List<Integer>> res) {
    List<Integer> curr = new ArrayList<>();
    curr.add(start);
    curr.add(end);
    res.add(curr);
  }

  //大神解法：来源：leetcodeSolution
  public List<List<Integer>> removeInterval2(int[][] intervals, int[] toBeRemoved) {
    int removeStart = toBeRemoved[0], removeEnd = toBeRemoved[1];
    List<List<Integer>> output = new ArrayList<List<Integer>>();

    for (int[] interval : intervals) {
      int start = interval[0], end = interval[1];

      if (end <= removeStart || start >= removeEnd) {
        // if current interval ends before toBeRemoved
        // or starts after
        output.add(new ArrayList<Integer>() {{add(start); add(end); }});

      } else if (start < removeStart && end > removeEnd) {
        // if the interval to be removed is inside
        // of the current interval
        output.add(new ArrayList<Integer>() {{add(start); add(removeStart); }}); //神奇写法
        output.add(new ArrayList<Integer>() {{add(removeEnd); add(end); }});

      } else if (start < removeStart && end <= removeEnd) {
        // "left" overlap
        output.add(new ArrayList<Integer>() {{add(start); add(removeStart); }});

      } else if (start >= removeStart && end > removeEnd) {
        // "right" overlap
        output.add(new ArrayList<Integer>() {{add(removeEnd); add(end); }});
      }
    }
    return output;
  }
}
