package CodingExerciseWithGary;

/**
 * 424. Longest Repeating Character Replacement
 *
 * Given a string s that consists of only uppercase English letters,
 * you can perform at most k operations on that string.
 *
 * In one operation, you can choose any character of the string
 * and change it to any other uppercase English character.
 *
 * Find the length of the longest sub-string containing all repeating letters
 * you can get after performing the above operations.
 *
 * Note:
 * Both the string's length and k will not exceed 104.
 *
 * Example 1:
 *
 * Input:
 * s = "ABAB", k = 2
 *
 * Output:
 * 4
 *
 * Explanation:
 * Replace the two 'A's with two 'B's or vice versa.
 *
 *
 * Example 2:
 *
 * Input:
 * s = "AABABBA", k = 1
 *
 * Output:
 * 4
 *
 * Explanation:
 * Replace the one 'A' in the middle with 'B' and form "AABBBBA".
 * The substring "BBBB" has the longest repeating letters, which is 4.
 */
public class LongestRepeatingCharacterReplacement {
  public int characterReplacement(String s, int k) {
    int maxFrequency = 0; // largest frequency
    int l = 0; // left pointer
    int r = 0; // right pointer
    int[] countFrequency = new int[26]; // count each character's frequency
    int maxWindow = 0; // store result

    //corner case
    if(s == null || s.length() == 0) return maxWindow;

    //sliding window
    while(r < s.length()) {
      countFrequency[s.charAt(r) - 'A']++;
      maxFrequency = Math.max(maxFrequency, countFrequency[s.charAt(r) - 'A']);
      if(r - l + 1 > maxFrequency + k) {
        countFrequency[s.charAt(l) - 'A']--;
        l++;
      }
      maxWindow = Math.max(maxWindow, r - l + 1);
      r++; //一定要注意右指针什么时候挪动，在一切操作都搞定了之后再挪
    }
    return maxWindow;
  }

  //Test Case
  public static void main(String[] args) {
    LongestRepeatingCharacterReplacement test = new LongestRepeatingCharacterReplacement();
    System.out.println(test.characterReplacement("AABABBA", 1));
  }
}
