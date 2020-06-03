package PreSum;

public class PreSum_NOTES {
/**
 * 总结：
 * 非常重要！！！！！
 *
 * 第一类： 求个数，fake head 0, 1, frequency map
 * 560. Subarray Sum Equals K
 * 930. Binary Subarrays With Sum
 * 437. Path Sum III
 * 上面这三个题都是要返回一共有多少种凑成这个SUM的组合，求得是一个count
 * preSum都是frequency map，一开始的fake head k:v pair都是0,1
 * 表示一个presum = 0， 出现了一次
 *
 * 第二类： 求最长长度， fake head 0, -1, preSum + index 构成hashmap
 * 525. Contiguous Array
 * 325. Maximum Size Subarray Sum Equals k
 * 上述两个题都是返回得到这个SUM的最长的subarray，求得是一个长度
 * preSum都是preSum 对应 index， 这次value不是frequency啦是index，这样才能index减法求出长度
 * 所以这种的fake head 是：k: v = 0, -1
 * 表示最初的preSum = 0的fake index是0前面一位 是 -1
 */
}
