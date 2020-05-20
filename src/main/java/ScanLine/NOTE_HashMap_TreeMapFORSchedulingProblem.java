package ScanLine;

public class NOTE_HashMap_TreeMapFORSchedulingProblem {
/**
 * 引入网友的总结：
 * https://leetcode.com/problems/meeting-rooms-ii/discuss/203658/HashMapTreeMap-resolves-Scheduling-Problem
 *
 * Here is the idea -
 *
 * Load all intervals to the TreeMap, where keys are intervals' start/end boundaries, and values accumulate the changes at that point in time.
 * Traverse the TreeMap (in other words, sweep the timeline). If a new interval starts, increase the counter (k value) by 1, and the counter decreases by 1, if an interval has finished.
 * Calcalulate the number of the active ongoing intervals.
 *
 * 想法很直接：
 * 将所有的start， end都放入treemap中做key, value的话：start += 1， end -= 1
 * 这样通过value的正负数flag可以判断这是个open 还是个 close
 * 根据对这个value的累加，也就是扫描一遍，可以得出现在 active ongoing intervals
 *
 * 这个思想可以解决如下问题：
 * 56. Merge Intervals
 * 57. Insert Interval (hard)
 * 252. Meeting Rooms
 * 253. Meeting Rooms II
 * 729. My Calendar I
 * 731. My Calendar II
 * 732. My Calendar III (hard)
 */
}
