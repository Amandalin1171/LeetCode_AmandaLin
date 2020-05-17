package SlidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 159. Longest Substring with At Most Two Distinct Characters
 * Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.
 *
 * Example 1:
 *
 * Input: "eceba"
 * Output: 3
 * Explanation: t is "ece" which its length is 3.
 * Example 2:
 *
 * Input: "ccaabbb"
 * Output: 5
 * Explanation: t is "aabbb" which its length is 5.
 */

/**
 * 用一个bar来flag 2这个上限，挪右指针，遇到新的char 这个bar就--
 * 当ba减到负数的时候，开始挪左指针，把左指针指向的char --， 如果减到0，那么bar++
 * 最后进行结算，结算必须在上面两个步骤之后，这样可以保证此刻bar >= 0就都是符合题目要求的
 */
public class LongestSubstringWithAtMostTwoDistinctCharacters {
  public int lengthOfLongestSubstringTwoDistinct(String s) {
    int res = 0;
    Map<Character, Integer> freqMap = new HashMap<>(); //frequency map of characters in s
    int l = 0; //left pointer
    int r = 0; //right pointer
    int bar = 2;

    //corner case
    if(s == null || s.length() == 0) return res;

    //sliding window
    for(; r < s.length(); r++) {
      if(freqMap.getOrDefault(s.charAt(r), 0) == 0) {
        bar--;
      }
      freqMap.put(s.charAt(r), freqMap.getOrDefault(s.charAt(r), 0) + 1);

      //move left pointer
      while(bar < 0) {
        freqMap.put(s.charAt(l), freqMap.get(s.charAt(l)) - 1);
        if(freqMap.get(s.charAt(l)) == 0) {
          bar++;
        }
        l++;
      }

      //settlement
      res = Math.max(res, r - l + 1);
    }
    return res;
  }
}
