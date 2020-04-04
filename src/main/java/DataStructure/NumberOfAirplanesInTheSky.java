package DataStructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * lINTCODE 题目 扫描线基础题：
 * 391. Number of Airplanes in the Sky
 * Given an list interval, which are taking off and landing time of the flight. How many airplanes are there at most at the same time in the sky?
 *
 * Example
 * Example 1:
 *
 * Input: [(1, 10), (2, 3), (5, 8), (4, 7)]
 * Output: 3
 * Explanation:
 * The first airplane takes off at 1 and lands at 10.
 * The second ariplane takes off at 2 and lands at 3.
 * The third ariplane takes off at 5 and lands at 8.
 * The forth ariplane takes off at 4 and lands at 7.
 * During 5 to 6, there are three airplanes in the sky.
 * Example 2:
 *
 * Input: [(1, 2), (2, 3), (3, 4)]
 * Output: 1
 * Explanation: Landing happen before taking off.
 * Notice：
 * 这句话非常非常非常关键！！！
 * If landing and taking off of different planes happen at the same time,
 * we consider landing should happen at first.
 *
 * 笔记：
 * 自己写的方法，把起飞和降落当成事件建立object进行排序，然后比较的时候写comparator
 * 掌握吧！
 */
public class NumberOfAirplanesInTheSky {
  public class Interval {
     int start, end;
     Interval(int start, int end) {
        this.start = start;
        this.end = end; }
}
  public class TimeSlot {
    public int time;
    public Character t;

    public TimeSlot() {
      time = 0;
      t = ' ';
    }
  }

  public int countOfAirplanes(List<Interval> airplanes) {
    // write your code here
    int n = airplanes.size();
    ArrayList<TimeSlot> list = new ArrayList<>();
    for(int i = 0; i < n; i++) {
      TimeSlot currStart = new TimeSlot();
      currStart.time = airplanes.get(i).start;
      currStart.t = 's';
      TimeSlot currEnd = new TimeSlot();
      currEnd.time = airplanes.get(i).end;
      currEnd.t = 'e';
      list.add(currStart);
      list.add(currEnd);
    }
    Collections.sort(list, new Comparator<TimeSlot>() {
      public int compare(TimeSlot t1, TimeSlot t2){
        if(t1.time == t2.time) { //这里就是处理降落的时间算在起飞之前！！！很重要！！！
          if(t1.t == 'e') return -1;
          else return 1;
        } else {
          return t1.time - t2.time;
        }
      }});

    int count = 0;
    int max = 0;
    for(int i = 0; i < list.size(); i++) {
      if(list.get(i).t == 's') {
        count++;
      } else {
        count--;
      }
      max = Math.max(max, count);
    }
    return max;
  }
}
