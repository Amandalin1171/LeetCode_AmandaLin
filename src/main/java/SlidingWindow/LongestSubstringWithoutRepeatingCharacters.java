package SlidingWindow;

/**
 * 3. Longest Substring Without Repeating Characters
 * Given a string, find the length of the longest substring without repeating characters.
 *
 * Example 1:
 *
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 *
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 *
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */

import java.util.HashSet;
import java.util.Set;

/**
 * 核心思想就是：两指针同时从左边开始，右指针挪动，往set里面加东西，如果set里面之前没有过这个char，就继续挪动
 * 右指针，同时更新左右指针之间的窗口宽度。
 * 如果set里面之间加过这个char，就挪动左指针同时将左指针的char从set里面删掉。
 * 此处之所以可以安全的删掉set里的东西是因为：
 * 右指针走过的地方都是没出现重复的，就算此刻右指针指向的是重复的，它也没在动了，我们删掉左指针看看是不是重复的那个
 * 不是的话就继续删，继续挪动左指针
 */
public class LongestSubstringWithoutRepeatingCharacters {
  public int lengthOfLongestSubstring(String s) {
    int left = 0; //left pointer
    int right = 0; //right pointer
    int res = 0; //final result
    Set<Character> set = new HashSet<>();

    //sliding window
    while(right < s.length() && right < s.length()) {
      if(!set.contains(s.charAt(right))) {
        set.add(s.charAt(right));
        res = Math.max(res, right - left + 1);
        right++;
      } else {
        set.remove(s.charAt(left));
        left++;
      }
    }
    return res;
  }
}
