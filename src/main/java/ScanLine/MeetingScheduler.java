package ScanLine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 1229. Meeting Scheduler
 * Given the availability time slots arrays slots1 and slots2 of two people and a meeting duration duration, return the earliest time slot that works for both of them and is of duration duration.
 *
 * If there is no common time slot that satisfies the requirements, return an empty array.
 *
 * The format of a time slot is an array of two elements [start, end] representing an inclusive time range from start to end.
 *
 * It is guaranteed that no two availability slots of the same person intersect with each other. That is, for any two time slots [start1, end1] and [start2, end2] of the same person, either start1 > end2 or start2 > end1.
 *
 *
 *
 * Example 1:
 *
 * Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 8
 * Output: [60,68]
 * Example 2:
 *
 * Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 12
 * Output: []
 *
 *
 * Constraints:
 *
 * 1 <= slots1.length, slots2.length <= 10^4
 * slots1[i].length, slots2[i].length == 2
 * slots1[i][0] < slots1[i][1]
 * slots2[i][0] < slots2[i][1]
 * 0 <= slots1[i][j], slots2[i][j] <= 10^9
 * 1 <= duration <= 10^6
 */

/**
 * 唯一比较tricky的地方就是什么条件下挪动指针，不要想得太复杂，不管是两个此刻的slot有没有overlap
 * 是压根没有overlap还是overlap的区间不够长，下一次挪动指针的唯一标准就是：
 * 此刻谁的end靠前，谁就要往挪一个位置，看看下一个start跟另一面靠后的end有没有overlap
 */
public class MeetingScheduler {
  public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
    //sort slots1 & slots2 by start time
    sortSlots(slots1);
    sortSlots(slots2);

    int p1 = 0; //pointer walk on slots1
    int p2 = 0; //pointer walk on slots2
    int n1 = slots1.length;
    int n2 = slots2.length;
    List<Integer> res = new ArrayList<>();

    //2 pointer to find overlap period
    while(p1 < n1 && p2 < n2) {
      int overlapStart = Math.max(slots1[p1][0], slots2[p2][0]);
      int overlapEnd = Math.min(slots1[p1][1], slots2[p2][1]);
      if(overlapStart + duration <= overlapEnd) { //find earliest result
        res.add(overlapStart);
        res.add(overlapStart + duration);
        break;
      } else if(slots1[p1][1] > slots2[p2][1]) {
        p2++;
      } else {
        p1++;
      }
    }
    return res;
  }

  //sort helper function
  private void sortSlots(int[][] slots) {
    Arrays.sort(slots, new Comparator<int[]>() {
      @Override
      public int compare(int[] a, int[] b) {
        return a[0] - b[0];
      }
    });
  }
}
