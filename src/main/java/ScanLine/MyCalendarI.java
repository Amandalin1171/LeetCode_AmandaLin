package ScanLine;

import java.util.TreeMap;

/**
 * 729. My Calendar I
 * Implement a MyCalendar class to store your events. A new event can be added if adding the event will not cause a double booking.
 * Your class will have the method, book(int start, int end). Formally,
 * this represents a booking on the half open interval [start, end), the range of real numbers x such that start <= x < end.
 * A double booking happens when two events have some non-empty intersection (ie., there is some time that is common to both events.)
 * For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully
 * without causing a double booking. Otherwise, return false and do not add the event to the calendar.
 *
 * Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
 * Example 1:
 *
 * MyCalendar();
 * MyCalendar.book(10, 20); // returns true
 * MyCalendar.book(15, 25); // returns false
 * MyCalendar.book(20, 30); // returns true
 * Explanation:
 * The first event can be booked.  The second can't because time 15 is already booked by another event.
 * The third event can be booked, as the first event takes every time less than 20, but not including 20.
 *
 *
 * Note:
 *
 * The number of calls to MyCalendar.book per test case will be at most 1000.
 * In calls to MyCalendar.book(start, end), start and end are integers in the range [0, 10^9].
 */

/**
 * 0. 在initiate TreeMap时候注意： 由于我们要call的function是treemap独有的，所以variable必须存treemap而不是map，详见47行注释
 * 1. TreeMap的用法，在Data Structure里的Notes也有补充。
 *  这俩个method返回值是个KEY 是Integer，wrapping object不是int
 *  * eg: Integer preStart = calendar.floorKey(start); 是Integer，不是int
 *  * floorKey(K key)
 *  * Returns the greatest key less than or equal to the given key, or null if there is no such key.
 *  *
 *  * ceilingKey(K key)
 *  * Returns the least key greater than or equal to the given key, or null if there is no such key.
 */
public class MyCalendarI {
  class MyCalendar {
    TreeMap<Integer, Integer> calendar; //为什么不能写Map<Integer, Integer> calendar
    //因为这是class的variable,我们call的function是treemap独有的不是map共有的，所以必须让这个class
    //里面的variable就是一个treemap
    public MyCalendar() {
      calendar = new TreeMap<>();
    }

    public boolean book(int start, int end) {
      Integer preStart = calendar.floorKey(start); //这个method返回值是个KEY 是Integer，wrapping object不是int
      if(preStart != null && calendar.get(preStart) > start) return false;
      Integer nextStart = calendar.ceilingKey(start);
      if(nextStart != null && nextStart < end) return false;

      calendar.put(start, end);
      return true;
    }
  }
}
