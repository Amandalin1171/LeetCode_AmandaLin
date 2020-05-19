package ScanLine;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 252. Meeting Rooms
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 * determine if a person could attend all meetings.
 *
 * Example 1:
 *
 * Input: [[0,30],[5,10],[15,20]]
 * Output: false
 * Example 2:
 *
 * Input: [[7,10],[2,4]]
 * Output: true
 * NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 */
public class MeetingRooms {
  public boolean canAttendMeetings(int[][] intervals) {
    //corner case
    if(intervals == null || intervals.length == 0) return true;

    //sort by start time
    Arrays.sort(intervals, new Comparator<int[]>() {
      public int compare(int[] a1, int[] a2) {
        return a1[0] - a2[0];
      }
    });

    //one pass to check overlap
    int[] pre = intervals[0];
    for(int i = 1; i < intervals.length; i++) {
      int[] curr = intervals[i];
      if(curr[0] < pre[1]) return false;
      pre = curr;
    }
    return true;
  }
}
