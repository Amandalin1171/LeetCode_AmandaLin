package Design;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 346. Moving Average from Data Stream
 * Given a stream of integers and a window size, calculate the moving average of all integers
 * in the sliding window.
 *
 * Example:
 * MovingAverage m = new MovingAverage(3);
 * m.next(1) = 1
 * m.next(10) = (1 + 10) / 2
 * m.next(3) = (1 + 10 + 3) / 3
 * m.next(5) = (10 + 3 + 5) / 3
 */

/**
 * 一开始想自己写个class: fixed-size queue
 * 但是发现并行不通，每次还要复制出来一个current的queue来poll出来结果求平均值
 * 最简单的解法就是一个queue
 * 大小达到size之后记录一个tail，每次去掉tail再加进去现在的current value即可
 */
public class MovingAverageFromDataStream {
  /** Initialize your data structure here. */
  Deque<Integer> dq = new ArrayDeque<>();
  int size;
  int count = 0;
  int tail = 0;
  int sum = 0;

  public MovingAverageFromDataStream(int size) {
    this.size = size;
  }

  public double next(int val) {
    dq.add(val);

    count++;
    if(count > size) {
      tail = dq.pollFirst();
      count--;
    } else {
      tail = 0;
    }
    sum = sum + val - tail;
    return (double)sum / Math.min(count, size);
  }

  //自己写的垃圾代码：
//  /** Initialize your data structure here. */
//  public class LimitedQueue<E> extends LinkedList<E> {
//    private int limit;
//    public LimitedQueue(int limit) {
//      this.limit = limit;
//    }
//    public boolean add(E e) {
//      super.add(e);
//      while(super.size() > limit) {
//        super.remove();
//      }
//      return true;
//    }
//  }
//  int size = 0;
//  LimitedQueue<Integer> lq = new LimitedQueue<>(size);
//  public MovingAverageFromDataStream(int size) {
//    this.size = size;
//  }
//
//  public double next(int val) {
//    lq.add(val);
//    LimitedQueue<Integer> curr = new LimitedQueue<>(size);
//    double res= 0d;
//    Iterator<Integer> it = lq.iterator();
//    while(it.hasNext()) {
//      curr.add(it.next());
//    }
//    while(!curr.isEmpty()) {
//      res = res + curr.poll();
//    }
//    return res/size;
//  }

  //test
  public static void main(String[] args) {
    MovingAverageFromDataStream m = new MovingAverageFromDataStream(3);
    m.next(1);
    m.next(10);
    m.next(3);
    m.next(5);
  }
}
