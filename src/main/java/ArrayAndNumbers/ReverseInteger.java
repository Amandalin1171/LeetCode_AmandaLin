package ArrayAndNumbers;

/**
 * 7. Reverse Integer
 * Given a 32-bit signed integer, reverse digits of an integer.
 *
 * Example 1:
 * Input: 123
 * Output: 321
 *
 * Example 2:
 * Input: -123
 * Output: -321
 *
 * Example 3:
 * Input: 120
 * Output: 21
 *
 * Note:
 * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−2^31,  2^31 − 1].
 * For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
 */

/**
 * 方法1： 上来没看如果overflow要返回0，这简直就是瞎啊！！！
 * 这道题考的就是如果数字正着写是Integer范围内，倒过来就超标了怎么办！！！
 * 比如：1,534,236,469 倒过来就超标了呀！！！
 * int -2,147,483,648 to 2,147,483,647
 *
 * 面试的时候一定要记得这种事情呀，integer 翻转之后就可能超标不是integer了呀喂！！！
 * 所以一个简单的方法是用long做，然后判断是不是>Integer.MAX.VALUE or < Integer.MIN_VALUE
 *
 * 方法2： 我自己写的 cast成String之后就会因为长度越界导致NumberFormatException 所以这个时候用try catch直接非法的时候返回0，符合题意
 */
public class ReverseInteger {
  //方法1：正经方法
  public int reverse1(int x) {
    long answer = 0;
    while(x != 0) {
      answer = 10 * answer + x % 10;
      x /= 10;
    }
    return (answer > Integer.MAX_VALUE || answer < Integer.MIN_VALUE) ? 0 : (int) answer;
  }

  //方法2：自己写的，但是可以学到try catch 的歪门邪道
  public int reverse2(int x) {
    String s = Integer.toString(x);
    StringBuilder sb = new StringBuilder();
    //corner case
    if(x == 0) return 0;

    //tackle negative sign
    if(s.charAt(0) == '-') sb.append('-');

    //append from back to front
    for(int i = s.length() - 1; i >= 0; i--) {
      if((i == s.length() - 1 && s.charAt(i) == '0') || (i == 0 && s.charAt(i) == '-')) continue;
      sb.append(s.charAt(i));
    }

    int res = 0;
    try {
      res = Integer.parseInt(sb.toString());
    } catch (NumberFormatException ignored) {
      System.out.println(res);
    }
    return res;
  }
}
