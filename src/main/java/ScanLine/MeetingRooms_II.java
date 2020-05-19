package ScanLine;

/**
 * 253. Meeting Rooms II
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 * find the minimum number of conference rooms required.
 *
 * Example 1:
 *
 * Input: [[0, 30],[5, 10],[15, 20]]
 * Output: 2
 * Example 2:
 *
 * Input: [[7,10],[2,4]]
 * Output: 1
 * NOTE: input types have been changed on April 15, 2019.
 * Please reset to default code definition to get new method signature.
 */

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 *方法1： TreeMap. 我的理解是：
 * this method is a bit like Valid Parentheses:
 * we need to pair an open time of meeting with exactly a close time of meeting followed by it.
 * So example 1(start), 2(start),10(end),12(end), 14(start),15(end) -> open, open, close, close, open, close.
 * We can easily see there are two consecutive open, we need 2 meeting rooms.
 *
 * 方法2：合并连续的会议区间：
 * group non-connecting intervals into a larger interval; in the end,
 * we check how many such "larger" intervals are left
 */
public class MeetingRooms_II {
  /**
   * 方法1：TreeMap
   * Dry Run: [[0, 30],[5, 10],[15, 20]]
   *   key: 0 5 10 15 20 30(sorted in treemap)
   * value: 1 1 -1 1 -1 -1
   * count: 1 2  2 2  2 2  结果就是2
   */
  public int minMeetingRooms(int[][] intervals) {
    Map<Integer, Integer> map = new TreeMap<>();
    //start: ++ end:--
    for(int[] interval : intervals) {
      map.put(interval[0], map.getOrDefault(interval[0], 0) + 1);
      map.put(interval[1], map.getOrDefault(interval[1], 0) - 1);
    }
    int roomNum = 0; int count = 0;
    for(int v : map.values()) {
      count = Math.max(count, roomNum += v);
    }
    return count;
  }

  //方法2：合并并且把合并的start改成flag: -1，然后count不是-1的start数量就是需要几个meeting room
  public int minMeetingRooms2(int[][] intervals) {
    Arrays.sort(intervals, ((a1, a2) -> a1[0] - a2[0]));
    int count = 0;
    for(int i = 0; i < intervals.length - 1; i++) {
      if(intervals[i][0] == -1) continue;
      for(int j = i + 1; j < intervals.length; j++) {
        if(intervals[j][0] >= intervals[i][1]) {
          intervals[i][1] = intervals[j][1];
          intervals[j][0] = -1;
        }
      }
    }

    for(int i = 0; i < intervals.length; i++) {
      if(intervals[i][0] != -1) {
        count++;
      }
    }

    return count;
  }
}
