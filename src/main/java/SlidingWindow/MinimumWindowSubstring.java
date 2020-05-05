package SlidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 76. Minimum Window Substring
 *
 * Given a string S and a string T, find the minimum window in S which will contain
 * all the characters in T in complexity O(n).
 *
 * Example:
 *
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 * Note:
 *
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 */
public class MinimumWindowSubstring {
  public String findSContainsAllT(String S, String T) {

    //final result
    String res = "";

    //corner case
    if(S == null || S.length() == 0 || T == null || T.length() == 0) return res;

    //define variables
    Map<Character, Integer> freqMap = new HashMap<>(); //key: Characters in T; value: freqency in T
    int min = Integer.MAX_VALUE; //min length
    int l = 0; //left pointer
    int r = 0; //right pointer

    //build frequency map
    for(int i = 0; i < T.length(); i++) {
      freqMap.put(T.charAt(i), freqMap.getOrDefault(T.charAt(i), 0) + 1);
    }

    //sliding window
    int countValidCharacter = 0;

    //move right pointer to find a valid window
    for(; r < S.length(); r++) {
      if(freqMap.containsKey(S.charAt(r))) {
        freqMap.put(S.charAt(r), freqMap.get(S.charAt(r)) - 1);
        if(freqMap.get(S.charAt(r)) == 0) {
          countValidCharacter++;
        }
      }

      //found a valid window and then compress by moving left pointer
      while(l <= r && countValidCharacter == T.length()) {
        if(min > r - l + 1){
          res = S.substring(l, r + 1);
          min = r - l + 1;
        }
        if(freqMap.containsKey(S.charAt(l))) {
          freqMap.put(S.charAt(l), freqMap.get(S.charAt(l)) + 1);
          if(freqMap.get(S.charAt(l)) > 0) {
            countValidCharacter--;
          }
        }
        l++;
      }
    }

    return res;
  }

  //Test Case
  public static void main(String[] args) {
    MinimumWindowSubstring test = new MinimumWindowSubstring();
    System.out.println(test.findSContainsAllT("ADOBECODEBANC", "ABC"));
  }
}
