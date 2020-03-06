package DataStructure;

import java.util.HashMap;
import java.util.Map;

/**
 * 题目：
 * 246. Strobogrammatic Number
 *
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees
 * (looked at upside down).
 *
 * Write a function to determine if a number is strobogrammatic.
 * The number is represented as a string.
 *
 * Example 1:
 *
 * Input:  "69"
 * Output: true
 * Example 2:
 *
 * Input:  "88"
 * Output: true
 * Example 3:
 *
 * Input:  "962"
 * Output: false
 */

/**
 * 这题是为了StrobogrammaticNumber_II 和 III做铺垫的，II在DFS里，III也在dataStructure里，
 * 用到了自己写Comparator或者compareTo() function。
 * 能够get到的是这种翻转180°对称的只有自我对称和69，也就是0， 8， 1 和6+9
 * 一脸姨母笑^^
 * 左右夹击，两边必须是对应的pair就完事了，很简单！快去复习II和III吧！
 */
public class StrobogrammaticNumber_I {
  public boolean isStrobogrammatic(String num) {
    Map<Character, Character> pairMap = new HashMap<>();
    pairMap.put('0', '0');
    pairMap.put('8', '8');
    pairMap.put('1', '1');
    pairMap.put('6', '9');
    pairMap.put('9', '6');

    int left = 0;
    int right = num.length() - 1;
    while(left <= right) {
      if(!pairMap.containsKey(num.charAt(left))) return false;
      else if(pairMap.get(num.charAt(left)) != num.charAt(right)) return false;
      else{
        left++;
        right--;
      }
    }
    return true;
  }

  //Test Class
  public static void main(String[] args) {
    StrobogrammaticNumber_I testClass = new StrobogrammaticNumber_I();
    boolean res = testClass.isStrobogrammatic("69");
    System.out.println(res);
  }
}
